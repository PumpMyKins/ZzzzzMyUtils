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
import net.minecraftforge.server.permission.PermissionAPI;

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

		if(sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) sender;
		if (args.length > 0) {



			for (Home h : this.homedata.getHomeByUsername(sender.getName())) {

				args[0] = "home";

				if (!(h.getWorld() == player.getEntityWorld().provider.getDimension())) {

					player.changeDimension(h.getWorld());
				}
				if(h.getHome_name() == args[0]) {
					BlockPos pos = h.getPos();
					player.setPosition(pos.getX(), pos.getY(), pos.getZ());
				}



			}
		} else if (args[0] == "list") {

			ITextComponent init = new TextComponentString("Liste de vos homes :");
			init.setStyle(PmkStyleTable.orangeBold());
			sender.sendMessage(init);

			for (Home h : this.homedata.getHomeByUsername(sender.getName())) {

				ITextComponent hm = new TextComponentString(h.getHome_name() + ", Dimension :" + h.getWorld());
				hm.setStyle(PmkStyleTable.itemList());
				sender.sendMessage(hm);
			}
		} else {
			for (Home h : this.homedata.getHomeByUsername(sender.getName())) {

				if (h.getHome_name() == args[0]) {

					if (!(h.getWorld() == player.getEntityWorld().provider.getDimension())) {

						player.changeDimension(h.getWorld());
					}
					BlockPos pos = h.getPos();
					player.setPosition(pos.getX(), pos.getY(), pos.getZ());


				}

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
