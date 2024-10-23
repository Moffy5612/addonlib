package com.moffy5612.addonlib.events;

import com.moffy5612.addonlib.api.ContentHandlerBase;
import com.moffy5612.addonlib.api.ContentHandlerProvider;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterEvents {

    public static void onRegisterBlocks(RegistryEvent.Register<Block> event){
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            for(Block block : handler.getRegisteringBlocks()){
                event.getRegistry().register(block);
            }
        }
    }

    public static void onRegisterItems(RegistryEvent.Register<Item> event){
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            for(Item item : handler.getRegisteringItems()){
                event.getRegistry().register(item);
            }
        }
    }
}
