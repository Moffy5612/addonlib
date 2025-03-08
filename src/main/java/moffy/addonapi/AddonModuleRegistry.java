package moffy.addonapi;

import java.util.HashSet;
import java.util.Set;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.LazyOptional;


final class AddonModuleRegistry {
    static final AddonModuleRegistry INSTANCE = new AddonModuleRegistry();

    private Set<RawAddonModule> rawModules;
    private Set<AddonModule> loadedModules;
    

    public void LoadModule(AddonModuleProvider provider, ForgeConfigSpec.Builder configBuilder){
        loadedModules = new HashSet<>();

        provider.registerRawModules();
        this.rawModules = provider.getRawAddonModules();

        configBuilder.comment("Provided by AddonAPI:", "Other Mod Compat Options").push("compat");
        for(RawAddonModule rawAddonModule : rawModules){
            boolean compatWillLoad = configBuilder.define(rawAddonModule.getName(), true).get();
            if(rawAddonModule.isModsLoaded() && compatWillLoad){
                LazyOptional<AddonModule> addonModuleOptional = rawAddonModule.loadNewModule();
                if(addonModuleOptional.isPresent()){
                    loadedModules.add(addonModuleOptional.orElse(null));
                }
            }
        }
    }

    public Set<AddonModule> getLoadedModules(){
        return this.loadedModules;
    }
}
