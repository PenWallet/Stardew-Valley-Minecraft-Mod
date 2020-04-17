package com.lethalmap.stardewmod.common.blocks.shippingchest;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.networking.S2CCurrencyPacket;
import com.lethalmap.stardewmod.common.tiles.TileEntityList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * The container is used to link the client side gui to the server side inventory and it is where
 * you add the slots to your gui. It can also be used to sync server side data with the client but
 * that will be covered in a later tutorial
 *
 * Vanilla Containers use IInventory to communicate with the parent TileEntity:
 *  markDirty(); isUsableByPlayer(); openInventory(); closeInventory();
 * For this example, we only need markDirty() and isUsableByPlayer().  I've chosen to implement these as callback functions
 *   (lambdas) because I think it's clearer than providing an Optional<TileEntityBasic>, but it could easily be done that
 *   way as well, because the functions are only needed on the server side, when the TileEntity is available.
 *   On the client side, there is no TileEntity available.
 */
public class ShippingChestContainer extends Container {

    public static ShippingChestContainer createContainerServerSide(int windowID, PlayerInventory playerInventory, ChestContents chestContents) {
        return new ShippingChestContainer(windowID, playerInventory, chestContents);
    }

    public static ShippingChestContainer createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        //  don't need extraData for this example; if you want you can use it to provide extra information from the server, that you can use
        //  when creating the client container
        //  eg String detailedDescription = extraData.readString(128);
        ChestContents chestContents = ChestContents.createForClientSideContainer(TileEntitySellingChest.NUMBER_OF_SLOTS);

