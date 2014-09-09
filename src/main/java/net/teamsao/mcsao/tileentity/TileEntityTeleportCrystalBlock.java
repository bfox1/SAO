package net.teamsao.mcsao.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.teamsao.mcsao.helper.Reference;

public class TileEntityTeleportCrystalBlock extends TileEntity {
	
	private int id;
	
	public TileEntityTeleportCrystalBlock() {
		id = 2;
	}
	
	public void updateEntity() {
		
	}
	
	public int getID() {
		return this.id;
	}
	
	public int setID(int i) {
		return this.id = i;
	}
	
	public void addPlayerData(EntityPlayer player) {
		NBTTagCompound tag = player.getEntityData();
		
		NBTBase modeTag = tag.getTag("CanTeleportArray");
		
		if(modeTag != null) {
			int[] array = tag.getIntArray("CanTeleportArray");
			if(array[this.getID()] == 0) {
				array[this.getID()] = 1;
			}
				
		} else {
			int[] array = new int[Reference.NUMBER_OF_TOWNS];
			array[this.getID()] = 1;
			tag.setIntArray("CanTeleportArray", array);
		}
		
		NBTBase modeTag2 = tag.getTag("xPositions");
		if(modeTag2 != null) {
			int[] array = tag.getIntArray("xPositions");
			if(array[this.getID()] == 0) {
				array[this.getID()] = this.xCoord;
			}		
		} else {
			int[] array = new int[Reference.NUMBER_OF_TOWNS];
			array[this.getID()] = this.xCoord;
			tag.setIntArray("xPositions", array);
		}
		
		NBTBase modeTag3 = tag.getTag("yPositions");
		if(modeTag3 != null) {
			int[] array = tag.getIntArray("yPositions");
			if(array[this.getID()] == 0) {
				array[this.getID()] = this.yCoord;
			}		
		} else {
			int[] array = new int[Reference.NUMBER_OF_TOWNS];
			array[this.getID()] = this.yCoord + 1;
			tag.setIntArray("yPositions", array);
		}
		
		NBTBase modeTag4 = tag.getTag("zPositions");
		if(modeTag4 != null) {
			int[] array = tag.getIntArray("zPositions");
			if(array[this.getID()] == 0) {
				array[this.getID()] = this.zCoord;
			}		
		} else {
			int[] array = new int[Reference.NUMBER_OF_TOWNS];
			array[this.getID()] = this.zCoord;
			tag.setIntArray("zPositions", array);
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.id = par1NBTTagCompound.getInteger("ID");

        
    }
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("ID", this.id);
	}
	
	//Packets stuff... I can't explain this
	/*@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		readFromNBT(pkt.func_148857_g());
    }*/
}
