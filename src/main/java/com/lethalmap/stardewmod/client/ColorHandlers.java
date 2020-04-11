package com.lethalmap.stardewmod.client;

import com.lethalmap.stardewmod.common.items.Backpack;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class ColorHandlers {
    public static void registerItemColor(ColorHandlerEvent.Item event){
        event.getItemColors().register(Backpack::getItemColor, ItemList.backpack);
    }
}
