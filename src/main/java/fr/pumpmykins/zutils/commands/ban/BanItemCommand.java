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

public class BanItemCommand implements ICommand {

	private BanChestData banitem;

	public BanItemCommand(BanChestData banitem2) {

		this.banitem = banitem2;
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "banitem";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Syntax : /banitem | With the item in the hand";
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

			if(!player.getHeldItemMainhand().isEmpty()) {

				ItemStack hand = player.getHeldItemMainhand();
				hand.setCount(1);

				if(this.banitem.addBanItem(hand, server)) {

					ITextComponent hm = new TextComponentString("Vous avez ban l'item : ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(hand.getDisplayName());
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);

					sender.sendMessage(hm);

				} else {

					ITextComponent hm = new TextComponentString("Une erreur est survenue lors du ban de : ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(hand.getDisplayName());
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);

					sender.sendMessage(hm);
				}

			} else {

				ITextComponent hm = new TextComponentString("Vous devez tenir l'item en");
				hm.setStyle(PmkStyleTable.italicBlue());
				ITextComponent hm2 = new TextComponentString(" main");
				hm2.setStyle(PmkStyleTable.boldDarkRed());
				ITextComponent hm3 = new TextComponentString(" pour ban un Item !");
				hm3.setStyle(PmkStyleTable.italicBlue());
				hm.appendSibling(hm2);
				hm.appendSibling(hm3);

				sender.sendMessage(hm);
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return PermissionAPI.hasPermission((EntityPlayer) sender, "zutils.command.banitem") ;
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
