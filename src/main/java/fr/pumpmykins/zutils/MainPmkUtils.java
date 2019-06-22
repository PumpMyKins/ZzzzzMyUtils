package fr.pumpmykins.zutils;

import fr.pumpmykins.zutils.commands.*;
import org.apache.logging.log4j.Logger;

import fr.pumpmykins.zutils.utils.HomeData;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

@Mod(useMetadata=true, modid = "zpmkutils")
public class MainPmkUtils {

	@Instance("zpmkutils")
	public static MainPmkUtils instance;
	
	public static Logger logger;

	private static final String MODID = "zpmkutils";
	
	private static final String HOME_KEY = MODID+"_home";
	
	private static final String SPAWN_KEY = MODID+"_spawn";
	
	private HomeData homedata;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		logger = event.getModLog();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		PermissionAPI.registerNode("rank.tier1", DefaultPermissionLevel.OP, "rank.tier1");
		PermissionAPI.registerNode("rank.tier2", DefaultPermissionLevel.OP, "rank.tier2");
		PermissionAPI.registerNode("rank.tier3", DefaultPermissionLevel.OP, "rank.tier3");
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		
		this.homedata = getHomeData(event.getServer().getWorld(0));
		
		event.registerServerCommand(new HomeCommand(this.homedata));
		event.registerServerCommand(new DelHomeCommand(this.homedata));
		event.registerServerCommand(new TpaCommand());
		event.registerServerCommand(new TpaHereCommand());
		event.registerServerCommand(new SetHomeCommand(this.homedata));
		event.registerServerCommand(new TpacceptCommand());
	}
	
	@SubscribeEvent
	public static void configChange(ConfigChangedEvent event) {
		
		if(event.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}
	
	@SuppressWarnings("unused")
	@Config(modid = MODID)
	public static class ModConfig{
		
		@Comment("Config for the ZzzzzMyUtils Mod")
		
		public static HomeSub homecat = new HomeSub();
		private static class HomeSub {
			public boolean active=true; 
			public int quantity=2;
		}
		
		public static SpawnSub spawncat = new SpawnSub();
		private static class SpawnSub {
			public boolean active=true; 
		}
		
		public static TpaSub tpacat = new TpaSub();
		private static class TpaSub {
			public boolean active=true; 
		}
		
	}

	public static HomeData getHomeData(World w) {
		
		MapStorage storage = w.getMapStorage();
		HomeData instance = (HomeData) storage.getOrLoadData(HomeData.class, HOME_KEY);
		if(instance == null) {
			
			instance = new HomeData();
			storage.setData(HOME_KEY, instance);
		}
		return instance;
	}
	
	public static void setHomeData(World w) {
	
		MapStorage storage = w.getMapStorage();
		HomeData instance = (HomeData) storage.getOrLoadData(HomeData.class, HOME_KEY);
		if(instance.isDirty()) {
			storage.setData(HOME_KEY, instance);
		}
	}
	
	public static MainPmkUtils getInstance() {
		return instance;
	}

	public static void setInstance(MainPmkUtils instance) {
		MainPmkUtils.instance = instance;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		MainPmkUtils.logger = logger;
	}

	public static String getModid() {
		return MODID;
	}

	public static String getHomeKey() {
		return HOME_KEY;
	}

	public static String getSpawnKey() {
		return SPAWN_KEY;
	}
	
	
}