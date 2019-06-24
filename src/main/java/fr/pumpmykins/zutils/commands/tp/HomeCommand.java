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

public class HomeCommand implements ICommand {

	public HomeData homedata;

	public HomeCommand(HomeData homedata) {

		this.homedata = homedata;
	}

	@Override
	public int compareTo(ICommand o) {

		return 0;
	}

	@Override
	public String getName() {

		return "home";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "/home <homename>";
	}

	@Override
	public List<String> getAliases() {

		return null;
	}



	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {


		EntityPlayer player = (EntityPlayer) sender;

		if(args.length > 0) {
			if(args[0] == "list") {

				ITextComponent init = new TextComponentString("Liste de vos homes :");
				init.setStyle(PmkStyleTable.orangeBold());
				sender.sendMessage(init);

				for(Home h : this.homedata.getHomeByUsername(sender.getName())) {
					
					int i = 1;
					
					ITextComponent hm = new TextComponentString(Integer.toString(i)+".");
					hm.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm2 = new TextComponentString(h.getHome_name());
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" | Dimension : ");
					hm3.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm4 = new TextComponentString(server.getWorld(h.getWorld()).getWorldInfo().getWorldName());
					hm4.setStyle(PmkStyleTable.boldDarkRed());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					hm.appendSibling(hm4);
					
					sender.sendMessage(hm);
					
					i++;
				}

			} else {
				for(Home h : this.homedata.getHomeByUsername(sender.getName())) {

					if(h.getHome_name() == args[0]) {

						if(!(h.getWorld() == player.getEntityWorld().provider.getDimension())) {

							player.changeDimension(h.getWorld());
						}
						BlockPos pos = h.getPos();
						player.setPosition(pos.getX(), pos.getY(), pos.getZ());

						ITextComponent hm = new TextComponentString("Vous avez été téléporté au home : ");
						hm.setStyle(PmkStyleTable.italicBlue());
						ITextComponent hm2 = new TextComponentString(h.getHome_name());
						hm2.setStyle(PmkStyleTable.boldDarkRed());
						hm.appendSibling(hm2);
						
						sender.sendMessage(hm);
						
						break;
					}
				}
			}
			
		} else {
			
			for(Home h : this.homedata.getHomeByUsername(player.getName())) {
				
				if(h.getHome_name().equalsIgnoreCase("home")) {
					
					if(!(h.getWorld() == player.getEntityWorld().provider.getDimension())) {

						player.changeDimension(h.getWorld());
					}
					BlockPos pos = h.getPos();
					player.setPosition(pos.getX(), pos.getY(), pos.getZ());

					break;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
