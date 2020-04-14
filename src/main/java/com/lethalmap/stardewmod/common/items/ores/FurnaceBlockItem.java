package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class FurnaceBlockItem extends BlockItem implements IStardewItem {
    public FurnaceBlockItem() {
        super(BlockList.blockfurnace, new Properties().group(Constants.SMITEMGROUP).maxStackSize(1));
        setRegistryName(Constants.MODID, Constants.FURNACE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}