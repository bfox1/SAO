package net.teamsao.mcsao.dimension;

import net.minecraftforge.common.DimensionManager;
import net.teamsao.mcsao.SwordArtOnline;

/**
 * Dimension helper class that stores relevant starter methods and settings for the SAO dimension.
 * @author Ian
 *
 */
public class SDimension
{
	/**
	 * Will help to add more dimensions later if necessary.
	 */
	public static void registerInit()
	{
		DimensionManager.registerProviderType(SwordArtOnline.dimensionId, SAOWorldProvider.class, false);
        DimensionManager.registerDimension(SwordArtOnline.dimensionId, SwordArtOnline.dimensionId);
	}
}
