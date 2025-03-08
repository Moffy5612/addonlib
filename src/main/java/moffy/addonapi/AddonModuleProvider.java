package moffy.addonapi;

import java.util.HashSet;
import java.util.Set;

public abstract class AddonModuleProvider {
    private Set<RawAddonModule> rawAddonModules = new HashSet<>();

    public abstract void registerRawModules();

    public void addRawModule(String name, Class<? extends AddonModule> moduleClass, String[] requiredModIDs){
        this.rawAddonModules.add(new RawAddonModule(name, moduleClass, requiredModIDs));
    }

    Set<RawAddonModule> getRawAddonModules() {
        return this.rawAddonModules;
    }
}
