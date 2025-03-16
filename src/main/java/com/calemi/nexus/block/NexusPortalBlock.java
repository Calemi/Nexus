package com.calemi.nexus.block;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.scanner2.VeinBlockScanner2;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.regsitry.NexusLists;
import com.calemi.nexus.regsitry.NexusParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;

public class NexusPortalBlock extends Block implements Portal {

    public static final MapCodec<NexusPortalBlock> CODEC = simpleCodec(NexusPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final int AABB_OFFSET = 2;
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

    private final DyeColor color;
    private final DeferredHolder<ParticleType<?>, SimpleParticleType> particleType;

    public NexusPortalBlock(DyeColor color, DeferredHolder<ParticleType<?>, SimpleParticleType> particleType) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL));
        registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
        this.color = color;
        this.particleType = particleType;
    }

    public NexusPortalBlock(Properties properties) {
        super(properties);
        this.color = DyeColor.WHITE;
        this.particleType = NexusParticles.WHITE_PORTAL_PARTICLES;
    }

    public DyeColor getColor() {
        return color;
    }

    public static Block fromDye(DyeColor color) {
        return NexusLists.NEXUS_PORTAL_BLOCKS.stream().map(m -> (NexusPortalBlock)m.get()).filter(block -> block.getColor() == color).findFirst().orElse(null);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {

        VeinBlockScanner2 scanner = new VeinBlockScanner2(new Location(level, pos), 1000);
        scanner.start();

        for (Location scan : scanner.collectedLocations) {

            scan.relative(Direction.DOWN);

            if (scan.getBlockEntity() instanceof NexusPortalCoreBlockEntity blockEntity) {
                blockEntity.teleportEntity(entity);
                return null;
            }
        }

        return null;
    }

    @Override
    public Portal.Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player player ? player.getAbilities().invulnerable ? 1 : 80 : 0;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {

        for (int i = 0; i < 4; i++) {

            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();

            double speedX = ((double) random.nextFloat() - 0.5) * 0.5;
            double speedY = ((double) random.nextFloat() - 0.5) * 0.5;
            double speedZ = ((double) random.nextFloat() - 0.5) * 0.5;

            int j = random.nextInt(2) * 2 - 1;

            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5 + 0.25 * (double) j;
                speedX = random.nextFloat() * 2.0F * (float) j;
            }

            else {
                z = (double) pos.getZ() + 0.5 + 0.25 * (double) j;
                speedZ = random.nextFloat() * 2.0F * (float) j;
            }

            level.addParticle(particleType.get(), x, y, z, speedX, speedY, speedZ);;
        }
    }


    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? Z_AXIS_AABB : X_AXIS_AABB;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {

        Direction.Axis axis = state.getValue(AXIS);

        BlockPos up = pos.above();
        BlockPos down = pos.below();
        BlockPos left = pos.relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE));
        BlockPos right = pos.relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE));

        return isSuitableNeighbor(level, up) && isSuitableNeighbor(level, down) && isSuitableNeighbor(level, left) && isSuitableNeighbor(level, right);
    }

    private boolean isSuitableNeighbor(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.getBlock() instanceof NexusPortalBlock || state.isViewBlocking(level, pos);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {

        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 0);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case Z -> state.setValue(AXIS, Direction.Axis.X);
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return ItemStack.EMPTY;
    }

    @Override
    public MapCodec<NexusPortalBlock> codec() {
        return CODEC;
    }
}