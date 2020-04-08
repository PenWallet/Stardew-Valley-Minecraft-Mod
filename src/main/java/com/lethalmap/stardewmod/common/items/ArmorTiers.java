package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorTiers implements IArmorMaterial {
    COMBATBOOTS("combatboots", 500, new int[]{1, 1, 1, 5}, 20, null, "item.armor.equip_generic", 0.0f);

    private static final int[] max_damage_array = new int[]{13, 15, 16, 11};
    private String name, equipSound;
    private int durability, enchantability;
    private Item repairItem;
    private int[] damageReductionAmounts;
    private float toughness;

    ArmorTiers(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness)
    {
        this.name = name;
        this.durability = durability;
        this.damageReductionAmounts = damageReductionAmounts;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.equipSound = equipSound;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return max_damage_array[slotIn.getIndex()] * this.durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmounts[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return new SoundEvent(new ResourceLocation(this.equipSound));
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairItem == null ? Ingredient.EMPTY : Ingredient.fromItems(this.repairItem);
    }

    @Override
    public String getName() {
        return Constants.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
