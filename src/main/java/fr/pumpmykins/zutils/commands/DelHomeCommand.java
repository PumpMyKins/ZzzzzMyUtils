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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DelHomeCommand implements ICommand {

	private HomeData homedata;

	public DelHomeCommand(HomeData homedata) {

		this.homedata = homedata;
		
	}

	@Override
	public int compareTo(ICommand o) {

		
		return 0;
	}

	@Override
	public String getName() {
		
		
		
		return "delhome";
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
		
		if(sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;
			
			String homename = "";
			
			if(args.length > 0) {

				homename = args[0];
				
			} else {
					
				homename = "home";
					
			}
			
			
			for(Home h : this.homedata.getHomeByUsername(player.getName())) {
			
				if(h.getHome_name() != homename) {
					
					
					ITextComponent init = new TextComponentString("se home n'existe pas");
					init.setStyle(PmkStyleTable.orangeBold());
					sender.sendMessage(init);
			 
				}
				
				if(h.getHome_name() == homename) {

					this.homedata.removeHome(this.homedata.getHome(homename));

					
				}
			}
		}
		

	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		
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
