package fr.pumpmykins.zutils.commands.tp;

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
			
			boolean done = false;
			
			for(Home h : this.homedata.getHomeByUsername(player.getName())) {
			
				if(h.getHome_name() == homename) {

					this.homedata.removeHome(this.homedata.getHome(homename));

					done = true;
					
					break;
				}
			}
			if(done) {
				
				ITextComponent hm = new TextComponentString("Votre home : ");
				hm.setStyle(PmkStyleTable.italicBlue());
				ITextComponent hm2 = new TextComponentString(homename);
				hm2.setStyle(PmkStyleTable.boldDarkRed());
				ITextComponent hm3 = new TextComponentString(" à été supprimé avec succès !");
				hm3.setStyle(PmkStyleTable.italicBlue());
				hm.appendSibling(hm2);
				hm.appendSibling(hm3);
				sender.sendMessage(hm);
				
			} else {
				
				ITextComponent hm = new TextComponentString("Le home : ");
				hm.setStyle(PmkStyleTable.italicBlue());
				ITextComponent hm2 = new TextComponentString(homename);
				hm2.setStyle(PmkStyleTable.boldDarkRed());
				ITextComponent hm3 = new TextComponentString(" n'existe pas !");
				hm3.setStyle(PmkStyleTable.italicBlue());
				hm.appendSibling(hm2);
				hm.appendSibling(hm3);
				sender.sendMessage(hm);
				
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
