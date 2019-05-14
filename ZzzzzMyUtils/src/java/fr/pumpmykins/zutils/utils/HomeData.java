package fr.pumpmykins.zutils.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.pumpmykins.zutils.MainPmkUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants.NBT;

public class HomeData extends WorldSavedData {

	public HomeData(String key) {
		super(key);
	}
	public HomeData() {
		super(MainPmkUtils.getHomeKey());
	}
	
	private List<Home> homeList;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		NBTTagList home_list = nbt.getTagList(MainPmkUtils.getHomeKey(), NBT.TAG_COMPOUND);
		for(int i = 0; i < home_list.tagCount(); i++) {
			
			NBTTagCompound tmp_nbt = home_list.getCompoundTagAt(i);
			
			int world = tmp_nbt.getInteger("world_name");
			UUID owner = tmp_nbt.getUniqueId("owner");
			String username = tmp_nbt.getString("username");
			int x = tmp_nbt.getInteger("x");
			int y = tmp_nbt.getInteger("y");
			int z = tmp_nbt.getInteger("z");
			String home_name = tmp_nbt.getString("home_name");
			
			BlockPos pos = new BlockPos(x,y,z);
			
			Home tmp_h = new Home(world, pos, owner, username, home_name);
			
			this.homeList.add(tmp_h);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		NBTTagList home_list = new NBTTagList();
		for(Home h : this.homeList) {
			
			NBTTagCompound tmp = new NBTTagCompound();
			tmp.setInteger("world_name", h.getWorld());
			tmp.setString("home_name", h.getHome_name());
			tmp.setUniqueId("owner", h.getOwner());
			tmp.setString("username", h.getUsername());
			tmp.setInteger("x", h.getPos().getX());
			tmp.setInteger("y", h.getPos().getY());
			tmp.setInteger("z", h.getPos().getZ());
			
			home_list.appendTag(tmp);
		}
		compound.setTag(MainPmkUtils.getHomeKey(), home_list);
		return compound;
	}
	
	public void removeHome(Home h) {
		
		for(int i = 0; i < this.homeList.size(); i++) {
			
			if(this.homeList.get(i).getPos().equals(h.getPos()) && this.homeList.get(i).getOwner().equals(h.getOwner()) && this.homeList.get(i).getHome_name().equals(h.getHome_name())) {
				
				this.homeList.remove(i);
				markDirty();
			}
		}
	}
	
	public void addHome(Home h) {
		
		this.homeList.add(h);
		markDirty();
	}
	
	public List<Home> getHomeByUsername(String username) {
		
		List<Home> hl = new ArrayList<Home>();
		for(int i = 0; i < this.homeList.size(); i++) {
			
			if(this.homeList.get(i).getUsername() == username) {
				
				hl.add(this.homeList.get(i));
			}
		}
		return hl;
	}
	
	public Home getHome(String homename) {
		
		for(int i = 0; i < this.homeList.size(); i++) {
			
			if(this.homeList.get(i).getHome_name().equals(homename)) {
				
				return this.homeList.get(i);
			}
		}
		return null;
	}
	
	public List<Home> getHomeList() {
		return homeList;
	}
	public void setHomeList(List<Home> homeList) {
		this.homeList = homeList;
	}

}
