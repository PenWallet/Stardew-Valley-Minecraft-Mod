package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.*;

public class DarkSword extends SwordItem {


    public DarkSword() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.DARKSWORD);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(Effects.WITHER,15,3));
        return true;
    }
}
