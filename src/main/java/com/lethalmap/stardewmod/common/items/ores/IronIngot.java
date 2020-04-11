package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class IronIngot extends Item {
    private static int nojeque = 0;

    public IronIngot() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRONINGOT);
    }

    /*
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.sendStatusMessage(new StringTextComponent("Prueba "+nojeque++), false);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }*/
}
