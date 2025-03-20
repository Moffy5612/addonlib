package moffy.addonapi;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.resources.ResourceLocation;

public abstract class AddonModuleProvider {
    private Set<RawAddonModule> rawAddonModules = new HashSet<>();

    public abstract void registerRawModules();

    public void addRawModule(ResourceLocation name, String label, Class<? extends AddonModule> moduleClass, String[] requiredModIDs){
        this.rawAddonModules.add(new RawAddonModule(name, label, moduleClass, requiredModIDs));
    }

    public void addRawModule(ResourceLocation name, String label, Class<? extends AddonModule> moduleClass, String[] requiredModIDs, boolean mandatory){
        this.rawAddonModules.add(new RawAddonModule(name, label, moduleClass, requiredModIDs, mandatory));
    }

    Set<RawAddonModule> getRawAddonModules() {
        return this.rawAddonModules;
    }

    public abstract String getModId();
}
