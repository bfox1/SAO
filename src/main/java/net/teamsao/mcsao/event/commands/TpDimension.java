package net.teamsao.mcsao.event.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.world.SAOTeleporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 8/20/2014.
 */
public class TpDimension implements ICommand{

    private List aliases;

    private int dimension;
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
        return "Sao <playerName> <command>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] strings) {

        //EntityPlayerMP playerMP = strings.length == 0 ? CommandBase.getCommandSenderAsPlayer(commandSender) : CommandBase.getPlayer(commandSender, strings[0]);
        EntityPlayerMP playerMP = strings.length == 0 ? CommandBase.getCommandSenderAsPlayer(commandSender) : CommandBase.getPlayer(commandSender, strings[0]);

        if(strings.length !=2 && strings.length !=3)
        {
            playerMP.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + ColorHelper.RED + "Invalid Arguments!"));
            getCommandUsage(commandSender);
        }
        if(strings.length == 2) {
            // byte b0 = 1;
            // int ji = b0 + 1;
            // int i = 30000000;

            //int j = parseIntBounded(commandSender, strings[2], -i, i);

            if (strings[1].equals("tpAincrad")) {
                this.dimension = 2;
                SAOTeleporter.tranferPlayerToDimension(playerMP, this.dimension);
            }
            if (strings[1].equals("tpOverworld")) {
                this.dimension = 0;
                SAOTeleporter.tranferPlayerToDimension(playerMP, this.dimension);
            }
            if (strings[1].equals("getCol")) {
                EntityPlayer player = (EntityPlayer) playerMP;
              //  PlayerCol.loadProxyData(player);
              //  PlayerCol data = PlayerCol.get(player);
                PlayerSAO.loadProxyData(player);
                PlayerSAO data = PlayerSAO.get(player);
                int amt = data.getColAmount();
                player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + ColorHelper.YELLOW + "You have "
                        + ColorHelper.DARK_GREEN + amt + ColorHelper.YELLOW +" Col"));

            }
        }
        if(strings.length == 3)
        {
            if(strings[1].equals("setCol")) {
                EntityPlayer player = (EntityPlayer) playerMP;
               // PlayerCol data = PlayerCol.get(player);
                PlayerSAO data = PlayerSAO.get(player);
                int amt = Integer.parseInt(strings[2]);
                data.setColAmount(amt);
               // PlayerCol.saveProxyData(player);
                PlayerSAO.loadProxyData(player);
                player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + ColorHelper.YELLOW + "You now have "
                        + ColorHelper.DARK_GREEN + amt + ColorHelper.YELLOW +" Col"));
            }
            if(strings[1].equals("addCol")) {
                EntityPlayer player = (EntityPlayer) playerMP;
               // PlayerCol.loadProxyData(player);
                PlayerSAO.loadProxyData(player);
              //  PlayerCol data = PlayerCol.get(player);
                PlayerSAO data = PlayerSAO.get(player);
                int amt = Integer.parseInt(strings[2]);
                data.addCol(amt);
                int currentAmt = data.getColAmount();
               // PlayerCol.saveProxyData(player);
                PlayerSAO.saveProxyData(player);
                player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + ColorHelper.YELLOW + " You added "
                        + ColorHelper.DARK_GREEN + amt + ColorHelper.YELLOW + " Col to your account and now have "
                        + ColorHelper.DARK_GREEN + currentAmt + ColorHelper.YELLOW + " Col"));
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
