package net.teamsao.mcsao.event.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.teamsao.mcsao.world.SAOTeleporter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bfox1
 */
public class SAOCommand extends CommandBase {



    private List aliases;
    public SAOCommand(){
        this.aliases = new ArrayList();
        this.aliases.add("SAO");
        this.aliases.add("SwordArtOnline");
    }
    @Override
    public String getCommandName() {
        return "Sao";
    }



    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Sao <command>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] strings) {

        EntityPlayerMP playerMP = strings.length == 0 ? getCommandSenderAsPlayer(commandSender) : getPlayer(commandSender, strings[0]);
        System.out.println("Made it here");

        if(strings.length == 3) {
            System.out.println("past the strings" );
            byte b0 = 1;
            int ji = b0 + 1;
            int i = 30000000;

            int j = parseIntBounded(commandSender, strings[2], -i, i);

            if (strings[1].equals("tptodimension")) {

                SAOTeleporter.tranferPlayerToDimension(playerMP, Integer.valueOf(j));
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] strings) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
