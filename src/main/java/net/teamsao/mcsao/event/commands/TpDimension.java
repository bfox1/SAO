package net.teamsao.mcsao.event.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.teamsao.mcsao.portal.SAOTeleporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 8/20/2014.
 */
public class TpDimension extends CommandBase{

    private List aliases;
    public TpDimension(){
        this.aliases = new ArrayList();
        this.aliases.add("SAO");
        this.aliases.add("SwordArtOnline");
        this.aliases.add("sao");
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
        //System.out.println("Made it here");

        if(strings.length == 3) {
          //  System.out.println("past the strings" );
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
