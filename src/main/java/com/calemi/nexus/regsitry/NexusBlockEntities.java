package com.calemi.nexus.regsitry;

import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class NexusBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NexusRef.MOD_ID);

    public static final Supplier<BlockEntityType<NexusPortalCoreBlockEntity>> NEXUS_PORTAL_CORE =
            BLOCK_ENTITY_TYPES.register("nexus_portal_core", () -> new BlockEntityType<>(NexusPortalCoreBlockEntity::new, Set.of(
                    NexusBlocks.NEXUS_PORTAL_CORE.get(),
                    NexusBlocks.IRON_NEXUS_PORTAL_CORE.get(),
                    NexusBlocks.GOLD_NEXUS_PORTAL_CORE.get(),
                    NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE.get(),
                    NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE.get(),
                    NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE.get()
            ), null));

    public static void init() {
        BLOCK_ENTITY_TYPES.register(Nexus.MOD_EVENT_BUS);
    }
}
