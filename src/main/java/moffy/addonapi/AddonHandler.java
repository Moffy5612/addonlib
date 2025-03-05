package moffy.addonapi;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public abstract class AddonHandler {

    public boolean isModsLoaded(){
        for(String modId: getRequiredModIDs()){
            if(!ModList.get().isLoaded(modId))return false;
        }
        return true;
    }

    public void setup(FMLCommonSetupEvent event){
       
    }

    public void enqueueIMC(InterModEnqueueEvent event)
    {

    }

    public void processIMC(InterModProcessEvent event)
    {
        
    }

    public void clientSetup(FMLClientSetupEvent event)
    {
        
    }

    public void handle(){
        AddonHandlerProvider.INSTANCE.register(this);
    }

    public abstract String[] getRequiredModIDs();
}
