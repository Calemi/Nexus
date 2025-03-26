package com.calemi.ccore.api.entity;

import com.calemi.ccore.api.boat.CBoatType;
import com.calemi.ccore.api.boat.CBoatTypeRegistry;
import com.calemi.ccore.api.init.CEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CBoat extends Boat {

    public CBoat(EntityType<? extends CBoat> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    public CBoat(Level level, double x, double y, double z) {
        this(CEntities.BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putString("Type", getBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("Type", Tag.TAG_STRING)) {
            entityData.set(DATA_ID_TYPE, CBoatTypeRegistry.getIndex(CBoatTypeRegistry.byName(nbt.getString("Type"))));
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {

        lastYd = getDeltaMovement().y;

        if (!isPassenger()) {

            if (onGround) {

                if (fallDistance > 3.0F) {

                    if (status != Boat.Status.ON_LAND) {
                        resetFallDistance();
                        return;
                    }

                    causeFallDamage(fallDistance, 1.0F, damageSources().fall());

                    if (!level().isClientSide && !isRemoved()) {

                        kill();

                        if (level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {

                            for (int i = 0; i < 3; ++i) {
                                spawnAtLocation(getBoatType().getPlanks());
                            }

                            for (int j = 0; j < 2; ++j) {
                                spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                resetFallDistance();
            }

            else if (!level().getFluidState(blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                fallDistance -= (float) y;
            }
        }
    }

    @Override
    public Item getDropItem() {
        return getBoatType().getBoatItem().get();
    }

    public void setBoatType(CBoatType type) {
        entityData.set(DATA_ID_TYPE, CBoatTypeRegistry.getIndex(type));
    }

    public CBoatType getBoatType() {
        return CBoatTypeRegistry.byIndex(entityData.get(DATA_ID_TYPE));
    }

    @Deprecated
    @Override
    public Type getVariant() {
        return Type.OAK;
    }

    @Deprecated
    @Override
    public void setVariant(Type vanillaType) {}
}