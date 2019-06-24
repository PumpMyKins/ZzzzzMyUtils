package fr.pumpmykins.zutils.commands;

import java.util.List;

import com.google.common.collect.Lists;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import fr.pumpmykins.zutils.utils.TpRequest;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

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

                TpRequest requestP = new TpRequest();

                requestP.setPrequest(true);

				TextComponentString init = new TextComponentString("Vous avez accepté la téléportation");
				init.setStyle(PmkStyleTable.itemList());
				sender.sendMessage(init);
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
