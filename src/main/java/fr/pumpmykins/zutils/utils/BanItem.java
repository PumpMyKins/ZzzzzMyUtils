package fr.pumpmykins.zutils.utils;

import net.minecraft.item.Item;

public class BanItem {

	private Item item;
	private String banreason;
	
	
	public BanItem() {
		
		
	}
	
	public BanItem(Item item, String reason) {
		
		this.item = item;
		Item.getIdFromItem(item);
		this.banreason = reason;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getBanreason() {
		return banreason;
	}

	public void setBanreason(String banreason) {
		this.banreason = banreason;
	}
	
	
}
