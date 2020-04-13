package com.lethalmap.stardewmod.common.items;

/* Interface that all Stardew Valley Mod items will implement so that we can know
* which items are from our mod faster. We'll just need to check:
* if(item instanceof IStardewItem)
*/
public interface IStardewItem {
    boolean canBeSold();
}
