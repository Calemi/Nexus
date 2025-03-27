package com.calemi.nexus.datagen;

import com.calemi.ccore.api.family.CBlockFamily;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Arrays;

import static com.calemi.nexus.main.NexusRef.ID;
import static com.calemi.nexus.main.NexusRef.rl;

public class NexusBlockStateProvider extends BlockStateProvider {

    public NexusBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        NexusBlockFamilies.ALL.forEach(this::family);

        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(this::nexusPortalCore);
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(this::nexusPortal);

        grass(NexusBlocks.CHRONOWARPED_GRASS, "chronowarped_dirt");
        all(NexusBlocks.CHRONOWARPED_DIRT);
        all(NexusBlocks.CHRONOWARPED_SAND);

        all(NexusBlocks.CHRONO_BLOCK);
        all(NexusBlocks.BUDDING_CHRONO);
        crystal(NexusBlocks.SMALL_CHRONO_BUD);
        crystal(NexusBlocks.MEDIUM_CHRONO_BUD);
        crystal(NexusBlocks.LARGE_CHRONO_BUD);
        crystal(NexusBlocks.CHRONO_CLUSTER);

        deepslate(NexusBlocks.WARPSLATE);

        allCutout(NexusBlocks.WARPBLOSSOM_LEAVES);

        sapling(NexusBlocks.WARPBLOSSOM_SAPLING);
        flowerPot(NexusBlocks.POTTED_WARPBLOSSOM_SAPLING, NexusBlocks.WARPBLOSSOM_SAPLING);

