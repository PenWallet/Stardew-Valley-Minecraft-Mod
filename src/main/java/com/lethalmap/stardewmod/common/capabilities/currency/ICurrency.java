package com.lethalmap.stardewmod.common.capabilities.currency;

import net.minecraft.nbt.IntNBT;

public interface ICurrency {

    public int getAmount();
    public void setAmount(int amount);
    public void addOrSubtractAmount(int amount);
}
