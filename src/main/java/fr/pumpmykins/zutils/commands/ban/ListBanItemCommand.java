package fr.pumpmykins.zutils.commands.ban;

import java.util.Collections;
import java.util.List;

import fr.pumpmykins.zutils.utils.BanChestData;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class ListBanItemCommand implements ICommand {

	private BanChestData banitem;

	public ListBanItemCommand(BanChestData chest) {
		
		this.banitem = chest;
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "banitemlist";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/banitemlist";
	}

	@Override
	public List<String> getAliases() {
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		ITextComponent hm = new TextComponentString("Liste des item ");
		hm.setStyle(PmkStyleTable.italicBlue());
		ITextComponent hm2 = new TextComponentString("bannis ");
		hm2.setStyle(PmkStyleTable.boldDarkRed());
		ITextComponent hm3 = new TextComponentString(" !");
		hm3.setStyle(PmkStyleTable.italicBlue());
		hm.appendSibling(hm2);
		hm.appendSibling(hm3);
		sender.sendMessage(hm);
		
		for(ItemStack bi : this.banitem.getItemban()) {
			
			ITextComponent tc = new TextComponentString("Item : " + bi.getDisplayName());
			tc.setStyle(PmkStyleTable.italicBlue());
			
			sender.sendMessage(tc);
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {

		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {

		return Collections.emptyList();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
