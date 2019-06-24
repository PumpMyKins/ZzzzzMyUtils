package fr.pumpmykins.zutils.commands.tp;

import fr.pumpmykins.zutils.utils.PmkStyleTable;
import fr.pumpmykins.zutils.utils.TpRequest;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class TpDenyCommand implements ICommand {


    @Override
    public String getName() {
        return "tpdeny";
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

        EntityPlayer player = (EntityPlayer) sender;

        if(sender instanceof EntityPlayer){

            if(args.length == 0){

                TpRequest requestP = new TpRequest();

                requestP.setPrequest(false);

                TextComponentString init = new TextComponentString("Vous avez accepté la téléportation");
                init.setStyle(PmkStyleTable.itemList());
                sender.sendMessage(init);

            }


        }



    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }


    @Override
    public int compareTo(ICommand iCommand) {
        return 0;
    }
}
