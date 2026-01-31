package org.sirox.event;

import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.item.ItemStack;

public class PickupEvent {

    public PickupEvent(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PickupItemEvent.class, event -> {
            if (event.getLivingEntity() instanceof Player) {
                final Player player = (Player) event.getLivingEntity();
                final ItemStack itemStack = event.getItemStack();

                player.getInventory().addItemStack(itemStack);
            }
        });
    }

}
