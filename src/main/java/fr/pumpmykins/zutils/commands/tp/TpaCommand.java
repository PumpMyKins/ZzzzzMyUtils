package fr.pumpmykins.zutils.commands.tp;

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


                        TpRequest requestP = new TpRequest();

                        requestP.setExpiration(System.currentTimeMillis());


                        ITextComponent init = new TextComponentString(receiver + "souhaite se teleporter à vous ( accepter / refuser )");
                        init.setStyle(PmkStyleTable.orangeBold());
                        receiver.sendMessage(init);


                        ITextComponent tpa = new TextComponentString("accepter");
                        tpa.setStyle(PmkStyleTable.rougeBold());
                        tpa.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept" ));

                        ITextComponent tpaD = new TextComponentString("refuser");
                        tpaD.setStyle(PmkStyleTable.rougeBold());
                        tpaD.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny" ));



                        if(requestP.getPrequest() == true){



                            if(requestP.getExpiration() <= requestP.getExpiration() + 3*60*1000){


                                ITextComponent init3 = new TextComponentString("La demande de teleportation à expiré");
                                init.setStyle(PmkStyleTable.orangeBold());
                                receiver.sendMessage(init);

                                requestP.setPrequest(false);

                            }else {

                                requestP.setP(receiver.getPosition());

                                BlockPos po = requestP.getP();
                                player.setPosition(po.getX(), po.getY(), po.getZ());
                            }



                        }else{

                            ITextComponent init2 = new TextComponentString(receiver + "à refuser la téléportation");
                            init.setStyle(PmkStyleTable.orangeBold());
                            player.sendMessage(init);


                        }
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
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
