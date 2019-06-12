package fr.pumpmykins.zutils.commands;

import java.util.List;

import fr.pumpmykins.zutils.utils.Home;
import fr.pumpmykins.zutils.utils.HomeData;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.server.permission.PermissionAPI;

public class SetHomeCommand implements ICommand {

	private HomeData homedata;
	
	public SetHomeCommand(HomeData homedata) {

		this.homedata = homedata;

	}

	@Override
	public int compareTo(ICommand o) {

		return 0;
	}

	@Override
	public String getName() {

		return "sethome";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return null;
	}

	@Override
	public List<String> getAliases() {

		return null;
	}
	
	@Config.RangeInt(min = 0, max = 5)
	private static int getMaxHomeT1 = 0;

	@Config.RangeInt(min = 0, max = 7)
	private static int getMaxHomeT2 = 0;

	@Config.RangeInt(min = 0, max = 8)
	private static int getMaxHomeT3 = 0;

	@Config.RangeInt(min = 0, max = 4)
	private static int getMaxHomeT0 = 0;
	
	

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) sender;
			
			int check1 = getMaxHomeT1;
			int check2 = getMaxHomeT2;
			int check3 = getMaxHomeT3;
			int check4 = getMaxHomeT0;

			String homename = "";
			boolean toset = false;
			
			if(args.length == 0) {

				homename = "home";
								
			} else {

				homename = args[0];
				
			}
			
			if(PermissionAPI.hasPermission(player, "rank.tier1")) {
				
				
				
				for(Home h : this.homedata.getHomeByUsername(player.getName())) {
					
<<<<<<< HEAD
					if(5 >= check1) {


=======
					if(getHomeByUsername().Size >= check1) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
							
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
						if(h.getHome_name() == homename) {


							toset = false;
<<<<<<< HEAD
							h.setPos(player.getPosition());
							h.setWorld(player.getEntityWorld().provider.getDimension());
						}else {


							ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
							init.setStyle(PmkStyleTable.orangeBold());
							sender.sendMessage(init);
						}

						
					}else {
=======
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							player.getEntityWorld().provider.getDimension(h.setWorld());
							
						}else {
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
							
							toset = true;
						}
						
<<<<<<< HEAD

=======
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17

				}
			}
				
