package io.github.qwerty770.mcmod.spmreborn.client;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.GrinderScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.MagicCubeScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.SeedUpdaterScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.util.registries.InternalRegistryLogWrapper;
import net.minecraft.world.inventory.MenuType;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.extendedMenuType;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.simpleMenuType;

public class SweetPotatoMenuTypes {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("menu_types");
    // Screen Handlers
    public static final RegistrySupplier<MenuType<SeedUpdaterScreenHandler>> SEED_UPDATER_SCREEN_HANDLER_TYPE =
            simpleMenuType("seed_updater", SeedUpdaterScreenHandler::new);
    public static final RegistrySupplier<MenuType<GrinderScreenHandler>> GRINDER_SCREEN_HANDLER_TYPE =
            simpleMenuType("grinder", GrinderScreenHandler::new);
    public static final RegistrySupplier<MenuType<MagicCubeScreenHandler>> MAGIC_CUBE_SCREEN_HANDLER_TYPE =
            extendedMenuType("magic_cube", MagicCubeScreenHandler::new);
}
