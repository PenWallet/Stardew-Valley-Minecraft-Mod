package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

public class CopperIngot extends Item {
    private static int nojeque = 0;
    public CopperIngot() {
        super(new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERINGOT);
    }

    /*
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.sendStatusMessage(new StringTextComponent("Prueba "+nojeque++), false);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }*/
}
