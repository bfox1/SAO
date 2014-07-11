package net.teamsao.mcsao.command;

import java.util.List;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

/**
 * 
 * @author 5chris100
 *
 */

public class DebugJumpToBlock implements ICommand {

	private List aliases;
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		int x = p_71518_1_.getPlayerCoordinates().posX;
		int y = p_71518_1_.getPlayerCoordinates().posY;
		int z = p_71518_1_.getPlayerCoordinates().posZ;
		return EnumChatFormatting.GREEN + "Jumped to block!";
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
			String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
