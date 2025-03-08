package moffy.addonapi;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;

public class RawAddonModule {
    private String name;
    private Class<? extends AddonModule> handlerClass;
    private String[] requiredModIds;
    private boolean required;


    public RawAddonModule(String name, Class<? extends AddonModule> handlerClass, String[] requiredModIds){
        this(name, handlerClass, requiredModIds, false);
    }

    public RawAddonModule(String name, Class<? extends AddonModule> handlerClass, String[] requiredModIds, boolean required){
        this.name = name;
        this.handlerClass = handlerClass;
        this.requiredModIds = requiredModIds;
    }

    public boolean isRequired(){
        return this.required;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Class<? extends AddonModule> getHandlerClass() {
        return handlerClass;
    }



    public void setHandlerClass(Class<? extends AddonModule> handlerClass) {
        this.handlerClass = handlerClass;
    }



    public String[] getRequiredModIds() {
        return requiredModIds;
    }

    public void setRequiredModIds(String[] requiredModIds) {
        this.requiredModIds = requiredModIds;
    }

    LazyOptional<AddonModule> loadNewModule(){
        try{
            AddonModule t = this.handlerClass.getDeclaredConstructor().newInstance();
            return LazyOptional.of(()->t);
        }catch(Exception e){
            return LazyOptional.empty();
        }
    }

    boolean isModsLoaded() {
        for(String modId: this.requiredModIds){
            if(!ModList.get().isLoaded(modId))return false;
        }
        return true;
    }
}
