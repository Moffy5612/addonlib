package moffy.addonapi;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AddonAPI.MODID)
public class AddonAPI {
    public static final String MODID = "addonapi";

    public AddonAPI(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        for(AddonHandler handler : AddonHandlerProvider.INSTANCE.getHandlers()){
            handler.setup(event);
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        for(AddonHandler handler : AddonHandlerProvider.INSTANCE.getHandlers()){
            handler.enqueueIMC(event);
        }
    }

    private void processIMC(final InterModProcessEvent event)
    {
        for(AddonHandler handler : AddonHandlerProvider.INSTANCE.getHandlers()){
            handler.processIMC(event);
        }
    }

    @OnlyIn(Dist.CLIENT)    
    private void clientSetup(final FMLClientSetupEvent event){
        for(AddonHandler handler : AddonHandlerProvider.INSTANCE.getHandlers()){
            handler.clientSetup(event);
        }
    }
}
