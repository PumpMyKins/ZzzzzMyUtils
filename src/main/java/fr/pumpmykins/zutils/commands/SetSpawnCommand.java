package fr.pumpmykins.zutils.commands;

import java.util.Collections;
import java.util.List;

import fr.pumpmykins.zutils.MainPmkUtils.ModConfig;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.permission.PermissionAPI;

public class SetSpawnCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return "setspawn";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		
		return "Syntax : /setspawn";
	}

	@Override
	public List<String> getAliases() {
		
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {
			
			BlockPos pos = sender.getPosition();
			
			ModConfig.spawncat.setDim(sender.getEntityWorld().provider.getDimension());
			ModConfig.spawncat.setX(pos.getX());;
			ModConfig.spawncat.setY(pos.getY());;
			ModConfig.spawncat.setZ(pos.getZ());;
			
			ITextComponent hm = new TextComponentString("Le nouveau ");
			hm.setStyle(PmkStyleTable.italicBlue());
			ITextComponent hm2 = new TextComponentString("spawn");
			hm2.setStyle(PmkStyleTable.boldDarkRed());
			ITextComponent hm3 = new TextComponentString(" à été établi avec succès !");
			hm3.setStyle(PmkStyleTable.italicBlue());
			hm.appendSibling(hm2);
			hm.appendSibling(hm3);
			sender.sendMessage(hm);
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		
		return PermissionAPI.hasPermission((EntityPlayer) sender, "zutils.command.setspawn");
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
