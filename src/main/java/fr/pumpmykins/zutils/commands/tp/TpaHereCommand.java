package fr.pumpmykins.zutils.commands.tp;

import java.util.Collections;
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
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

public class TpaHereCommand implements ICommand {

	private List<TpRequest> tprequest;
	
	public TpaHereCommand(List<TpRequest> tprequest) {
		
		this.tprequest = tprequest;
	}
	
	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "tpahere";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "Syntax : /tpahere <name>";
	}

	@Override
	public List<String> getAliases() {
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (sender instanceof EntityPlayer) {

			if(args.length > 0) {

				EntityPlayer player = (EntityPlayer) sender;

				EntityPlayer receiver = server.getPlayerList().getPlayerByUsername(args[0]);

				if(receiver != null) {

					TpRequest tr = new TpRequest();
					tr.setHere(true);
					tr.setSender(player);
					tr.setReceiver(receiver);

					this.tprequest.add(tr);

					server.commandManager.executeCommand(receiver, "/tplist");
					
					ITextComponent init = new TextComponentString("Le joueur ");
					ITextComponent suite = new TextComponentString(args[0]);
					ITextComponent suite2 = new TextComponentString(" à reçu votre demande de Tpa !");
					suite.setStyle(PmkStyleTable.rougeBold());
					suite2.setStyle(PmkStyleTable.italicBlue());
					init.setStyle(PmkStyleTable.italicBlue());
					init.appendSibling(suite);
					init.appendSibling(suite2);
					
					sender.sendMessage(init);

				} else {

					ITextComponent init = new TextComponentString("Le joueur ");
					ITextComponent suite = new TextComponentString(args[0]);
					ITextComponent suite2 = new TextComponentString(" n'éxiste pas !");
					suite.setStyle(PmkStyleTable.rougeBold());
					suite2.setStyle(PmkStyleTable.orangeBold());
					init.setStyle(PmkStyleTable.orangeBold());
					init.appendSibling(suite);
					init.appendSibling(suite2);
					
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
