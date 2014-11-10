package net.teamsao.mcsao.event.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.world.AincradFloorSavedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 9/11/2014.
 */
public class setSkill implements ICommand {

    private List aliases;
    private int dimension;
    public setSkill(){
        this.aliases = new ArrayList();
        this.aliases.add("s");
        this.aliases.add("skill");
    }
    @Override
    public String getCommandName() {
        return "Skill";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Skill <command>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] strings) {
        EntityPlayerMP playerMP =  CommandBase.getCommandSenderAsPlayer(commandSender);

        if(strings.length == 3)
        {
            if(strings[0].equals("setlevel"))
            {
                EntityPlayer player = (EntityPlayer)playerMP;
                PlayerSAO data = PlayerSAO.get(player);
                //int lvl = Integer.parseInt(strings[1]);
                int amt = Integer.parseInt(strings[2]);
                data.setSkillLvl(strings[1], amt);
                PlayerSAO.saveProxyData(player);

                player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + "§6You set new Level!"
                ));
            }
        }
        if(strings.length == 2)
        {
            if(strings[0].equals("getLevel"))
            {
                EntityPlayer player = (EntityPlayer)playerMP;
                PlayerSAO.loadProxyData(player);
                PlayerSAO data =PlayerSAO.get(player);
                int currentLvl = data.getSkillLvl(strings[1]);
                player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + "§6Your level is!" + "§4" + currentLvl
                ));
            }
            if(strings[0].equals("checkunlocks"))
            {
                EntityPlayer player = (EntityPlayer)playerMP;
                AincradFloorSavedData data = new AincradFloorSavedData();
                data.getUnlock();
            }
            if(strings[0].equals("setunlock"))
            {
                AincradFloorSavedData data = new AincradFloorSavedData();
                NBTTagCompound compound = new NBTTagCompound();
                int number = Integer.parseInt(strings[1]);
                data.floorBossDefeat(number);
                data.markDirty();
                data.writeToNBT(compound);
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
        EntityPlayerMP playerMP =  CommandBase.getCommandSenderAsPlayer(commandSender);

        if(playerMP.capabilities.isCreativeMode)
        {
            return true;
        }
        return false;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
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