        petal(NexusBlocks.PURPLE_PETALS);
    }

    private void family(CBlockFamily family) {

        DeferredBlock<Block> baseBlock = family.get(CBlockFamily.Variant.BASE);
        DeferredBlock<Block> log = family.get(CBlockFamily.Variant.LOG);
        DeferredBlock<Block> wood = family.get(CBlockFamily.Variant.WOOD);
        DeferredBlock<Block> strippedLog = family.get(CBlockFamily.Variant.STRIPPED_LOG);
        DeferredBlock<Block> strippedWood = family.get(CBlockFamily.Variant.STRIPPED_WOOD);
        DeferredBlock<Block> cracked = family.get(CBlockFamily.Variant.CRACKED);
        DeferredBlock<Block> chiseled = family.get(CBlockFamily.Variant.CHISELED);
        DeferredBlock<Block> stairs = family.get(CBlockFamily.Variant.STAIRS);
        DeferredBlock<Block> slab = family.get(CBlockFamily.Variant.SLAB);
        DeferredBlock<Block> wall = family.get(CBlockFamily.Variant.WALL);
        DeferredBlock<Block> fence = family.get(CBlockFamily.Variant.FENCE);
        DeferredBlock<Block> fenceGate = family.get(CBlockFamily.Variant.FENCE_GATE);
        DeferredBlock<Block> door = family.get(CBlockFamily.Variant.DOOR);
        DeferredBlock<Block> trapDoor = family.get(CBlockFamily.Variant.TRAPDOOR);
        DeferredBlock<Block> pressurePlate = family.get(CBlockFamily.Variant.PRESSURE_PLATE);
        DeferredBlock<Block> button = family.get(CBlockFamily.Variant.BUTTON);
        DeferredBlock<Block> sign = family.get(CBlockFamily.Variant.SIGN);
        DeferredBlock<Block> wallSign = family.get(CBlockFamily.Variant.WALL_SIGN);
        DeferredBlock<Block> hangingSign = family.get(CBlockFamily.Variant.HANGING_SIGN);
        DeferredBlock<Block> wallHangingSign = family.get(CBlockFamily.Variant.WALL_HANGING_SIGN);

        if (log != null && wood != null) log(log, wood);
        if (strippedLog != null && strippedWood != null) log(strippedLog, strippedWood);
        if (baseBlock != null) all(baseBlock);
        if (cracked != null) all(cracked);
        if (chiseled != null) all(chiseled);
        if (stairs != null) stairs(stairs, baseBlock);
        if (slab != null) slab(slab, baseBlock);
        if (wall != null) wall(wall, baseBlock);
        if (fence != null) fence(fence, baseBlock);
        if (fenceGate != null) fenceGate(fenceGate, baseBlock);
        if (door != null) door(door);
        if (trapDoor != null) trapDoor(trapDoor);
        if (pressurePlate != null) pressurePlate(pressurePlate, baseBlock);
        if (button != null) button(button, baseBlock);
        if (sign != null) sign(sign, wallSign, baseBlock);
        if (hangingSign != null) hangingSign(hangingSign, wallHangingSign, baseBlock);
    }

    private void all(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeAll(blockName, rl(blockPath));

        simpleBlockWithItem(block, model);
    }

    private void allCutout(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeAll(blockName, rl(blockPath)).renderType("cutout");

        simpleBlockWithItem(block, model);
    }

    private void sapling(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = models().cross(blockName, rl(blockPath)).renderType("cutout");

        simpleBlock(block, model);
        flatItem(blockName, "block");
    }

    private void log(DeferredBlock<?> deferredLog, DeferredBlock<?> deferredWood) {

        RotatedPillarBlock log = (RotatedPillarBlock) deferredLog.get();
        RotatedPillarBlock wood = (RotatedPillarBlock) deferredWood.get();

        String logName = name(log);
        String woodName = name(wood);

        String logPath = "block/" + logName;

        ModelFile logModel = models().cubeColumn(logName, rl(logPath), rl(logPath + "_top"));
        ModelFile logHorizontalModel = models().cubeColumn(logName + "_horizontal", rl(logPath), rl(logPath + "_top"));

        ModelFile woodModel = models().cubeColumn(woodName, rl(logPath), rl(logPath));

        axisBlock(log, logModel, logHorizontalModel);
        simpleBlockItem(log, logModel);

        axisBlock(wood, woodModel, woodModel);
        simpleBlockItem(wood, woodModel);
    }

    private void stairs(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        StairBlock block = (StairBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().stairs(blockName, blockMaterialRL, blockMaterialRL, blockMaterialRL);

        stairsBlock(block, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void slab(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        SlabBlock block = (SlabBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().slab(blockName, blockMaterialRL, blockMaterialRL, blockMaterialRL);

        slabBlock(block, blockMaterialRL, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void wall(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        WallBlock block = (WallBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().wallInventory(blockName, blockMaterialRL);

        wallBlock(block, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void fence(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        FenceBlock block = (FenceBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().fenceInventory(blockName, blockMaterialRL);

        fenceBlock(block, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void fenceGate(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        FenceGateBlock block = (FenceGateBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().fenceGate(blockName, blockMaterialRL);

        fenceGateBlock(block, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void door(DeferredBlock<?> deferredBlock) {

        DoorBlock block = (DoorBlock) deferredBlock.get();

        String blockName = name(block);

        String blockPath = "block/" + blockName;
        ResourceLocation bottomBlockRL = rl(blockPath + "_bottom");
        ResourceLocation topBlockRL = rl(blockPath + "_top");

        doorBlockWithRenderType(block, bottomBlockRL, topBlockRL, "cutout");
        flatItem(blockName, "item");
    }

    private void trapDoor(DeferredBlock<?> deferredBlock) {

        TrapDoorBlock block = (TrapDoorBlock) deferredBlock.get();

        String blockName = name(block);

        String blockPath = "block/" + blockName;
        ResourceLocation blockRL = rl(blockPath);

        ModelFile itemModel = models().trapdoorBottom(blockName, blockRL);

        trapdoorBlockWithRenderType(block, blockRL, true, "cutout");
        simpleBlockItem(block, itemModel);
    }

    private void pressurePlate(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        PressurePlateBlock block = (PressurePlateBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        ModelFile itemModel = models().pressurePlate(blockName, blockMaterialRL);

        pressurePlateBlock(block, blockMaterialRL);
        simpleBlockItem(block, itemModel);
    }

    private void button(DeferredBlock<?> deferredBlock, DeferredBlock<?> deferredBlockMaterial) {

        ButtonBlock block = (ButtonBlock) deferredBlock.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockName = name(block);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        buttonBlock(block, blockMaterialRL);

        ModelFile itemModel = models().buttonInventory(blockName + "_inventory", blockMaterialRL);

        simpleBlockItem(block, itemModel);
    }

    private void sign(DeferredBlock<?> deferredBlockSign, DeferredBlock<?> deferredBlockWallSign, DeferredBlock<?> deferredBlockMaterial) {

        StandingSignBlock blockSign = (StandingSignBlock) deferredBlockSign.get();
        WallSignBlock blockWallSign = (WallSignBlock) deferredBlockWallSign.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockSignName = name(blockSign);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        signBlock(blockSign, blockWallSign, blockMaterialRL);
        flatItem(blockSignName, "item");
    }

    private void hangingSign(DeferredBlock<?> deferredBlockHangingSign, DeferredBlock<?> deferredBlockHangingWallSign, DeferredBlock<?> deferredBlockMaterial) {

        CeilingHangingSignBlock blockHangingSign = (CeilingHangingSignBlock) deferredBlockHangingSign.get();
        WallHangingSignBlock blockHangingWallSign = (WallHangingSignBlock) deferredBlockHangingWallSign.get();
        Block blockMaterial = deferredBlockMaterial.get();

        String blockHangingSignName = name(blockHangingSign);
        String blockMaterialName = name(blockMaterial);

        String blockMaterialPath = "block/" + blockMaterialName;
        ResourceLocation blockMaterialRL = rl(blockMaterialPath);

        hangingSignBlock(blockHangingSign, blockHangingWallSign, blockMaterialRL);

        flatItem(blockHangingSignName, "item");
    }

    private void deepslate(DeferredBlock<?> deferredBlock) {

        RotatedPillarBlock block = (RotatedPillarBlock) deferredBlock.get();
        String blockName = name(block);

        String blockSidePath = "block/" + blockName;
        String blockTopPath = "block/" + blockName + "_top";

        String parent = "block/cube_column_mirrored";

        ModelFile model = models().cubeColumn(blockName, rl(blockSidePath), rl(blockTopPath));
        ModelFile mirroredModel = models().
                withExistingParent(blockName + "_mirrored", parent)
                .texture("end", blockTopPath)
                .texture("side", blockSidePath);

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState()
                .modelFile(model).rotationX(90).rotationY(90).nextModel()
                .modelFile(mirroredModel).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState()
                .modelFile(model).nextModel()
                .modelFile(mirroredModel).nextModel()
                .modelFile(model).rotationY(180).nextModel()
                .modelFile(mirroredModel).rotationY(180).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState()
                .modelFile(model).rotationX(90).nextModel()
                .modelFile(mirroredModel).rotationX(90).nextModel()
                .modelFile(model).rotationX(90).rotationY(180).nextModel()
                .modelFile(mirroredModel).rotationX(90).rotationY(180).addModel();

        simpleBlockItem(block, model);
    }

    private void grass(DeferredBlock<?> deferredBlock, String dirtName) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        String dirtPath = "block/" + dirtName;

        ModelFile model = models().cubeBottomTop(blockName, rl(blockPath + "_side"), rl(dirtPath), rl(blockPath + "_top"));

        simpleBlockWithItem(block, model);
    }

    private void crystal(DeferredBlock<?> deferredBlock) {

        Block blockCrystal = deferredBlock.get();

        String blockCrystalName = name(blockCrystal);
        String blockCrystalPath = "block/" + blockCrystalName;

        String modelParent = "minecraft:block/cross";
        ModelFile model = models().withExistingParent(blockCrystalPath, modelParent).texture("cross", rl(blockCrystalPath)).renderType("cutout");

        getVariantBuilder(blockCrystal)
                .partialState().with(BlockStateProperties.FACING, Direction.DOWN)
                .modelForState().modelFile(model).rotationX(180).addModel()
                .partialState().with(BlockStateProperties.FACING, Direction.EAST)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(BlockStateProperties.FACING, Direction.NORTH)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(BlockStateProperties.FACING, Direction.SOUTH)
                .modelForState().modelFile(model).rotationX(90).rotationY(180).addModel()
                .partialState().with(BlockStateProperties.FACING, Direction.UP)
                .modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.FACING, Direction.WEST)
                .modelForState().modelFile(model).rotationX(90).rotationY(270).addModel();

        flatItem(blockCrystalName, "block");
    }

    private void petal(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        int[] flowerAmounts = {1, 2, 3, 4};
        ModelFile[] models = {
                petalModel(blockName, 1),
                petalModel(blockName, 2),
                petalModel(blockName, 3),
                petalModel(blockName, 4)
        };

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        for (int i = 0; i < models.length; i++) {

            for (Direction direction : directions) {

                int[] applicableAmounts = Arrays.copyOfRange(flowerAmounts, i, flowerAmounts.length);

                builder.part().modelFile(models[i])
                        .rotationY(direction.getCounterClockWise().getCounterClockWise().get2DDataValue() * 90)
                        .addModel()
                        .condition(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .condition(BlockStateProperties.FLOWER_AMOUNT, Arrays.stream(applicableAmounts).boxed().toArray(Integer[]::new))
                        .end();
            }
        }

        flatItem(blockName, "item");
    }

    private ModelFile petalModel(String blockName, int petalAmount) {
        return models().withExistingParent(blockName + "_" + petalAmount, "minecraft:block/flowerbed_" + petalAmount)
                .texture("flowerbed", "block/" + blockName)
                .texture("stem", "block/" + blockName + "_stem")
                .renderType("cutout");
    }

    private void flowerPot(DeferredBlock<?> deferredBlockPottedFlower, DeferredBlock<?> deferredBlockFlower) {

        Block blockPottedFlower = deferredBlockPottedFlower.get();
        Block blockFlower = deferredBlockFlower.get();

        String pottedFlowerName = name(blockPottedFlower);
        String flowerName = name(blockFlower);

        String pottedFlowerPath = "block/" + pottedFlowerName;
        String flowerPath = "block/" + flowerName;

        ResourceLocation flowerTextureRL = rl(flowerPath);

        String modelParent = "minecraft:block/flower_pot_cross";

        ModelFile model = models().withExistingParent(pottedFlowerPath, modelParent).texture("plant", flowerTextureRL).renderType("cutout");

        simpleBlock(blockPottedFlower, model);
    }

    private void nexusPortalCore(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeTop(blockName, rl(blockPath + "_side"), rl(blockPath + "_top"));

        getVariantBuilder(block).forAllStates(state -> {

            Direction facing = state.getValue(BlockStateProperties.FACING);
            Direction.Axis axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationX(facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(facing.getAxis().isVertical() ? (axis == Direction.Axis.Z ? 90 : 0) : (((int) facing.toYRot() + 180)) % 360)
                    .build();
        });

        simpleBlockItem(block, model);
    }

    private void nexusPortal(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;
        String parent = ID + ":block/nexus_portal";

        ModelFile model = models().withExistingParent(blockName, parent)
                .texture("particle", rl(blockPath))
                .texture("portal", rl(blockPath))
                .renderType("translucent");

        getVariantBuilder(block)
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel();
    }

    private void flatItem(String name, String textureSource) {
        itemModels().getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", NexusRef.rl(textureSource + "/" + name));
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