<<<<<<< HEAD
			if(PermissionAPI.hasPermission(player, "rank.tier2")) {
				
				
				
				for(Home h : this.homedata.getHomeByUsername(getName())) {

					if(7 >= check2) {


=======
			if(player.hasPermission("rank.tier2")) {
				
				
				
				for(Home h : this.homedata.getHomeByUsername(getName())) {
					
					if(getHomeByUsername().Size >= check2) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
						
						
						for(Home h : this.homedata.getHomeByUsername(player.getName())) {
							
							
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
						if(h.getHome_name() == homename) {


							toset = false;
<<<<<<< HEAD
							h.setPos(player.getPosition());
							h.setWorld(player.getEntityWorld().provider.getDimension());
						}else {


							ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
							init.setStyle(PmkStyleTable.orangeBold());
							sender.sendMessage(init);
=======
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							player.getEntityWorld().provider.getDimension(h.setWorld());
							
						}else {
							
							toset = true;
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
						}


					}else {

						toset = true;
					}
				}

	

<<<<<<< HEAD
			if(PermissionAPI.hasPermission(player, "rank.tier3")) {


				for(Home h : this.homedata.getHomeByUsername(player.getName())) {

					if(8 >= check3) {


						if(h.getHome_name() == homename) {


							toset = false;
							h.setPos(player.getPosition());
							h.setWorld(player.getEntityWorld().provider.getDimension());
						}else {


							ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
							init.setStyle(PmkStyleTable.orangeBold());
							sender.sendMessage(init);
=======
			if(player.hasPermission("rank.tier3")) {
				
				
				for(Home h : this.homedata.getHomeByUsername(player.getName())) {
					
					if(getHomeByUsername().Size >= check3) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
						
						
						for(Home h : this.homedata.getHomeByUsername(player.getName())) {
							
							
							if(h.getHome_name() == homename) {


							toset = false;
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							player.getEntityWorld().provider.getDimension(h.setWorld());
							
						}else {
							
							toset = true;
						}
							break;
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
						}


					}else {

						toset = true;
					}
				}
					
					
						if(!toset) {
<<<<<<< HEAD
							
							
=======
							
							
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
							check1 = getMaxHomeT1 + 1;
							check2 = getMaxHomeT2 + 1;
                            check3 = getMaxHomeT3 + 1;
                            check4 = getMaxHomeT0 + 1;
						
<<<<<<< HEAD
                            if((PermissionAPI.hasPermission(player, "rank.tier1")) && (getMaxHomeT1 != 5)) {

								Home h = new Home();

								// Set other properties
								this.homedata.addHome(h);

								BlockPos pos = h.getPos();
								player.setPosition(pos.getX(), pos.getY(), pos.getZ());

								h.setHome_name(homename);
								h.setUsername(player.getName());
								h.setOwner(player.getUniqueID());
								h.setWorld(player.getEntityWorld().provider.getDimension());
								

                            	}

                            
                            
                            
                            if((PermissionAPI.hasPermission(player, "rank.tier2")) && (getMaxHomeT2 != 7)) {

									Home h = new Home();

									// Set other properties
									this.homedata.addHome(h);

									BlockPos pos = h.getPos();
									h.setPos(player.getPosition());

									h.setHome_name(homename);
									h.setUsername(player.getName());
									h.setOwner(player.getUniqueID());
									h.setWorld(player.getEntityWorld().provider.getDimension());
								

	
                            	}


                            
                            if((PermissionAPI.hasPermission(player, "rank.tier3")) && (getMaxHomeT2 != 8)) {
                            	


									Home h = new Home();
                            		// Set other properties 
                            		this.homedata.addHome(h);


									h.setPos(player.getPosition());

                            		h.setHome_name(homename);
                            		h.setUsername(player.getName());
                            		h.setOwner(player.getUniqueID());
									h.setWorld(player.getEntityWorld().provider.getDimension());
								

	

=======
                            if((player.hasPermission("rank.tier1")) && (getMaxHomeT1 != 5)) {
                            	
                            	
                            	for(Home h : this.homedata.getHomeByUsername(player.getName())) {
						
                            		Home h = new Home();
                            		// Set other properties 
                            		this.homedata.addHome(h);
						
                            		player.getPosition(pos.setX(), pos.setY(), pos.setZ());
                            		BlockPos pos = h.getPos();
                            		h.setHome_name(homename);
                            		player.getName(h.setUsername());
                            		player.getGameProfile(h.setOwner());
                            		player.getEntityWorld().provider.getDimension(h.setWorld());
								
                            		break;
                            	}
                            }
                            
                            
                            
                            if((player.hasPermission("rank.tier2")) && (getMaxHomeT2 != 7)) {
                            	
                            	for(Home h : this.homedata.getHomeByUsername(player.getName())){
                            		
                            		Home h = new Home();
                            		// Set other properties 
                            		this.homedata.addHome(h);
						
                            		player.getPosition(pos.setX(), pos.setY(), pos.setZ());
                            		BlockPos pos = h.getPos();
                            		h.setHome_name(homename);
                            		player.getName(h.setUsername());
                            		player.getGameProfile(h.setOwner());
                            		player.getEntityWorld().provider.getDimension(h.setWorld());
								
                            		break;
	
                            	}

                            }
                            
                            if((player.hasPermission("rank.tier3")) && (getMaxHomeT2 != 8)) {
                            	
                            	for(Home h : this.homedata.getHomeByUsername(player.getName())){
                            		
                            		Home h = new Home();
                            		// Set other properties 
                            		this.homedata.addHome(h);
						
                            		player.getPosition(pos.setX(), pos.setY(), pos.setZ());
                            		BlockPos pos = h.getPos();
                            		h.setHome_name(homename);
                            		player.getName(h.setUsername());
                            		player.getGameProfile(h.setOwner());
                            		player.getEntityWorld().provider.getDimension(h.setWorld());
								
                            		break;
	
                            	}
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17

                            }
                            
                            if(getMaxHomeT2 != 4) {
                            	
<<<<<<< HEAD


									Home h = new Home();

                            		this.homedata.addHome(h);



									h.setPos(player.getPosition());
                            		h.setHome_name(homename);
                            		h.setUsername(player.getName());
                            		h.setOwner(player.getUniqueID());
									h.setWorld(player.getEntityWorld().provider.getDimension());
								

	

=======
                            	for(Home h : this.homedata.getHomeByUsername(player.getName())){
                            		
                            		Home h = new Home();
                            		// Set other properties 
                            		this.homedata.addHome(h);
						
                            		player.getPosition(pos.setX(), pos.setY(), pos.setZ());
                            		BlockPos pos = h.getPos();
                            		h.setHome_name(homename);
                            		player.getName(h.setUsername());
                            		player.getGameProfile(h.setOwner());
                            		player.getEntityWorld().provider.getDimension(h.setWorld());
								
                            		break;
	
                            	}
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17

                            }
                            
						}
						
						
						
<<<<<<< HEAD

=======
						break;
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17
						
					}
				}
			
			
				}
			}


			

			
				

			


	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		
<<<<<<< HEAD

=======
		}
>>>>>>> 2ee064fa744b1fa5bb252a5c8b0f03611a016a17

		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {

		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {

		return false;
	}

}
