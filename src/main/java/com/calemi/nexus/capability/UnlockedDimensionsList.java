package com.calemi.nexus.capability;

import com.calemi.nexus.regsitry.NexusAttachments;
import com.calemi.nexus.regsitry.NexusDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.HashSet;
import java.util.Set;

public class UnlockedDimensionsList implements INBTSerializable<CompoundTag> {

    private final Set<ResourceLocation> unlockedDimensions = new HashSet<>();

    public UnlockedDimensionsList() {
        unlockedDimensions.add(Level.OVERWORLD.location());
        unlockedDimensions.add(NexusDimensions.NEXUS_RL);
    }

    public static UnlockedDimensionsList get(Player player) {
        return player.getData(NexusAttachments.UNLOCKED_DIMENSIONS_ATTACHMENT);
    }

    public Set<ResourceLocation> getUnlockedDimensions() {
        return unlockedDimensions;
    }

    public boolean isUnlocked(ResourceLocation dimensionResourceLocation) {
        return unlockedDimensions.contains(dimensionResourceLocation);
    }

    public boolean unlock(ResourceLocation dimensionResourceLocation) {

        if (!isUnlocked(dimensionResourceLocation)) {

            unlockedDimensions.add(dimensionResourceLocation);
            return true;
        }

        return false;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {

        CompoundTag tag = new CompoundTag();

        ListTag listTag = new ListTag();

        for (ResourceLocation location : unlockedDimensions) {
            listTag.add(StringTag.valueOf(location.toString()));
        }

        tag.put("UnlockedDimensions", listTag);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {

        unlockedDimensions.clear();

        if (tag.contains("UnlockedDimensions")) {

            ListTag listTag = tag.getList("UnlockedDimensions", StringTag.TAG_STRING);

            for (int i = 0; i < listTag.size(); i++) {
                unlockedDimensions.add(ResourceLocation.parse(listTag.getString(i)));
            }
        }
    }
}
