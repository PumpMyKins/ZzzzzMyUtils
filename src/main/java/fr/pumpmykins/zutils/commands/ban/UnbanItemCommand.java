package fr.pumpmykins.zutils.commands.ban;

import java.util.Collections;
import java.util.List;

import fr.pumpmykins.zutils.utils.BanChestData;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.permission.PermissionAPI;

public class UnbanItemCommand implements ICommand {

	private BanChestData banitem;

	public UnbanItemCommand(BanChestData chest) {
		
		this.banitem = chest;
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "unbanitem";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "Syntax : /unbanitem | With the item in the hand";
	}

	@Override
	public List<String> getAliases() {
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if(sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) sender;

			if(!player.getHeldItemMainhand().isEmpty()) {


				ItemStack hand = player.getHeldItemMainhand();
				
				if(this.banitem.removeBanItem(hand, server)) {
					
					ITextComponent hm = new TextComponentString("Vous avez déban l'item ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(hand.getDisplayName());
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					
					sender.sendMessage(hm);
					
				} else {
					
					ITextComponent hm = new TextComponentString("L'item n'est pas ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString("ban");
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					
					sender.sendMessage(hm);
				}
			} 
		} else {

			ITextComponent hm = new TextComponentString("Vous devez avoir un ");
			hm.setStyle(PmkStyleTable.italicBlue());
			ITextComponent hm2 = new TextComponentString("item");
			hm2.setStyle(PmkStyleTable.boldDarkRed());
			ITextComponent hm3 = new TextComponentString(" en main pour ban un Item !");
			hm3.setStyle(PmkStyleTable.italicBlue());
			hm.appendSibling(hm2);
			hm.appendSibling(hm3);
			sender.sendMessage(hm);
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {

		return PermissionAPI.hasPermission((EntityPlayer) sender, "zutils.command.banitem") ;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return Collections.emptyList();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
