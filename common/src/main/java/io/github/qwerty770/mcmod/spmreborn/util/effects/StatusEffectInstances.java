package io.github.qwerty770.mcmod.spmreborn.util.effects;

import com.google.gson.JsonObject;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusEffectInstances {
    // Update to Minecraft 1.20 -- 2023/12/16
    private static final Logger LOGGER = LoggerFactory.getLogger("SPR Status Effect Manager");

    /**
     * For Enchanted Sweet Potatoes only. Not compatible with ordinal ones.
     */
    @Nullable   // null: empty status
    public static MobEffectInstance readNbt(CompoundTag tag) {
        if (!tag.contains("id", Tag.TAG_STRING)) return null;
        String raw = tag.getString("id");
        MobEffect effect = fromId(raw);
        if (effect == null) return null;
        int duration = tag.getInt("duration"), amplifier = tag.getInt("amplifier"); // defaulted as 0
        return new MobEffectInstance(effect, duration, amplifier);
    }

    private static MobEffect fromId(String raw) {
        ResourceLocation id = ResourceLocationTool.create(raw);
        if (!BuiltInRegistries.MOB_EFFECT.keySet().contains(id)) {
            LOGGER.error("Cannot apply status effect: {}", raw);
            return null;
        }
        return BuiltInRegistries.MOB_EFFECT.getOptional(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Missing mob effect id: " + id));
    }

    @Nullable
    public static MobEffectInstance readJson(JsonObject json) {
        if (!GsonHelper.isStringValue(json, "id")) {
            LOGGER.warn("Expected id as string, found {}", json.get("id"));
            return null;
        }
        String raw = GsonHelper.getAsString(json, "id");
        MobEffect effect = fromId(raw);
        if (effect == null) return null;
        int duration = GsonHelper.getAsInt(json, "duration", 0 /*sic*/);
        int amplifier = GsonHelper.getAsInt(json, "amplifier", 0);
        return new MobEffectInstance(effect, duration, amplifier);
    }

    /**
     * For Enchanted Sweet Potatoes only. Not compatible with ordinal ones.
     */
    public static CompoundTag writeNbt(MobEffectInstance effect) {
        CompoundTag tag = new CompoundTag();
        MobEffect statusEffect = effect.getEffect();
        ResourceLocation id = BuiltInRegistries.MOB_EFFECT.getKey(statusEffect);
        if (id == null) {
            LOGGER.error("Cannot write status effect: {}", statusEffect.getDisplayName());
            return tag;
        }

        tag.putString("id", id.toString());
        tag.putInt("duration", effect.getDuration());
        tag.putInt("amplifier", effect.getAmplifier());
        return tag;
    }

    public static void writeJson(JsonObject json, MobEffectInstance effect) {
        MobEffect statusEffect = effect.getEffect();
        ResourceLocation id = BuiltInRegistries.MOB_EFFECT.getKey(statusEffect);
        if (id == null) throw new IllegalArgumentException("unknown effect");
        json.addProperty("id", id.toString());
        json.addProperty("duration", effect.getDuration());
        json.addProperty("amplifier", effect.getAmplifier());
    }

    public static JsonObject writeJson(MobEffectInstance effect) {
        var obj = new JsonObject();
        writeJson(obj, effect);
        return obj;
    }
}
