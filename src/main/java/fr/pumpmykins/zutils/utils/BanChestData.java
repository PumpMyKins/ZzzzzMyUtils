package fr.pumpmykins.zutils.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pumpmykins.zutils.MainPmkUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BanChestData extends WorldSavedData {

	public BanChestData(String name) {
		super(name);
		this.chestPos = new ArrayList<BlockPos>();
		this.setItemban(new ArrayList<ItemStack>());
	}

	public BanChestData() {
		super(MainPmkUtils.getBanItemKey());
		this.chestPos = new ArrayList<BlockPos>();
		this.setItemban(new ArrayList<ItemStack>());
	}

	private List<BlockPos> chestPos;
	private List<ItemStack> itemban;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		NBTTagList banitem_list = nbt.getTagList(MainPmkUtils.getBanItemKey(), NBT.TAG_COMPOUND);

		for(int i = 0; i < banitem_list.tagCount(); i++) {

			NBTTagCompound tmp_nbt = banitem_list.getCompoundTagAt(i);

			long x = tmp_nbt.getLong("x");
			long y = tmp_nbt.getLong("y");
			long z = tmp_nbt.getLong("z");

			BlockPos cpos = new BlockPos(x,y,z);

			this.chestPos.add(cpos);
		}

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		NBTTagList banitem_list = new NBTTagList();
		for(BlockPos cpos : this.chestPos) {

			NBTTagCompound tmp = new NBTTagCompound();

			tmp.setLong("x", cpos.getX());
			tmp.setLong("y", cpos.getY());
			tmp.setLong("z", cpos.getZ());

			banitem_list.appendTag(tmp);
		}

		compound.setTag(MainPmkUtils.getBanItemKey(), banitem_list);

		return compound;
	}

	public List<BlockPos> getAllChest() {

		return this.chestPos;
	}

	public List<ItemStack> getItemban() {
		return itemban;
	}

	public void setItemban(List<ItemStack> itemban) {
		this.itemban = itemban;
	}

	public boolean addBanItem(ItemStack is, MinecraftServer server) {

		if(this.itemban.contains(is)) {

			return false;
		} else {

			if(!this.getAllChest().isEmpty()) {

				for(Iterator<BlockPos> bpiterator = this.getAllChest().iterator(); bpiterator.hasNext();) {

					BlockPos bp = bpiterator.next();
					World w = server.getEntityWorld();

					if(!w.getBlockState(bp).toString().equals("minecraft:chest")) {

						System.out.println("THIS BLOCK IS NOT A CHEST ! " + bp.toString());
						this.removeChest(bp);
						continue;
					}

					TileEntity te = w.getTileEntity(bp);
					
					IItemHandler ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
					
					for(int i = 0; i < ih.getSlots(); i++) {

						ItemStack istack = ih.getStackInSlot(i);
						if(istack.isEmpty()) {

							ih.insertItem(i, is, false);
							this.itemban.add(is);
							return true;

						}
					}
				}
			}
			this.addNewChest(server);
			this.addBanItem(is, server);
		}
		return false;
	}

	public boolean removeBanItem(ItemStack is, MinecraftServer server) {

		if(!this.itemban.contains(is)) {

			return false;
		} else {

			for(BlockPos bp : this.getAllChest()) {

				World w = server.getEntityWorld();

				TileEntity te = w.getTileEntity(bp);

				IItemHandler ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				for(int i = 0; i < ih.getSlots(); i++) {

					ItemStack istack = ih.getStackInSlot(i);
					if(istack.equals(is)) {

						ih.extractItem(i, 65, false);
						this.itemban.remove(is);
						return true;

					}
				}
			}
		}
		return false;
	}

	public void addNewChest(MinecraftServer server) {

		BlockPos newchest = new BlockPos(0,8,0);

		boolean exist = false;
		if(!this.chestPos.isEmpty()) {

			BlockPos lastchest = this.chestPos.get(this.chestPos.size()+1);
			newchest = new BlockPos(lastchest.getX() + 2, newchest.getY(), newchest.getZ() +2);

		}
		for(int i = 0; i < this.chestPos.size(); i++) {
			if(newchest.equals(this.chestPos.get(i))) {

				exist = true;
				break;
			}
		}
		if(!exist) {

			IBlockState chest = Blocks.CHEST.getDefaultState();
			
			if(server.getEntityWorld().setBlockState(newchest, chest)) {

				this.chestPos.add(newchest);
				markDirty();
			}
		}
	}

	public void removeChest(BlockPos pos) {

		if(this.chestPos.contains(pos)) {

			this.chestPos.remove(pos);
		}
	}

}
