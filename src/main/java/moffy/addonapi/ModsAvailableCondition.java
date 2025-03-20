package moffy.addonapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

public class ModsAvailableCondition implements ICondition{

    private final ResourceLocation name;
    private final String[] required;

    public ModsAvailableCondition(ResourceLocation name, String[] required){
        this.name = name;
        this.required = required;
    }

    @Override
    public ResourceLocation getID() {
        return name;
    }

    @Override
    public boolean test(IContext context) {
        Map<ResourceLocation, ForgeConfigSpec.BooleanValue>compatSettings = AddonModuleRegistry.INSTANCE.getCompatSettings();
        for(String requiredModId:required){
            if(!ModList.get().isLoaded(requiredModId)){
                return false;
            }

            for(Entry<ResourceLocation, ForgeConfigSpec.BooleanValue> compatEntry : compatSettings.entrySet()){
                if(!compatEntry.getKey().getNamespace().equals(this.name.getNamespace())){
                    continue;
                }

                if(compatEntry.getKey().getPath().equals("compat_"+requiredModId) && !compatEntry.getValue().get()){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static class Serializer implements IConditionSerializer<ModsAvailableCondition>{

        private final ResourceLocation name;

        public Serializer(String modId){
            this.name = new ResourceLocation(modId, "mods_available");
        }

        @Override
        public void write(JsonObject json, ModsAvailableCondition value) {
            json.add("required", new JsonArray());
        }

        @Override
        public ModsAvailableCondition read(JsonObject json) {
            List<String> requiredModIdList = new ArrayList<>();
            JsonArray array = json.get("required").getAsJsonArray();
            array.forEach(element->{
                requiredModIdList.add(element.getAsString());
            });
            return new ModsAvailableCondition(name, requiredModIdList.stream().toArray(String[]::new));
        }

        @Override
        public ResourceLocation getID() {
            return name;
        }
    }
}
