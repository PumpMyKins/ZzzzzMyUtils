package fr.pumpmykins.zutils.commands.tp;

import java.util.List;

import fr.pumpmykins.zutils.utils.Home;
import fr.pumpmykins.zutils.utils.HomeData;
import fr.pumpmykins.zutils.utils.PmkStyleTable;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.server.permission.PermissionAPI;

public class SetHomeCommand implements ICommand {

	private HomeData homedata;
	
	public SetHomeCommand(HomeData homedata) {

		this.homedata = homedata;

	}

	@Override
	public int compareTo(ICommand o) {

		return 0;
	}

	@Override
	public String getName() {

		return "sethome";
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

            int check1 = getMaxHomeT1;
            int check2 = getMaxHomeT2;
            int check3 = getMaxHomeT3;
            int check4 = getMaxHomeT0;

            String homename = "";
            boolean toset = false;

            if (args.length == 0) {

                homename = "home";

            } else {

                homename = args[0];

            }

            if (PermissionAPI.hasPermission(player, "rank.tier1")) {


                for (Home h : this.homedata.getHomeByUsername(player.getName())) {


                    if (5 >= check1) {


                        if (check1 == 5) {

                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);

                        } else if (h.getHome_name() == homename) {


                            toset = false;

                            h.setPos(player.getPosition());
                            h.setWorld(player.getEntityWorld().provider.getDimension());

                        } else {


                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);
                        }


                    } else {

                        toset = true;
                    }
                    break;
                }
            }

            if (PermissionAPI.hasPermission(player, "rank.tier2")) {


                for (Home h : this.homedata.getHomeByUsername(player.getName())) {


                    if (7 >= check2) {


                        if (check2 == 7) {

                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);

                        } else if (h.getHome_name() == homename) {


                            toset = false;

                            h.setPos(player.getPosition());
                            h.setWorld(player.getEntityWorld().provider.getDimension());

                        } else {


                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);
                        }


                    } else {

                        toset = true;
                    }
                    break;
                }


            }

            if (PermissionAPI.hasPermission(player, "rank.tier3")) {


                for (Home h : this.homedata.getHomeByUsername(player.getName())) {


                    if (5 >= check3) {


                        if(check3 == 8){

                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);

                        } else if (h.getHome_name() == homename) {


                            toset = false;

                            h.setPos(player.getPosition());
                            h.setWorld(player.getEntityWorld().provider.getDimension());

                        } else {


                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);
                        }


                    } else {

                        toset = true;
                    }
                    break;
                }


            }



                for (Home h : this.homedata.getHomeByUsername(player.getName())) {


                    if (4 >= check4) {


                        if (check4 == 4) {

                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);

                        } else if (h.getHome_name() == homename) {


                            toset = false;

                            h.setPos(player.getPosition());
                            h.setWorld(player.getEntityWorld().provider.getDimension());

                        } else {


                            ITextComponent init = new TextComponentString("Vous ne pouvez pas faire plus de home");
                            init.setStyle(PmkStyleTable.orangeBold());
                            sender.sendMessage(init);
                        }


                    } else {

                        toset = true;
                    }
                    break;
                }





            if (!toset) {
                for (Home h : this.homedata.getHomeByUsername(player.getName())) {


                    check1 = getMaxHomeT1 + 1;
                    check2 = getMaxHomeT2 + 1;
                    check3 = getMaxHomeT3 + 1;
                    check4 = getMaxHomeT0 + 1;


                    if ((PermissionAPI.hasPermission(player, "rank.tier1")) && (getMaxHomeT1 != 5)) {


                        // Set other properties
                        this.homedata.addHome(h);

                        h.setPos(player.getPosition());
                        h.setHome_name(homename);
                        h.setHome_name(homename);
                        h.setUsername(player.getName());
                        h.setOwner(player.getUniqueID());
                        h.setWorld(player.getEntityWorld().provider.getDimension());

                    }


                    if ((PermissionAPI.hasPermission(player, "rank.tier2")) && (getMaxHomeT2 != 7)) {


                        // Set other properties
                        this.homedata.addHome(h);

                        h.setPos(player.getPosition());
                        h.setHome_name(homename);
                        h.setUsername(player.getName());
                        h.setOwner(player.getUniqueID());
                        h.setWorld(player.getEntityWorld().provider.getDimension());


                    }


                    if ((PermissionAPI.hasPermission(player, "rank.tier3")) && (getMaxHomeT2 != 8)) {


                        // Set other properties
                        this.homedata.addHome(h);

                        h.setPos(player.getPosition());
                        h.setHome_name(homename);
                        h.setUsername(player.getName());
                        h.setOwner(player.getUniqueID());
                        h.setWorld(player.getEntityWorld().provider.getDimension());


                    }


                    if (getMaxHomeT2 != 4) {


                        this.homedata.addHome(h);


                        h.setPos(player.getPosition());
                        h.setHome_name(homename);
                        h.setUsername(player.getName());
                        h.setOwner(player.getUniqueID());
                        h.setWorld(player.getEntityWorld().provider.getDimension());


                        // Set other properties
                        this.homedata.addHome(h);

                        h.setPos(player.getPosition());
                        h.setHome_name(homename);
                        h.setUsername(player.getName());
                        h.setOwner(player.getUniqueID());
                        h.setWorld(player.getEntityWorld().provider.getDimension());

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
