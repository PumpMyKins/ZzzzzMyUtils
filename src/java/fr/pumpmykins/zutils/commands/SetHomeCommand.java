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
			if(args.length > 0) {

				homename = args[0];
				
				
			} else {

				homename = "home";
			}
			
			boolean toset = true;
			
			for(Home h : this.homedata.getHomeByUsername(player.getName())) {

				if(h.getHome_name() == homename) {

					// Send name already use
					toset = false;
					player.getPosition(pos.setX(), pos.setY(), pos.setZ());
					BlockPos pos = h.getPos();
					h.setWorld() = player.getEntityWorld().provider.getDimension();
					
				}
			}
			
			if(toset = true) {
				
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
				
				}
			}

		}

	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {

		return false;
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
