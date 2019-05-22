package fr.pumpmykins.zutils.commands;

import java.util.List;

import fr.pumpmykins.zutils.utils.HomeData;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class SetHomeCommand implements ICommand {

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


			if(args.length < 0) 
			{


				if(args[0]) 
				{
					for(Home h : this.homedata.getHomeByUsername(sender.getName())) {
						

						player.setHome(set.PosX(), set.PosY(), set.PosZ());

						else if(args.length == 1) 
						
						{
							
							
							
							
						}

					}

				}

			}

		}else 
		
		{
			sender.sendMessage(Erreur);
			
			
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
