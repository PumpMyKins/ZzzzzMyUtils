package fr.pumpmykins.zutils.utils;

import java.util.UUID;

import net.minecraft.util.math.BlockPos;

public class Home {

	private int world;
	private BlockPos pos;
	private UUID owner;
	private String username;
	private String home_name;

	
	public Home(){
		
		this.world = 0;
		this.pos = null;
		this.owner = null;
		this.username = null;
		this.home_name = null;
		
	}
	public Home(int w, BlockPos p, UUID o, String s, String hn) {
		
		this.world = w;
		this.pos = p;
		this.owner = o;
		this.username = s;
		this.home_name = hn;
	}

	public BlockPos getPos() {
		return pos;
	}
	public void setPos(BlockPos pos) {
		this.pos = pos;
	}
	public int getWorld() {
		return world;
	}
	public void setWorld(int world) {
		this.world = world;
	}
	public UUID getOwner() {
		return owner;
	}
	public void setOwner(UUID owner) {
		this.owner = owner;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHome_name() {
		return home_name;
	}
	public void setHome_name(String home_name) {
		this.home_name = home_name;
	}
	
	
}
