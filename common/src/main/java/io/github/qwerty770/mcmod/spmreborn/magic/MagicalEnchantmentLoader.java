package io.github.qwerty770.mcmod.spmreborn.magic;

import com.google.gson.*;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ParametersAreNonnullByDefault
public class MagicalEnchantmentLoader extends SimpleJsonResourceReloadListener<JsonElement> implements PreparableReloadListener {
    private static final Logger LOGGER = LoggerFactory.getLogger("MagicalEnchantmentLoader");
    private static final String folder = "spm__magical_enchantments";

    public MagicalEnchantmentLoader() {
        super(ExtraCodecs.JSON, folder);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        WeightedStatusEffect.EFFECTS.clear();
        loader.forEach((fileId, json) -> {
            JsonArray root = GsonHelper.convertToJsonArray(json, fileId.toString());
            Set<WeightedStatusEffect> set = new HashSet<>();
            int i = 0;
            for (JsonElement je: root) {
                JsonObject eachObj = GsonHelper.convertToJsonObject(je, "Element #" + i);
                ResourceLocation id = ResourceLocationTool.create(GsonHelper.getAsString(eachObj, "id"));
                if (!BuiltInRegistries.MOB_EFFECT.keySet().contains(id)) {
                    LOGGER.error("Invalid status effect id: {}", id);
                    continue;
                }
                MobEffect effect = BuiltInRegistries.MOB_EFFECT.getOptional(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Missing mob effect id: " + id));
                int duration = GsonHelper.getAsInt(eachObj, "duration", 0);
                int amplifier = GsonHelper.getAsInt(eachObj, "amplifier", 0);
                int weight = GsonHelper.getAsInt(eachObj, "weight", 1);
                int addWithPowder = GsonHelper.getAsInt(eachObj, "powder_adds", 10);
                set.add(new WeightedStatusEffect(new MobEffectInstance(Holder.direct(effect), duration, amplifier), weight, addWithPowder));
                ++i;
            } WeightedStatusEffect.EFFECTS.addAll(set);
        });
    }
}
