package fr.pumpmykins.zutils.commands;

import java.util.Collections;
import java.util.List;

import fr.pumpmykins.zutils.MainPmkUtils.ModConfig;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class SpawnCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return "spawn";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		
		return "Syntax : /spawn";
	}

	@Override
	public List<String> getAliases() {
		
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;
			
			player.setPosition(ModConfig.spawncat.x, ModConfig.spawncat.y, ModConfig.spawncat.z);
			if(player.dimension != ModConfig.spawncat.dim) {
				
				player.changeDimension(ModConfig.spawncat.dim);
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
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
