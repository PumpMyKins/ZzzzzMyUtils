package fr.pumpmykins.zutils.commands.tp;

import java.util.Date;
import java.util.ArrayList;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TpAcceptCommand implements ICommand {

	private List<TpRequest> tprequest;

	public TpAcceptCommand(List<TpRequest> tprequest) {

		this.tprequest = tprequest;
	}

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

			List<TpRequest> ltr = new ArrayList<TpRequest>();
			for(int i = 0; i < this.tprequest.size(); i++) {

				TpRequest tr = this.tprequest.get(i);
				
				Date d = new Date();
				
				if(tr.getDate().getTime()+30000 < d.getTime()) {
					
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

					TpRequest tp = ltr.get(0);

					EntityPlayer tpreceiver = tp.getReceiver();
					EntityPlayer tpsender = tp.getSender();

					if(tp.getReceiver().isEntityAlive() && tp.getSender().isEntityAlive()) {
						
						if(tp.isHere()) {


							int dim = tpsender.getEntityWorld().provider.getDimension();
							BlockPos pos = tpsender.getPosition();

							tpreceiver.changeDimension(dim);
							tpreceiver.setPosition(pos.getX(), pos.getY(), pos.getZ());

							this.tprequest.remove(0);

							ITextComponent hm = new TextComponentString("Vous avez été tp à");
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
							ITextComponent hmr2 = new TextComponentString(" a été téléporté à vous !");
							hmr2.setStyle(PmkStyleTable.italicBlue());
							hmr.appendSibling(hmr2);
							tpsender.sendMessage(hmr); 
						} else {

							int dim = tpreceiver.getEntityWorld().provider.getDimension();
							BlockPos pos = tpreceiver.getPosition();

							tpsender.changeDimension(dim);
							tpsender.setPosition(pos.getX(), pos.getY(), pos.getZ());

							this.tprequest.remove(0);
							
							ITextComponent hm = new TextComponentString("Vous avez été tp à");
							hm.setStyle(PmkStyleTable.italicBlue());
							ITextComponent hm2 = new TextComponentString(tpreceiver.getName());
							hm2.setStyle(PmkStyleTable.boldDarkRed());
							ITextComponent hm3 = new TextComponentString(" !");
							hm3.setStyle(PmkStyleTable.italicBlue());
							hm.appendSibling(hm2);
							hm.appendSibling(hm3);
							tpsender.sendMessage(hm); 
							
							ITextComponent hmr = new TextComponentString(tpsender.getName());
							hmr.setStyle(PmkStyleTable.boldDarkRed());
							ITextComponent hmr2 = new TextComponentString(" a été téléporté à vous !");
							hmr2.setStyle(PmkStyleTable.italicBlue());
							hmr.appendSibling(hmr2);
							tpreceiver.sendMessage(hmr);
							
						}
					} else {

						ITextComponent hm = new TextComponentString("Un de vous est ");
						hm.setStyle(PmkStyleTable.italicBlue());
						ITextComponent hm2 = new TextComponentString("mort");
						hm2.setStyle(PmkStyleTable.boldDarkRed());
						ITextComponent hm3 = new TextComponentString(" !");
						hm3.setStyle(PmkStyleTable.italicBlue());
						hm.appendSibling(hm2);
						hm.appendSibling(hm3);
						sender.sendMessage(hm);
					}

				}
			} else {

				for(TpRequest tp : ltr) {

					if(args[0].equals(tp.getSender().getName())) {

						EntityPlayer tpreceiver = tp.getReceiver();
						EntityPlayer tpsender = tp.getSender();
						
						if(tp.getReceiver().isEntityAlive() && tp.getSender().isEntityAlive()) {
							
							if(tp.isHere()) {


								int dim = tpsender.getEntityWorld().provider.getDimension();
								BlockPos pos = tpsender.getPosition();

								tpreceiver.changeDimension(dim);
								tpreceiver.setPosition(pos.getX(), pos.getY(), pos.getZ());

								this.tprequest.remove(0);

								ITextComponent hm = new TextComponentString("Vous avez été tp à");
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
								ITextComponent hmr2 = new TextComponentString(" a été téléporté à vous !");
								hmr2.setStyle(PmkStyleTable.italicBlue());
								hmr.appendSibling(hmr2);
								tpsender.sendMessage(hmr); 
							} else {

								int dim = tpreceiver.getEntityWorld().provider.getDimension();
								BlockPos pos = tpreceiver.getPosition();

								tpsender.changeDimension(dim);
								tpsender.setPosition(pos.getX(), pos.getY(), pos.getZ());

								this.tprequest.remove(0);
								
								ITextComponent hm = new TextComponentString("Vous avez été tp à");
								hm.setStyle(PmkStyleTable.italicBlue());
								ITextComponent hm2 = new TextComponentString(tpreceiver.getName());
								hm2.setStyle(PmkStyleTable.boldDarkRed());
								ITextComponent hm3 = new TextComponentString(" !");
								hm3.setStyle(PmkStyleTable.italicBlue());
								hm.appendSibling(hm2);
								hm.appendSibling(hm3);
								tpsender.sendMessage(hm); 
								
								ITextComponent hmr = new TextComponentString(tpsender.getName());
								hmr.setStyle(PmkStyleTable.boldDarkRed());
								ITextComponent hmr2 = new TextComponentString(" a été téléporté à vous !");
								hmr2.setStyle(PmkStyleTable.italicBlue());
								hmr.appendSibling(hmr2);
								tpreceiver.sendMessage(hmr);
								
							}
						} else {

							ITextComponent hm = new TextComponentString("Un de vous est ");
							hm.setStyle(PmkStyleTable.italicBlue());
							ITextComponent hm2 = new TextComponentString("mort");
							hm2.setStyle(PmkStyleTable.boldDarkRed());
							ITextComponent hm3 = new TextComponentString(" !");
							hm3.setStyle(PmkStyleTable.italicBlue());
							hm.appendSibling(hm2);
							hm.appendSibling(hm3);
							sender.sendMessage(hm);
						}

						break;
					}
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
