package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class CombatBoots extends ArmorItem {
    public CombatBoots() {
        super(ArmorTiers.COMBATBOOTS, EquipmentSlotType.FEET, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COMBATBOOTS);
    }
}
