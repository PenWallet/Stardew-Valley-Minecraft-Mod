package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.inventory.BackpackContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;


public class Backpack extends Item {

    public Backpack() {
        super(new Properties().group(Constants.SMITEMGROUP).maxStackSize(1));
        setRegistryName(Constants.MODID, Constants.BACKPACK);
    }

    public void saveInventory(ItemStack stack, IItemHandler iItemHandler){
        if(iItemHandler instanceof ItemStackHandler){
            stack.getOrCreateTag().put("Inventory",((ItemStackHandler) iItemHandler).serializeNBT());
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            playerIn.openContainer(new SimpleNamedContainerProvider(
                    (id, playerInventory, player) -> new BackpackContainer(id, playerInventory),
                    new TranslationTextComponent("Backpack")
            ));
        }
        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
    public int getInventorySize(ItemStack stack){
        return 27;
    }

    public IItemHandler getInventory(ItemStack stack){
        ItemStackHandler stackHandler = new ItemStackHandler(getInventorySize(stack));
        stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound("Inventory"));
        return stackHandler;
    }
}
