package io.github.qwerty770.mcmod.spmreborn.world.tree;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;
import java.util.function.Supplier;

import static io.github.qwerty770.mcmod.spmreborn.world.tree.SweetPotatoTreeFeatures.*;

public class SweetPotatoTreeGrowers {
    public static final Supplier<TreeGrower> ENCHANTED_ACACIA_GROWER = () -> new TreeGrower("spr_enchanted_acacia",
            Optional.empty(), ACACIA, Optional.empty());
    public static final Supplier<TreeGrower> ENCHANTED_BIRCH_GROWER = () -> new TreeGrower("spr_enchanted_birch",
            Optional.empty(), BIRCH, BIRCH_BEES_005);
    public static final Supplier<TreeGrower> ENCHANTED_DARK_OAK_GROWER = () -> new TreeGrower("spr_enchanted_dark_oak",
            DARK_OAK,Optional.empty(), Optional.empty());
    public static final Supplier<TreeGrower> ENCHANTED_JUNGLE_GROWER = () -> new TreeGrower("spr_enchanted_jungle",
            MEGA_JUNGLE_TREE, JUNGLE_TREE_NO_VINE, Optional.empty());
    public static final Supplier<TreeGrower> ENCHANTED_OAK_GROWER = () -> new TreeGrower("spr_enchanted_oak",
            0.1f, Optional.empty(), Optional.empty(), OAK, FANCY_OAK, OAK_BEES_005, FANCY_OAK_BEES_005);
    public static final Supplier<TreeGrower> ENCHANTED_SPRUCE_GROWER = () -> new TreeGrower("spr_enchanted_spruce",
            0.5f, Optional.empty(), Optional.empty(), OAK, FANCY_OAK, OAK_BEES_005, FANCY_OAK_BEES_005);

}
