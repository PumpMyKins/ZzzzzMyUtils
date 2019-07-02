package fr.pumpmykins.zutils.utils;

import java.util.Date;

import net.minecraft.entity.player.EntityPlayer;

public class TpRequest {

	private boolean here;
	private EntityPlayer sender;
	private EntityPlayer receiver;
	private Date date;


	public TpRequest() {

		this.date = new Date();
	}

	public boolean isHere() {
		return here;
	}

	public void setHere(boolean here) {
		this.here = here;
	}

	public EntityPlayer getSender() {
		return sender;
	}

	public void setSender(EntityPlayer sender) {
		this.sender = sender;
	}

	public EntityPlayer getReceiver() {
		return receiver;
	}

	public void setReceiver(EntityPlayer receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
