package org.sirox.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class OpCommand extends Command {

    private final MiniMessage mm = MiniMessage.miniMessage();

    public OpCommand() {
        super("op");

        setDefaultExecutor((sender, context) -> {
            final Player player = (Player) sender;

            Component message = mm.deserialize("<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Musisz podać gracza");
            player.sendMessage(message);
        });

        var playerArgument = ArgumentType.Entity("player").onlyPlayers(true);

        addSyntax((sender, context) -> {
            final Player player = context.get(playerArgument).findFirstPlayer(sender);

            if (player != null) {
                player.setPermissionLevel(4);
            }
        }, playerArgument);
    }

}
