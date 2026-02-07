package org.sirox;

import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import org.sirox.command.GamemodeCommand;
import org.sirox.command.OpCommand;
import org.sirox.command.StopCommand;
import org.sirox.event.AsyncPlayerEvent;
import org.sirox.event.BlockBreakEvent;
import org.sirox.event.CopyBlockEvent;
import org.sirox.event.PickupEvent;
import org.sirox.util.WorldGeneratorUtil;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init(
                new Auth.Offline()
        );

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instance = instanceManager.createInstanceContainer();

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        loadListeners(instance);
        loadUtils(instance);
        registerCommands();

        minecraftServer.start("0.0.0.0", 25566);
    }

    static void loadListeners(InstanceContainer instance) {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        new AsyncPlayerEvent(eventHandler, instance);
        new BlockBreakEvent(eventHandler);
        new PickupEvent(eventHandler);
        new CopyBlockEvent(eventHandler);
    }

    static void loadUtils(InstanceContainer instance) {
        WorldGeneratorUtil.generateWorld(instance);
        WorldGeneratorUtil.saveWorld(instance);
        WorldGeneratorUtil.generateLightning(instance);
    }

    static void registerCommands() {
        MinecraftServer.getCommandManager().register(
            new GamemodeCommand(),
            new OpCommand(),
            new StopCommand()
        );
    }
}