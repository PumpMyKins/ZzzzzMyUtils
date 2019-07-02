package fr.pumpmykins.zutils.commands.tp;

import java.util.Collections;
import java.util.List;

import fr.pumpmykins.zutils.MainPmkUtils.ModConfig;
import fr.pumpmykins.zutils.utils.Home;
import fr.pumpmykins.zutils.utils.HomeData;
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

		return "Syntax : /sethome <homename>";
	}

	@Override
	public List<String> getAliases() {

		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) sender;

			String homename = "";
			boolean toset = true;

			if (args.length == 0) {

				homename = "home";
			} else {

				homename = args[0];
			}

			int homelimite = ModConfig.homecat.homeTier0;

			if(PermissionAPI.hasPermission(player, "rank.tier1")) {

				homelimite = ModConfig.homecat.homeTier1;

			} else if(PermissionAPI.hasPermission(player, "rank.tier2")) {

				homelimite = ModConfig.homecat.homeTier2;

			} else if(PermissionAPI.hasPermission(player, "rank.tier3")) {

				homelimite = ModConfig.homecat.homeTier3;

			}

			for (Home h : this.homedata.getHomeByUsername(player.getName())) {

				if(h.getHome_name().equalsIgnoreCase(homename)) {

					this.homedata.removeHome(h);

					h.setWorld(player.getEntityWorld().provider.getDimension());
					h.setPos(player.getPosition());

					toset = false;

					this.homedata.addHome(h);
					
					ITextComponent hm = new TextComponentString("Votre home : ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(homename);
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" à été remplacé avec succès !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					sender.sendMessage(hm);
					
					break;
				}
			}
			if(toset) {

				int homesize = this.homedata.getHomeByUsername(player.getName()).size();
				if(!(homesize >= homelimite)) {

					Home h = new Home();
					h.setHome_name(homename);
					h.setOwner(player.getUniqueID());
					h.setPos(player.getPosition());
					h.setWorld(player.getEntityWorld().provider.getDimension());
					h.setUsername(player.getName());

					ITextComponent hm = new TextComponentString("Le home : ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(homename);
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" à été mis avec succès !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					sender.sendMessage(hm);
					
					this.homedata.addHome(h);
				} else {
					
					ITextComponent hm = new TextComponentString("Vous avez déjà : ");
					hm.setStyle(PmkStyleTable.italicBlue());
					ITextComponent hm2 = new TextComponentString(Integer.toString(homesize));
					hm2.setStyle(PmkStyleTable.boldDarkRed());
					ITextComponent hm3 = new TextComponentString(" home ce qui est votre limite !");
					hm3.setStyle(PmkStyleTable.italicBlue());
					hm.appendSibling(hm2);
					hm.appendSibling(hm3);
					sender.sendMessage(hm);
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
