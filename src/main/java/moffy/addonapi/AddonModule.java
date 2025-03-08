package moffy.addonapi;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public abstract class AddonModule {
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
}
