package com.lethalmap.stardewmod.common.tiles;

import com.lethalmap.stardewmod.common.furnace.ContainerFurnace;
import com.lethalmap.stardewmod.common.furnace.TileEntityFurnace;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityList {
    public static TileEntityType<TileEntityFurnace> furnaceTile;
    public static ContainerType<ContainerFurnace> furnaceContainer;
}
