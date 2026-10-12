package dev.IlikeHello47.clockwork_smp.ponder;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ChunkLoaderPonder implements PonderPlugin {

    @Override
    public String getModId() {
        return Clockwork_SMP.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        ResourceLocation meinItem = ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "chunk_loader");

        helper.addStoryBoard(meinItem, "empty_scene", Storyboards::programmatischeSzene);
    }

    private static class Storyboards {

        public static void programmatischeSzene(SceneBuilder scene, SceneBuildingUtil util) {
            scene.title("chunk_loader", "Wie benutze ich den Chunk Loader");

            scene.configureBasePlate(0, 0, 5);
            scene.showBasePlate();
            scene.idle(10);

            BlockPos position1 = new BlockPos(2, 1, 2);
            BlockPos position2 = new BlockPos(2, 1, 3);

            BlockState chunkLoader = ModBlocks.CHUNK_LOADER.get().defaultBlockState();
            BlockState redstoneHebel = Blocks.LEVER.defaultBlockState();

            scene.world().setBlock(position1, chunkLoader, false);
            scene.world().setBlock(position2, redstoneHebel, false);

            scene.world().showSection(util.select().position(position1), Direction.DOWN);
            scene.idle(10);
            scene.world().showSection(util.select().position(position2), Direction.DOWN);
            scene.idle(15);

            scene.overlay().showText(60)
                    .text("Aktiviere den Chunk Loader mit einem Hebel!")
                    .pointAt(util.vector().blockSurface(position2, Direction.UP))
                    .placeNearTarget();

            scene.idle(70);
        }
    }
}
