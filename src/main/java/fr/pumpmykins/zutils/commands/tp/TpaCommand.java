package fr.pumpmykins.zutils.commands.tp;

import java.util.List;

import fr.pumpmykins.zutils.utils.PmkStyleTable;
import fr.pumpmykins.zutils.utils.TpRequest;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TpaCommand implements ICommand {

	private List<TpRequest> tprequest;

	public TpaCommand(List<TpRequest> tprequest) {

		this.tprequest = tprequest;
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {

		return "tpa";
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

		if (sender instanceof EntityPlayer) {

			if(args.length > 0) {

				EntityPlayer player = (EntityPlayer) sender;

				EntityPlayer receiver = server.getPlayerList().getPlayerByUsername(args[0]);

				if(receiver != null) {

					TpRequest tr = new TpRequest();
					tr.setHere(false);
					tr.setSender(player);
					tr.setReceiver(receiver);

					this.tprequest.add(tr);

					//TODO message clickable
					ITextComponent init1 = new TextComponentString(receiver + "souhaite se teleporter a vous ( accepter / refuser )");
					init1.setStyle(PmkStyleTable.orangeBold());
					receiver.sendMessage(init1);

				} else {

					//TODO message 
					ITextComponent init = new TextComponentString("joueur inconnue");
					init.setStyle(PmkStyleTable.orangeBold());
					sender.sendMessage(init);

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
