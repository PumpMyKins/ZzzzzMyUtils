package fr.pumpmykins.zutils.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class BanItemData extends WorldSavedData {

	public BanItemData(String name) {
		super(name);
		this.banitem = new ArrayList<BanItem>();
	}

	private List<BanItem> banitem;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		
		return null;
	}

	
}
