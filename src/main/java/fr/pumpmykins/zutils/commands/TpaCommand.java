package fr.pumpmykins.zutils.commands;

import java.util.List;

import fr.pumpmykins.zutils.utils.PmkStyleTable;
import fr.pumpmykins.zutils.utils.tpRequest;
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
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TpaCommand implements ICommand {

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

			EntityPlayer player = (EntityPlayer) sender;
			EntityPlayer receiver = null;
			String PlayerP = args[0];

			if (args.length > 0) {

				for (World w : server.worlds) {

					receiver = w.getPlayerEntityByName(args[0]);


					if (receiver == null) {


						ITextComponent init = new TextComponentString("joueur inconnue");
						init.setStyle(PmkStyleTable.orangeBold());
						sender.sendMessage(init);

						break;

					} else {


                        ITextComponent init = new TextComponentString(receiver + "souhaite se teleporter à vous ( accepter / refuser )");
                        init.setStyle(PmkStyleTable.orangeBold());
                        receiver.sendMessage(init);


                        ITextComponent tpa = new TextComponentString("accepter");
                        tpa.setStyle(PmkStyleTable.rougeBold());
                        tpa.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept" ));

                        ITextComponent tpaD = new TextComponentString("refuser");
                        tpaD.setStyle(PmkStyleTable.rougeBold());
                        tpaD.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny" ));


                        if(tpRequest.getRequest(){



                        }
					}


				}


			}


		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return false;
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
