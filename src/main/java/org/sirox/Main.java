package org.sirox;

import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.MinestomAdventure;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import org.sirox.command.GamemodeCommand;
import org.sirox.event.AsyncPlayerEvent;
import org.sirox.event.BlockBreakEvent;
import org.sirox.event.PickupEvent;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init(
                new Auth.Offline()
        );

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instance = instanceManager.createInstanceContainer();

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        MinestomAdventure.AUTOMATIC_COMPONENT_TRANSLATION = true;

        loadListeners(instance);
        generateWorld(instance);
        generateLightning(instance);
        registerCommands();

        minecraftServer.start("0.0.0.0", 25565);
    }

    static void loadListeners(InstanceContainer instance) {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        new AsyncPlayerEvent(eventHandler, instance);
        new BlockBreakEvent(eventHandler);
        new PickupEvent(eventHandler);
    }

    static void generateWorld(InstanceContainer instance) {
        instance.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK);
        });
    }

    static void generateLightning(InstanceContainer instance) {
        instance.setChunkSupplier(LightingChunk::new);
    }

    static void registerCommands() {
        MinecraftServer.getCommandManager().register(
            new GamemodeCommand()
        );
    }
}