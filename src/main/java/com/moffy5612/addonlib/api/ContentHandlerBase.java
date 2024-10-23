package com.moffy5612.addonlib.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import com.moffy5612.addonlib.AddonLib;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public abstract class ContentHandlerBase {
    private final HashSet<Item> REG_ITEMS = new HashSet<>();
    private final HashSet<Block> REG_BLOCKS = new HashSet<>();

    public boolean isModsLoaded(){
        String[] modids = this.getRequiredModIds();
        for(String id : modids){
            if(!ModList.get().isLoaded(id))return false;
        }
        return true;
    }

    public <T> T newInstance(Class<T> contentClass, Object... args){
        try{
            if(isModsLoaded()) return contentClass.getDeclaredConstructor().newInstance(args);
            else return null;
        }catch(IllegalAccessException | InstantiationException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e){
            AddonLib.LOGGER.error(e.toString(), e);
            return null;
        }
        
    }

    public void Handle(){
        ContentHandlerProvider.INSTANCE.addContentHandler(this);
    }

    public void setup(FMLCommonSetupEvent event){
       
    }

    public void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    public void processIMC(final InterModProcessEvent event)
    {
        
    }

    public void registerItem(Item item){
        REG_ITEMS.add(item);
    }

    public HashSet<Item> getRegisteringItems(){
        return REG_ITEMS;
    }

    public void registerBlock(Block block){
        REG_BLOCKS.add(block);
        REG_ITEMS.add(block.asItem());
    }

    public HashSet<Block> getRegisteringBlocks(){
        return REG_BLOCKS;
    }

    public abstract String[] getRequiredModIds();
}
