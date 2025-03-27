package com.calemi.nexus.block;

import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.client.model.NexusPortalCoreBakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Most of the code for this class was taken from the OpenBlocks Elevator Mod. All credit goes to the authors of that mod.
 * <a href="https://github.com/VsnGamer/ElevatorMod/blob/1.21/src/main/java/xyz/vsngamer/elevatorid/blocks/ElevatorBlock.java">...</a>
 */
public abstract class AbstractCamoBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public AbstractCamoBlock(Properties properties) {
        super(properties);
    }

    public abstract Optional<BlockState> getCamoState(BlockGetter world, BlockPos pos);

    public abstract Optional<NexusPortalCoreBlockEntity> getPortalCore(BlockGetter world, BlockPos pos);

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockAndTintGetter level, BlockPos pos, Direction side, @Nullable BlockState queryState, @Nullable BlockPos queryPos) {

        if (level instanceof ServerLevel) {
            return getCamoState(level, pos)
                    .map(s -> s.getAppearance(level, pos, side, queryState, queryPos))
                    .orElse(super.getAppearance(state, level, pos, side, queryState, queryPos));
        }

        var data = level.getModelData(pos);

        if (data == ModelData.EMPTY) {
            return super.getAppearance(state, level, pos, side, queryState, queryPos);
        }

        var heldState = data.get(NexusPortalCoreBakedModel.CAMO_STATE);

        if (heldState == null) {
            return super.getAppearance(state, level, pos, side, queryState, queryPos);
        }

        return heldState.getAppearance(level, pos, side, queryState, queryPos);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return getCamoState(level, pos)
                .map(s -> s.getLightBlock(level, pos))
                .orElse(level.getMaxLightLevel());
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return getCamoState(level, pos)
                .map(s -> s.getShadeBrightness(level, pos))
                .orElse(super.getShadeBrightness(state, level, pos));
    }

    @Override
    public boolean collisionExtendsVertically(BlockState state, BlockGetter level, BlockPos pos, Entity collidingEntity) {
        return getCamoState(level, pos)
                .map(s -> s.collisionExtendsVertically(level, pos, collidingEntity))
                .orElse(super.collisionExtendsVertically(state, level, pos, collidingEntity));
    }

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        return getCamoState(level, pos)
                .map(s -> s.getFriction(level, pos, entity))
                .orElse(super.getFriction(state, level, pos, entity));
    }

    @Override
    public boolean supportsExternalFaceHiding(BlockState state) {
        return true;
    }

    @Override
    public boolean hasDynamicLightEmission(BlockState state) {
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return Optional.ofNullable(level.getAuxLightManager(pos)).map(lm -> lm.getLightAt(pos)).orElse(0);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        return getCamoState(level, pos)
                .map(s -> s.getSoundType(level, pos, entity))
                .orElse(super.getSoundType(state, level, pos, entity));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState placedOnState = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        BlockState placedState = defaultBlockState().setValue(AXIS, context.getHorizontalDirection().getClockWise().getAxis());
        return placedOnState.is(this) && placedOnState.getValue(FACING) == direction
                ? placedState.setValue(FACING, direction.getOpposite()) : placedState.setValue(FACING, direction);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AXIS);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        super.triggerEvent(state, level, pos, id, param);
        BlockEntity blockentity = level.getBlockEntity(pos);
        return blockentity != null && blockentity.triggerEvent(id, param);
    }
}
