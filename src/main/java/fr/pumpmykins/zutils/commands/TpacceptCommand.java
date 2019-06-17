package fr.pumpmykins.zutils.commands;

import java.util.List;

import com.google.common.collect.Lists;
import fr.pumpmykins.zutils.utils.tpRequest;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class TpacceptCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return "tpaccept";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		
		return null;
	}

	@Override
	public List<String> getAliases() {
		
		return Lists.newArrayList("tpyes");
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) sender;

			if (args.length == 0) {

                if(tpRequest.getRequest == true){



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
