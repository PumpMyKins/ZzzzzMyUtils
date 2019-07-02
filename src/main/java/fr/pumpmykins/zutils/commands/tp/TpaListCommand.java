package fr.pumpmykins.zutils.commands.tp;

import java.util.ArrayList;
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

public class TpaListCommand implements ICommand {

	private List<TpRequest> tprequest;
	
	public TpaListCommand(List<TpRequest> tprequest) {

		this.tprequest = tprequest;
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "tplist";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Syntax : /tplist";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;
			
			List<TpRequest> ltr = new ArrayList<TpRequest>();
            for(TpRequest tr : this.tprequest) {
            	
            	if(tr.getReceiver().equals(player)) {
            		
            		ltr.add(tr);
            	}
            }
			
			ITextComponent hm = new TextComponentString("Vous avez ");
			hm.setStyle(PmkStyleTable.italicBlue());
			ITextComponent hm2 = new TextComponentString(Integer.toString(ltr.size()));
			hm2.setStyle(PmkStyleTable.boldDarkRed());
			ITextComponent hm3 = new TextComponentString(" requêtes de Tpa !");
			hm3.setStyle(PmkStyleTable.italicBlue());
			hm.appendSibling(hm2);
			hm.appendSibling(hm3);
			sender.sendMessage(hm);
        	
			int i = 1;
			
			for(TpRequest tr : ltr) {
				
				
				ITextComponent it = new TextComponentString(Integer.toString(i)+".");
				it.setStyle(PmkStyleTable.italicBlue());
				ITextComponent it2 = new TextComponentString(tr.getSender().getName());
				it2.setStyle(PmkStyleTable.boldDarkRed());
				it.appendSibling(it2);
				if(tr.isHere()) {
					ITextComponent it3 = new TextComponentString(" veux vous téléporter à lui ! (");
					it3.setStyle(PmkStyleTable.italicBlue());
					it.appendSibling(it3);
				} else {
					ITextComponent it3 = new TextComponentString(" veux se téléporter à toi ! (");
					it3.setStyle(PmkStyleTable.italicBlue());
					it.appendSibling(it3);
				}
				ITextComponent it4 = new TextComponentString("Accepter");
				it4.setStyle(PmkStyleTable.boldDardGreen().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpaccept "+tr.getSender().getName())));
				ITextComponent it5 = new TextComponentString("|");
				it5.setStyle(PmkStyleTable.italicBlue());
				ITextComponent it6 = new TextComponentString("Refuser");
				it6.setStyle(PmkStyleTable.boldDarkRed().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpdeny "+tr.getSender().getName())));
				
				it.appendSibling(it4);
				it.appendSibling(it5);
				it.appendSibling(it6);
				
				sender.sendMessage(it);
				
				i++;
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
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
