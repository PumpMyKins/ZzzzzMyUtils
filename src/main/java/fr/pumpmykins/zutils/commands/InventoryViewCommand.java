package fr.pumpmykins.zutils.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.permission.PermissionAPI;

public class InventoryViewCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return "invview";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		
		return "Syntax : /invview <playername>";
	}

	@Override
	public List<String> getAliases() {
		
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
	
		if(args.length > 0) {
		
			EntityPlayerMP self = (EntityPlayerMP) sender;
			EntityPlayerMP aim = server.getPlayerList().getPlayerByUsername(args[0]);
			
			self.displayGUIChest(aim.inventory);
		} else {
			
			getUsage(sender);
		}
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		
		return PermissionAPI.hasPermission((EntityPlayer) sender, "zutils.command.invview");
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		if(args.length == 0) {
			
			return CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		
		return true;
	}

}
