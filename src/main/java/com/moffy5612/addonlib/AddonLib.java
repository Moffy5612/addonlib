package com.moffy5612.addonlib;

import org.slf4j.Logger;

import com.moffy5612.addonlib.api.ContentHandlerBase;
import com.moffy5612.addonlib.api.ContentHandlerProvider;
import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Reference.MOD_ID)
public class AddonLib {
    public static final Logger LOGGER = LogUtils.getLogger();

    public AddonLib()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            handler.setup(event);
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            handler.enqueueIMC(event);
        }
    }

    private void processIMC(final InterModProcessEvent event)
    {
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            handler.processIMC(event);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event){
        for(ContentHandlerBase handler : ContentHandlerProvider.INSTANCE.getHandlers()){
            handler.clientSetup(event);
        }
    }
}
