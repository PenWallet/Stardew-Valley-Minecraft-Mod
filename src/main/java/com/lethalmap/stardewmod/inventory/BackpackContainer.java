package com.lethalmap.stardewmod.inventory;

import com.lethalmap.stardewmod.common.items.Backpack;
import com.lethalmap.stardewmod.init.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class BackpackContainer extends Container {
    private final ItemStack item;
    private final IItemHandler iItemHandler;
    private int blocked = -1;

    public BackpackContainer(int id, PlayerInventory playerInventory) {
        super(ModContainerTypes.backpack, id);
        this.item = getHeldItem(playerInventory.player);
        this.iItemHandler = ((Backpack) this.item.getItem()).getInventory(this.item);

        for (int i = 0; i < this.iItemHandler.getSlots(); i++) {
            int x = 8 + 18 * (i % 9);
            int y = 18 + 18 * (i / 9);
            addSlot(new SlotItemHandler(this.iItemHandler, i, x, y));
        }

        final int rowCount = this.iItemHandler.getSlots() / 9;
        final int yOffset = (rowCount - 4) * 18;

        //Player Inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + yOffset));
            }
        }

        // Hotbar
        for (int x = 0; x < 9; ++x) {
            Slot slot = addSlot(new Slot(playerInventory, x, 8 + x * 18, 161 + yOffset) {
                @Override
                public boolean canTakeStack(PlayerEntity playerIn) {
                    return slotNumber != blocked;
                }
            });

            if (x == playerInventory.currentItem && ItemStack.areItemStacksEqual(playerInventory.getCurrentItem(), this.item)) {
                blocked = slot.slotNumber;
            }
        }

    }

    private static ItemStack getHeldItem(PlayerEntity player) {
        // Determine which held item is a backpack (if either)
        if (isBackpack(player.getHeldItemMainhand())) {
            return player.getHeldItemMainhand();
        }
        if (isBackpack(player.getHeldItemOffhand())) {
            return player.getHeldItemOffhand();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        // This method handles shift-clicking to transfer items quickly. This can easily crash the game if not coded
        // correctly. The first slots (index 0 to whatever) are usually the inventory block/item, while player slots
        // start after those.
        Slot slot = this.getSlot(index);

        if (!slot.canTakeStack(playerIn)) {
            return slot.getStack();
        }

        if (index == blocked || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        int containerSlots = iItemHandler.getSlots();
        if (index < containerSlots) {
            if (!this.mergeItemStack(stack, containerSlots, this.inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.onSlotChanged();
        } else if (!this.mergeItemStack(stack, 0, containerSlots, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.onSlotChanged();
        }

        return slot.onTake(playerIn, newStack);
    }
    public int getInventoryRows() {
        return this.iItemHandler.getSlots() / 9;
    }
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (slotId < 0 || slotId > inventorySlots.size()) {
            return super.slotClick(slotId, dragType, clickTypeIn, player);
        }

        Slot slot = inventorySlots.get(slotId);
        if (!canTake(slotId, slot, dragType, player, clickTypeIn)) {
            return slot.getStack();
        }

        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
    private static boolean isBackpack(ItemStack stack) {
        return stack.getItem() instanceof Backpack;
    }

    private boolean canTake(int slotId, Slot slot, int button, PlayerEntity player, ClickType clickType) {
        if (slotId == blocked || slotId <= iItemHandler.getSlots() - 1 && isBackpack(player.inventory.getItemStack())) {
            return false;
        }

        // Hotbar swapping via number keys
        if (clickType == ClickType.SWAP) {
            int hotbarId = iItemHandler.getSlots() + 27 + button;
            // Block swapping with container
            if (blocked == hotbarId) {
                return false;
            }

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= iItemHandler.getSlots() - 1) {
                return !isBackpack(slot.getStack()) && !isBackpack(hotbarSlot.getStack());
            }
        }

        return true;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        // Save the inventory to the backpack's NBT
        ((Backpack) this.item.getItem()).saveInventory(this.item, this.iItemHandler);
    }
}
