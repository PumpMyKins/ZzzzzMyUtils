package fr.pumpmykins.zutils.event;

import fr.pumpmykins.zutils.utils.BanChestData;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BanItemEventHandler {

	private BanChestData banitem;

	public BanItemEventHandler(BanChestData banitem) {

		this.banitem = banitem;
	}

	@SubscribeEvent
	public void onPlaceEvent(PlaceEvent event) {

		for(ItemStack is : this.banitem.getItemban()) {

			ItemStack isnew = new ItemStack(is.getItem(), 1);

			Block placedBlock = event.getPlacedBlock().getBlock();
			ItemStack ip = new ItemStack(placedBlock, 1);

			if(ItemStack.areItemsEqual(isnew, ip)) {

				EntityPlayer bp = event.getPlayer();
				
				ITextComponent hm = new TextComponentString("Vous avez tenter d'utiliser un Item banni !! ");
				hm.setStyle(PmkStyleTable.boldDarkRed());
				
				bp.sendMessage(hm);
				
				ip.setCount(0);
				event.setCanceled(true);
				return;
			}
		}
	}

	@SubscribeEvent
	public void onRightClick(PlayerInteractEvent.RightClickBlock event) {

		if(event.getItemStack().isEmpty()) {

			return;
		} else {

			Block ibs = event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock();

			ItemStack ip = new ItemStack(ibs , 1); 

			for(ItemStack is : this.banitem.getItemban()) {

				ItemStack isnew = new ItemStack(is.getItem(), 1);

				if(ItemStack.areItemsEqual(isnew, ip)) {
					
					EntityPlayer bp = event.getEntityPlayer();
					
					ITextComponent hm = new TextComponentString("Vous avez tenter d'utiliser un Item banni !! ");
					hm.setStyle(PmkStyleTable.boldDarkRed());
					
					bp.sendMessage(hm);
					
					event.getWorld().setBlockState(event.getPos(), Blocks.CAKE.getDefaultState());
					return;
				}
			}
		}
	}
}