        // on the client side there is no parent TileEntity to communicate with, so we:
        // 1) use a dummy inventory
        // 2) use "do nothing" lambda functions for canPlayerAccessInventory and markDirty
        return new ShippingChestContainer(windowID, playerInventory, chestContents);
    }

    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = TileEntitySellingChest.NUMBER_OF_SLOTS;

    public static final int TILE_INVENTORY_YPOS = 20;  // the ContainerScreenBasic needs to know these so it can tell where to draw the Titles
    public static final int PLAYER_INVENTORY_YPOS = 51;

    /**
     * Creates a container suitable for server side or client side
     * @param windowID ID of the container
     * @param playerInventory the inventory of the player
     * @param chestContents the inventory stored in the chest
     */
    private ShippingChestContainer(int windowID, PlayerInventory playerInventory, ChestContents chestContents) {
        super(TileEntityList.shippingChestContainer, windowID);
        if (TileEntityList.shippingChestContainer == null)
            throw new IllegalStateException("Must initialise containerBasicContainerType before constructing a ContainerBasic!");

        PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory);  // wrap the IInventory in a Forge IItemHandler.
        // Not actually necessary - can use Slot(playerInventory) instead of SlotItemHandler(playerInventoryForge)
        this.chestContents = chestContents;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 109;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlot(new SlotItemHandler(playerInventoryForge, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        // Add the rest of the player's inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new SlotItemHandler(playerInventoryForge, slotNumber,  xpos, ypos));
            }
        }

        if (TE_INVENTORY_SLOT_COUNT != chestContents.getSizeInventory()) {
            LOGGER.warn("Mismatched slot count in ContainerBasic(" + TE_INVENTORY_SLOT_COUNT
                    + ") and TileInventory (" + chestContents.getSizeInventory()+")");
        }
        final int TILE_INVENTORY_XPOS = 8;

        // Add the tile inventory container to the gui
        addSlot(new Slot(chestContents, 0, TILE_INVENTORY_XPOS + SLOT_X_SPACING * 4, TILE_INVENTORY_YPOS) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                if(stack.getItem() instanceof IStardewItem && ((IStardewItem) stack.getItem()).canBeSold())
                    return true;

                return false;
            }
        });
    }

    // Vanilla calls this method every tick to make sure the player is still able to access the inventory, and if not closes the gui
    // Called on the SERVER side only
    @Override
    public boolean canInteractWith(PlayerEntity playerEntity)
    {
        // This is typically a check that the player is within 8 blocks of the container.
        //  Some containers perform it using just the block placement:
        //  return isWithinUsableDistance(this.iWorldPosCallable, playerIn, Blocks.MYBLOCK); eg see BeaconContainer
        //  where iWorldPosCallable is a lambda that retrieves the blockstate at a particular world blockpos
        // for other containers, it defers to the IInventory provided to the Container (i.e. the TileEntity) which does the same
        //  calculation
        // return this.furnaceInventory.isUsableByPlayer(playerEntity);
        // Sometimes it perform an additional check (eg for EnderChests - the player owns the chest)

        return chestContents.isUsableByPlayer(playerEntity);
    }

    // This is where you specify what happens when a player shift clicks a slot in the gui
    //  (when you shift click a slot in the TileEntity Inventory, it moves it to the first available position in the hotbar and/or
    //    player inventory.  When you you shift-click a hotbar or player inventory item, it moves it to the first available
    //    position in the TileEntity inventory)
    // At the very least you must override this and return ItemStack.EMPTY or the game will crash when the player shift clicks a slot
    // returns ItemStack.EMPTY if the source slot is empty, or if none of the the source slot item could be moved
    //   otherwise, returns a copy of the source stack
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerEntity, int sourceSlotIndex)
    {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        //If the user clicked on a vanilla slot
        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            //If the item he clicked can be sold
            if(sourceStack.getItem() instanceof IStardewItem && ((IStardewItem)sourceStack.getItem()).canBeSold())
            {
                Slot slotInside = inventorySlots.get(TE_INVENTORY_FIRST_SLOT_INDEX);

                //If the chest has something in the slot, then it means we already introduced something and will sell that
                if(this.inventorySlots.get(TE_INVENTORY_FIRST_SLOT_INDEX).getHasStack())
                {
                    //Get the stack we're going to sell
                    ItemStack stackInside = slotInside.getStack();

                    //Check the item inside our slot, just in case
                    if(stackInside.getItem() instanceof IStardewItem && ((IStardewItem)stackInside.getItem()).canBeSold())
                    {
                        if(!playerEntity.world.isRemote)
                        {
                            //Give the correct amount to the user
                            ICurrency currency = playerEntity.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
                            currency.addOrSubtractAmount(stackInside.getTag().getInt(Constants.SELLINGPRICE) * stackInside.getCount());
                            StardewMod.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)playerEntity), new S2CCurrencyPacket(currency.getAmount()));
                        }

                        sourceSlot.putStack(ItemStack.EMPTY);
                        slotInside.putStack(copyOfSourceStack);
                        return copyOfSourceStack;
                    }
                    else //If the user managed to get something not sellable in our slot, then just return, instead of crashing the game, that's nice
                        return ItemStack.EMPTY;
                }
                else
                {
                    //If there is no stack in our slot, then just move it there
                    sourceSlot.putStack(ItemStack.EMPTY);
                    slotInside.putStack(copyOfSourceStack);
                    return copyOfSourceStack;
                }
            }
            else //If it can't, return empty
                return ItemStack.EMPTY;
        } else if (sourceSlotIndex >= TE_INVENTORY_FIRST_SLOT_INDEX && sourceSlotIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) { //The user clicked on our slots
            //Merge stack
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else { // NANI?!
            LOGGER.warn("Invalid slotIndex:" + sourceSlotIndex);
            return ItemStack.EMPTY;
        }

        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }

    //We're only going to allow the user to interact with our items in this window
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {

        if(slotId == TE_INVENTORY_FIRST_SLOT_INDEX && !player.inventory.getItemStack().isEmpty())
        {
            Slot sellSlot = this.inventorySlots.get(TE_INVENTORY_FIRST_SLOT_INDEX);
            ItemStack sellStack = sellSlot.getStack();
            ItemStack inHandStack = player.inventory.getItemStack();

            //If the user clicked on the slot with a sellable item
            if(inHandStack.getItem() instanceof IStardewItem && ((IStardewItem) inHandStack.getItem()).canBeSold())
            {
                //If the user clicked on the slot with a sellable item AND if there's something on the slot, sell that item
                if(sellSlot.getHasStack())
                {
                    //Sell the item only on server side
                    if(!player.world.isRemote)
                    {
                        //Give the correct amount to the user
                        ICurrency currency = player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
                        currency.addOrSubtractAmount(sellStack.getTag().getInt(Constants.SELLINGPRICE) * sellStack.getCount());
                        StardewMod.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new S2CCurrencyPacket(currency.getAmount()));
                    }

                    //Empty the sell slot stack, since it's sold
                    sellSlot.putStack(ItemStack.EMPTY);
                }
            }
            else
                return ItemStack.EMPTY;
        }

        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    // pass the close container message to the parent inventory (not strictly needed for this example)
    //  see ContainerChest and TileEntityChest - used to animate the lid when no players are accessing the chest any more
    @Override
    public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);
    }

    private ChestContents chestContents;
    private static final Logger LOGGER = LogManager.getLogger();
}
