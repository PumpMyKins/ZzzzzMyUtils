package fr.pumpmykins.zutils.commands;

import java.util.List;

import fr.pumpmykins.zutils.utils.HomeData;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
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
	
	

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (sender instanceof Entityplayer) {

			EntityPlayer player = (EntityPlayer) sender;
			
			

			String homename = "";
			int toset = 0;
			
			if(args.length > 0) {

				homename = args[0];
								
			} else {

				homename = "home";
				
			}
			
			if(sender.hasPermission("rank.tier1")) {
				
				int check1 = checkPermission();
				
				for(Home h : this.homedata.getHomeByUsername()) {
					
					if(getHomeByUsername().Size >= check1) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
						
						
						for(Home h : this.homedata.getHomeByUsername(player.getName())) {
							
							
						if(h.getHome_name() == homename) {


							toset = 2;
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							h.setWorld() = player.getEntityWorld().provider.getDimension();
							
						}else {
							
							toset = 1;
						}
						break;
						}

					}
					break;
				}
			}
				
			if(sender.hasPermission("rank.tier2")) {
				
				int check2 = checkPermission();
				
				for(Home h : this.homedata.getHomeByUsername()) {
					
					if(getHomeByUsername().Size >= check2) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
						
						
						for(Home h : this.homedata.getHomeByUsername(player.getName())) {
							
							
						if(h.getHome_name() == homename) {


							toset = 1;
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							h.setWorld() = player.getEntityWorld().provider.getDimension();
							
						}else {
							
							toset = 2;
						}
						break;
						}	
					}
					break;
				}
	

			if(sender.hasPermission("rank.tier3")) {
				
				int check3 = checkPermission();
				
				for(Home h : this.homedata.getHomeByUsername()) {
					
					if(getHomeByUsername().Size >= check3) {
						
						ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);
						
					}else {
						
						
						for(Home h : this.homedata.getHomeByUsername(player.getName())) {
							
							
							if(h.getHome_name() == homename) {


							toset = 1;
							player.getPosition(pos.setX(), pos.setY(), pos.setZ());
							BlockPos pos = h.getPos();
							h.setWorld() = player.getEntityWorld().provider.getDimension();
							
						}else {
							
							toset = 2;
						}
							break;
						}
					}
					
					
						if(toset = 2) {
							
							for(Home h : this.homedata.getHomeByUsername(player.getName())) {
						
								Home h = new Home();
								// Set other properties 
								this.homedata.addHome(h);
						
								player.getPosition(pos.setX(), pos.setY(), pos.setZ());
								BlockPos pos = h.getPos();
								h.setHome_name() = homename;
								h.setUsername() = player;
								h.setOwner() = player.getGameProfile();
								h.setWorld() = player.getEntityWorld().provider.getDimension();
								
								break;
						
							}
						}
						break;
					}
				}
			
			
				}
			}
		}

			

			
				

			


	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if(sender instancof EntityPlayer) {
			
			if(PermissionAPI.hasPermission((EntityPlayer) sender, "rank.tier1")){
				
				
			    return 5;
			    
			    // nombre de home pour un utilisateur tier 1
			    
			} else if(PermissionAPI.hasPermission((EntityPlayer) sender, "rank.tier2"))
			
			{
			    return 7;     // nombre de home pour un utilisateur tier 2
			    
			}  else if(PermissionAPI.hasPermission((EntityPlayer) sender, "rank.tier3"))
			{
			    
				return 8;  
				// nombre de home pour un utilisateur de tier 3
			} else {
				
				return 4;     // nombre de home pour un utilisateur lambda
				
			}
		}

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
