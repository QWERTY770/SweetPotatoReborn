package io.github.qwerty770.mcmod.spmreborn.util.inventory;

import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface PeelInserter {
    enum PeelActionResult {
        INSERT,
        SPAWN
    }

    static PeelActionResult insert(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack eachStack;
        int i;
        for (i = 0; i < inventory.items.size(); ++i) {
            eachStack = inventory.items.get(i);
            if (eachStack.getItem().equals(SweetPotatoItems.PEEL.get()) && eachStack.getCount() < SweetPotatoItems.PEEL.get().getMaxStackSize()) {
                eachStack.grow(1);
                return PeelActionResult.INSERT;
            }
        }
        for (i = 0; i < inventory.items.size(); ++i) {
            eachStack = inventory.items.get(i);
            if (eachStack.equals(ItemStack.EMPTY)) {
                inventory.items.set(i, new ItemStack(SweetPotatoItems.PEEL.get(), 1));
                return PeelActionResult.INSERT;
            }
        }

        return PeelActionResult.SPAWN;
    }

    static void run(Player player) {
        if (insert(player).equals(PeelActionResult.SPAWN)) {
            player.spawnAtLocation(SweetPotatoItems.PEEL.get());
        } else {
            player.getInventory().setChanged();
        }
    }
}
