package org.sirox.event;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerPacketEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.client.ClientPacket;
import net.minestom.server.network.packet.client.play.ClientPickItemFromBlockPacket;

import java.util.Arrays;

public class CopyBlockEvent {

    public CopyBlockEvent(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerPacketEvent.class, event -> {
            final ClientPacket packet = event.getPacket();
            final Player player = event.getPlayer();

            if (player.getGameMode() == GameMode.CREATIVE) {
                if (packet instanceof ClientPickItemFromBlockPacket) {
                    final Pos blockPos = ((ClientPickItemFromBlockPacket) packet).pos().asPos();

                    final Block block = player.getInstance().getBlock(blockPos);
                    final Material material = block.registry().material();
                    final ItemStack itemStack = ItemStack.of(material);

                    final PlayerInventory inventory = player.getInventory();
                    final ItemStack[] itemStacks = inventory.getItemStacks();

                    for (ItemStack item : itemStacks) {
                        if (item != null && item.material().equals(itemStack.material())) {
                            player.setHeldItemSlot( (byte) Arrays.asList(itemStacks).indexOf(item));
                            return;
                        }
                    }
                    player.setItemInMainHand(itemStack);
                }
            }

        });
    }

}
