package fr.pumpmykins.zutils.commands.tp;

import fr.pumpmykins.zutils.MainPmkUtils.ModConfig;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TpDenyCommand implements ICommand {

	private List<TpRequest> tprequest;

	public TpDenyCommand(List<TpRequest> tprequest) {

		this.tprequest = tprequest;
	}

	@Override
	public String getName() {
		return "tpdeny";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "Syntax : /tpdeny <name>";
	}

	@Override
	public List<String> getAliases() {
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		EntityPlayer player = (EntityPlayer) sender;

		List<TpRequest> ltr = new ArrayList<TpRequest>();
		for(int i = 0; i < this.tprequest.size(); i++) {

			TpRequest tr = this.tprequest.get(i);

			Date d = new Date();

			if(tr.getDate().getTime()+ModConfig.tpacat.expirationTime < d.getTime()) {

				this.tprequest.remove(i);
			}

			if(tr.getReceiver().equals(player)) {

				ltr.add(tr);
			}
		}

		if (args.length == 0) {

			if(ltr.isEmpty()) {

				ITextComponent hm = new TextComponentString("Vous avez ");
				hm.setStyle(PmkStyleTable.italicBlue());
				ITextComponent hm2 = new TextComponentString("0");
				hm2.setStyle(PmkStyleTable.boldDarkRed());
				ITextComponent hm3 = new TextComponentString(" requête de Tpa !");
				hm3.setStyle(PmkStyleTable.italicBlue());
				hm.appendSibling(hm2);
				hm.appendSibling(hm3);
				sender.sendMessage(hm);

			} else if(ltr.size() > 1) {

				server.commandManager.executeCommand(sender, "tplist");

			} else {

				for(int i = 0; i < this.tprequest.size(); i++) {

					TpRequest tr = this.tprequest.get(i);
					if(tr.getReceiver().equals(player)) {

						this.tprequest.remove(i);

						break;
					}
				}
			}
		} else {

			for(TpRequest tp : ltr) {

				if(args[0].equals(tp.getSender().getName())) {

					EntityPlayer tpreceiver = tp.getReceiver();
					EntityPlayer tpsender = tp.getSender();

					if(tp.isHere()) {

						this.tprequest.remove(0);

						ITextComponent hm = new TextComponentString("Vous avez refusé d'être tp à");
						hm.setStyle(PmkStyleTable.italicBlue());
						ITextComponent hm2 = new TextComponentString(tpsender.getName());
						hm2.setStyle(PmkStyleTable.boldDarkRed());
						ITextComponent hm3 = new TextComponentString(" !");
						hm3.setStyle(PmkStyleTable.italicBlue());
						hm.appendSibling(hm2);
						hm.appendSibling(hm3);
						tpreceiver.sendMessage(hm); 

						ITextComponent hmr = new TextComponentString(tpreceiver.getName());
						hmr.setStyle(PmkStyleTable.boldDarkRed());
						ITextComponent hmr2 = new TextComponentString(" a refusé d'être téléporté à vous !");
						hmr2.setStyle(PmkStyleTable.italicBlue());
						hmr.appendSibling(hmr2);
						tpsender.sendMessage(hmr);

					} else {

						this.tprequest.remove(0);

						ITextComponent hm = new TextComponentString("Vous avez refusé d'être tp à");
						hm.setStyle(PmkStyleTable.italicBlue());
						ITextComponent hm2 = new TextComponentString(tpreceiver.getName());
						hm2.setStyle(PmkStyleTable.boldDarkRed());
						ITextComponent hm3 = new TextComponentString(" !");
						hm3.setStyle(PmkStyleTable.italicBlue());
						hm.appendSibling(hm2);
						hm.appendSibling(hm3);
						tpreceiver.sendMessage(hm); 

						ITextComponent hmr = new TextComponentString(tpsender.getName());
						hmr.setStyle(PmkStyleTable.boldDarkRed());
						ITextComponent hmr2 = new TextComponentString(" a refusé d'être téléporté à vous !");
						hmr2.setStyle(PmkStyleTable.italicBlue());
						hmr.appendSibling(hmr2);
						tpsender.sendMessage(hmr);

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
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}


	@Override
	public int compareTo(ICommand iCommand) {
		return 0;
	}
}
