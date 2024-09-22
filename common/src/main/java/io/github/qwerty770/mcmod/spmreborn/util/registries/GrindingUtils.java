package io.github.qwerty770.mcmod.spmreborn.util.registries;

import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagContainer;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagLike;
import it.unimi.dsi.fastutil.objects.Object2DoubleLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.OptionalDouble;

@StableApi
public final class GrindingUtils {
    public static IngredientDataMap ingredientDataMap() {
        return IngredientDataMap.INSTANCE;
    }

    private GrindingUtils() {}

    public static void registerGrindableItem(double ingredientDataAdded, @NotNull ItemLike item) {
        Objects.requireNonNull(item, "item");
        ingredientDataMap().put(item.asItem(), ingredientDataAdded);
    }

    @SuppressWarnings("unused")
    public static void registerGrindableTag(double ingredientDataAdded, @NotNull TagContainer<Item> tagContainer) {
        Objects.requireNonNull(tagContainer, "tagContainer");
        ingredientDataMap().put(tagContainer, ingredientDataAdded);
    }

    public static boolean grindable(@Nullable ItemStack itemStack) {
        if (itemStack == null)
            return false;
        return grindable(itemStack.getItem());
    }

    public static boolean grindable(@Nullable ItemLike item) {
        if (item == null)
            return false;
        return ingredientDataMap().containsItem(item.asItem());
    }

    public record IngredientDataMap(Object2DoubleMap<TagLike<Item>> map) {
        private static final IngredientDataMap INSTANCE = new IngredientDataMap();

        public IngredientDataMap() {
            this(new Object2DoubleLinkedOpenHashMap<>());
        }

        public void put(Item key, double value) {
            map.put(TagLike.asItem(key), value);
        }

        public void put(TagContainer<Item> key, double value) {
            map.put(TagLike.asTag(key), value);
        }

        private OptionalDouble get0(Item key) {
            for (var entry : map.object2DoubleEntrySet()) {
                if (entry.getKey().contains(key))
                    return OptionalDouble.of(entry.getDoubleValue());
            } return OptionalDouble.empty();
        }

        public double getDouble(Item key) {
            return get0(key).orElse(map.defaultReturnValue());
        }

        public boolean containsItem(Item key) {
            return get0(key).isPresent();
        }

        @Override
        public String toString() {
            return map.toString();
        }
    }
}
