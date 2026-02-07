package org.sirox.command;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop");

        setDefaultExecutor(((sender, context) -> {
            if (sender instanceof Player player) {
                    final Player player1 = (Player) sender;

                    if ( player1.getPermissionLevel() < 4) {
                        sender.sendMessage("No Perm");
                        return;
                    }

                    MinecraftServer.stopCleanly();
                    sender.sendMessage("Shutdown");

            }
        }));
    }

}
