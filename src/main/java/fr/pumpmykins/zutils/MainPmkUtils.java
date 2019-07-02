package fr.pumpmykins.zutils;

import fr.pumpmykins.zutils.commands.InventoryViewCommand;
import fr.pumpmykins.zutils.commands.SetSpawnCommand;
import fr.pumpmykins.zutils.commands.SpawnCommand;
import fr.pumpmykins.zutils.commands.ban.BanItemCommand;
import fr.pumpmykins.zutils.commands.ban.ListBanItemCommand;
import fr.pumpmykins.zutils.commands.ban.UnbanItemCommand;
import fr.pumpmykins.zutils.commands.tp.DelHomeCommand;
import fr.pumpmykins.zutils.commands.tp.HomeCommand;
import fr.pumpmykins.zutils.commands.tp.SetHomeCommand;
import fr.pumpmykins.zutils.commands.tp.TpDenyCommand;
import fr.pumpmykins.zutils.commands.tp.TpaCommand;
import fr.pumpmykins.zutils.commands.tp.TpaHereCommand;
import fr.pumpmykins.zutils.commands.tp.TpaListCommand;
import fr.pumpmykins.zutils.event.TpaEventHandler;
import fr.pumpmykins.zutils.commands.tp.TpAcceptCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;

import fr.pumpmykins.zutils.utils.BanChestData;
import fr.pumpmykins.zutils.utils.HomeData;
import fr.pumpmykins.zutils.utils.TpRequest;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.MinecraftForge;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

@Mod(useMetadata=true, modid = "zpmkutils")
public class MainPmkUtils {

	@Instance("zpmkutils")
	public static MainPmkUtils instance;

	public static MinecraftServer server;

	public static Logger logger;

	private static final String MODID = "zpmkutils";

	private static final String HOME_KEY = MODID+"_home";

	private static final String SPAWN_KEY = MODID+"_spawn";

	private static final String BANITEM_KEY = MODID+"_banitem";

	private HomeData homedata;

	private List<TpRequest> tprequest;

