package org.sirox.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {

    private final MiniMessage mm = MiniMessage.miniMessage();

    public GamemodeCommand() {
        super("gamemode");

        setDefaultExecutor((sender, context) -> {
           final Player player = (Player) sender;

           Component message = mm.deserialize("<#AAAAAA>[<#F03C3C>✘<#AAAAAA>]");
           player.sendMessage(message);
        });

        var gamemodeArgument = ArgumentType.Word("gamemode").from("survival", "creative", "adventure", "spectator");
        var playerArgument = ArgumentType.Entity("player").onlyPlayers(true);

        addSyntax((sender, context) -> {
                final Player player = (Player) sender;

                String gamemode = context.get(gamemodeArgument);
                Component message = mm.deserialize("<#AAAAAA>[<#5DF083>✔<#AAAAAA>] Tryb gry ustawiony na: " + gamemode);

                player.sendMessage(message);

                switch(context.get(gamemodeArgument)) {
                    case "survival" -> player.setGameMode(GameMode.SURVIVAL);
                    case "creative" -> player.setGameMode(GameMode.CREATIVE);
                    case "adventure" -> player.setGameMode(GameMode.ADVENTURE);
                    case "spectator" -> player.setGameMode(GameMode.SPECTATOR);
                }
        }, gamemodeArgument);

        addSyntax((sender, context) -> {
            final Player player = context.get(playerArgument).findFirstPlayer(sender);

            if (player != null) {
                
                String gamemode = context.get(gamemodeArgument);
                String playerName = player.getUsername();
                Component senderMessage = mm.deserialize("<#AAAAAA>[<#5DF083>✔<#AAAAAA>] Tryb gry dla " + playerName + " ustawiony na: " + gamemode);
                Component playerMessage = mm.deserialize("<#AAAAAA>[<#5DF083>✔<#AAAAAA>] Tryb gry ustawiony na: " + gamemode);

                sender.sendMessage(senderMessage);
                player.sendMessage(playerMessage);

                switch(context.get(gamemodeArgument)) {
                    case "survival" -> player.setGameMode(GameMode.SURVIVAL);
                    case "creative" -> player.setGameMode(GameMode.CREATIVE);
                    case "adventure" -> player.setGameMode(GameMode.ADVENTURE);
                    case "spectator" -> player.setGameMode(GameMode.SPECTATOR);
                }
            }
        }, gamemodeArgument, playerArgument);
    }

}
