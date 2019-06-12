package fr.pumpmykins.zutils.utils;

import java.util.UUID;

import net.minecraft.util.math.BlockPos;

public class Home {

	private int world;
	private BlockPos pos;
	private UUID owner;
	private String username;
	private String home_name;
	private int rank1;
	private int rank2;
	private int rank3;
	
	public Home(){
		
		this.world = 0;
		this.pos = null;
		this.owner = null;
		this.username = null;
		this.home_name = null;
		
	}
	public Home(int w, BlockPos p, UUID o, String s, String hn, int a, int b, int c) {
		
		this.world = w;
		this.pos = p;
		this.owner = o;
		this.username = s;
		this.home_name = hn;
		this.rank1 = a;
		this.rank2 = b;
		this.rank3 = c;
	}
	public int getRank1() {
		return rank1;
	}
	public void setRank1(int rank1) {
		this.rank1 = rank1;
	}
	public int getRank2() {
		return rank2;
	}
	public void setRank2(int rank2) {
		this.rank2 = rank2;
	}
	public int getRank3() {
		return rank3;
	}
	public void setRank3(int rank3) {
		this.rank3 = rank3;
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
