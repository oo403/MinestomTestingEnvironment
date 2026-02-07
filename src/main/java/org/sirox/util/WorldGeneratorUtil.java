package org.sirox.util;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGeneratorUtil {
    public static void generateWorld(InstanceContainer instance) {
        instance.setGenerator(unit -> {
            Point start = unit.absoluteStart();

            unit.modifier().fillHeight( (int) start.y(), 49, Block.DIRT);
            unit.modifier().fillHeight(49, 50, Block.GRASS_BLOCK);
        });
    }

    public static void saveWorld(InstanceContainer instance) {
        instance.setChunkLoader(new AnvilLoader("worlds/world"));
        MinecraftServer.getSchedulerManager().buildShutdownTask(instance::saveChunksToStorage);
    }

    public static void generateLightning(InstanceContainer instance) {
        instance.setChunkSupplier(LightingChunk::new);
    }
}