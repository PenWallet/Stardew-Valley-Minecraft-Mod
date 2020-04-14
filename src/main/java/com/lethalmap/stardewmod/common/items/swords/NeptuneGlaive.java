package com.lethalmap.stardewmod.common.items.swords;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class NeptuneGlaive extends SwordItem implements IStardewItem {
    public NeptuneGlaive() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Properties().group(Constants.SMSSWORDSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.NEPTUNEGLAIVE);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 350);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}