	private BanChestData chest;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		logger = event.getModLog();

	}



	@EventHandler
	public void init(FMLInitializationEvent event) {

		PermissionAPI.registerNode("rank.tier1", DefaultPermissionLevel.NONE, "rank.tier1");
		PermissionAPI.registerNode("rank.tier2", DefaultPermissionLevel.NONE, "rank.tier2");
		PermissionAPI.registerNode("rank.tier3", DefaultPermissionLevel.NONE, "rank.tier3");
		PermissionAPI.registerNode("zutils.command.invview", DefaultPermissionLevel.OP, "zutils.command.invview inv view permision");
		PermissionAPI.registerNode("zutils.command.setspawn", DefaultPermissionLevel.OP, "zutils.command.setspawn setspawn permission");
		PermissionAPI.registerNode("zutils.command.banitem", DefaultPermissionLevel.OP, "zutils.command.banitem banitem permission");
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		if(ModConfig.homecat.active) {

			this.homedata = getHomeData(event.getServer().getWorld(0));

			event.registerServerCommand(new HomeCommand(this.homedata));
			event.registerServerCommand(new DelHomeCommand(this.homedata));
			event.registerServerCommand(new SetHomeCommand(this.homedata));

		}
		if(ModConfig.tpacat.active) {

			this.tprequest = new ArrayList<TpRequest>();

			event.registerServerCommand(new TpAcceptCommand(this.tprequest));
			event.registerServerCommand(new TpDenyCommand(this.tprequest));
			event.registerServerCommand(new TpaCommand(this.tprequest));
			event.registerServerCommand(new TpaHereCommand(this.tprequest));
			event.registerServerCommand(new TpaListCommand(this.tprequest));

			MinecraftForge.EVENT_BUS.register(new TpaEventHandler(this.tprequest));
		}
		if(ModConfig.spawncat.active) {

			event.registerServerCommand(new SetSpawnCommand());
			event.registerServerCommand(new SpawnCommand());
		}
		if(ModConfig.staffcat.active) {

			event.registerServerCommand(new InventoryViewCommand());
		}
		if(ModConfig.banitemcat.active) {

			this.chest = getBanItemData(event.getServer().getWorld(0));
			this.chest.setItemban(getItemBan(event.getServer()));

			event.registerServerCommand(new BanItemCommand(this.chest));
			event.registerServerCommand(new ListBanItemCommand(this.chest));
			event.registerServerCommand(new UnbanItemCommand(this.chest));


		}

	}

	@SubscribeEvent
	public static void configChange(ConfigChangedEvent event) {

		if(event.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}

	@Config(modid = MODID)
	public static class ModConfig {

		@Comment("Config for the ZzzzzMyUtils Mod")

		public static ModuleHome homecat = new ModuleHome();

		public static ModuleSpawn spawncat = new ModuleSpawn();

		public static ModuleTpa tpacat = new ModuleTpa();

		public static ModuleStaff staffcat = new ModuleStaff();

		public static ModuleBanItem banitemcat = new ModuleBanItem(); 

		public static class ModuleHome {

			@Config.Name("Activer le Module Home :")
			public boolean active=true;

			@Config.RangeInt(min = 0, max = 20)
			public int homeTier0 = 2;

			@Config.RangeInt(min = 0, max = 20)
			public int homeTier1 = 4;

			@Config.RangeInt(min = 0, max = 20)
			public int homeTier2 = 6;

			@Config.RangeInt(min = 0, max = 20)
			public int homeTier3 = 10;

		}

		public static class ModuleSpawn {

			@Config.Name("Activer le Module Spawn :")
			public boolean active=true; 

			public int z = 0;
			public int y = 0;
			public int x = 0;

			public int dim = 0;

			public void setZ(int z) {
				this.z = z;
			}

			public void setY(int y) {
				this.y = y;
			}

			public void setX(int x) {
				this.x = x;
			}

			public void setDim(int dim) {
				this.dim = dim;
			}


		}

		public static class ModuleTpa {

			@Config.Name("Activer le Module Tpa :")
			public boolean active=true;

			public int expirationTime = 180000;
		}

		public static class ModuleStaff {

			@Config.Name("Activer le Module Staff :")
			public boolean active=true;
		}

		public static class ModuleBanItem {

			@Config.Name("Activer le Module BanItem :")
			public boolean active=true;

			@Config.Comment("Position du premier coffre de ban Item")
			public int z = 0;
			public int y = 5;
			public int x = 0;

			public int dim = 0;
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

	public static BanChestData getBanItemData(World w) {

		MapStorage storage = w.getMapStorage();
		BanChestData instance = (BanChestData) storage.getOrLoadData(BanChestData.class, BANITEM_KEY);
		if(instance == null) {

			instance = new BanChestData();
			storage.setData(BANITEM_KEY, instance);
		}


		return instance;
	}

	public static void setBanItemData(World w) {

		MapStorage storage = w.getMapStorage();
		BanChestData instance = (BanChestData) storage.getOrLoadData(BanChestData.class, BANITEM_KEY);
		if(instance.isDirty()) {
			storage.setData(BANITEM_KEY, instance);
		}
	}

	public List<ItemStack> getItemBan(MinecraftServer server) {

		List<ItemStack> islist = new ArrayList<ItemStack>();

		World world = server.getWorld(0);
		
		if(!this.chest.getAllChest().isEmpty()) {
			for(Iterator<BlockPos> bpiterator = this.chest.getAllChest().iterator(); bpiterator.hasNext();) {
				
				BlockPos pos = bpiterator.next();
				
				if(!world.getBlockState(pos).toString().equals("minecraft:chest")) {
					
					System.out.println("THIS BLOCK IS NOT A CHEST ! " + pos.toString());
					this.chest.removeChest(pos);
					continue;
				}
				TileEntity te = world.getTileEntity(pos);

				IItemHandler ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				for(int i = 0; i < ih.getSlots(); i++) {

					ItemStack is = ih.getStackInSlot(i);
					if(!is.isEmpty() && !islist.contains(is)) {
						islist.add(is);
					}
				}

			}
		}
		return islist;
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

	public static String getBanItemKey() {

		return BANITEM_KEY;
	}

	public static MinecraftServer getServer() {
		return server;
	}

}