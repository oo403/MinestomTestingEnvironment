package org.sirox.event;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class BlockBreakEvent {

    public BlockBreakEvent(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            final Player player = event.getPlayer();

            if (player.getGameMode() == GameMode.CREATIVE) return;

            final Block block = event.getBlock();
            final Pos blockPos = event.getBlockPosition().asPos();
            final Material material = block.registry().material();
            
            final ItemStack itemStack = ItemStack.of(material);

            final var instance = event.getInstance();
            final var itemEntity = new net.minestom.server.entity.ItemEntity(itemStack);
            itemEntity.setInstance(instance, blockPos.add(0.5, 0.5, 0.5));
        });
    }

}
