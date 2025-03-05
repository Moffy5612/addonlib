package moffy.addonapi;

import java.util.HashSet;
import java.util.Set;

public final class AddonHandlerProvider {
    public static final AddonHandlerProvider INSTANCE = new AddonHandlerProvider();
    
    private final Set<AddonHandler> HANDLERS = new HashSet<>();

    public void register(AddonHandler handler){
        if(handler.isModsLoaded()){
            AddonHandlerProvider.INSTANCE.register(handler);
        }
    }

    public Set<AddonHandler> getHandlers(){
        return this.HANDLERS;
    }
}
