package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ToolTiers implements IItemTier {
    COPPER(2.0f, 4.0f, 200, 2, 20, ItemList.copperingot),
    STARTER(2.0f, 4.0f, -1, 2, 20, null),
    IRON(3.5f, 6.0f, 200, 2, 20, ItemList.ironingot),
    GOLD(3.5f, 6.0f, 200, 2, 20, ItemList.goldingot),
    IRIDIUM(3.5f, 6.0f, 200, 2, 20, ItemList.iridiumingot),
    GENERICSWORD(1f,1f,1,1,20,null),
    GENERICDAGGER(1f,1f,1,1,20,null);

    private float attackDamage, efficiency;
    private int durability, harvestLevel, enchantability;
    private Item repairMaterial;

    ToolTiers(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial)
    {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return durability;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairMaterial);
    }
}
