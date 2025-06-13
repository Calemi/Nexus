package com.calemi.nexus.block.entity;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class NexusBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NexusRef.ID);

    public static final Supplier<BlockEntityType<NexusPortalCoreBlockEntity>> NEXUS_PORTAL_CORE = BLOCK_ENTITY_TYPES.register("nexus_portal_core",
            () -> new BlockEntityType<>(NexusPortalCoreBlockEntity::new, Set.copyOf(NexusLists.NEXUS_PORTAL_CORE_BLOCKS), null));

    public static final Supplier<BlockEntityType<NexusPortalBlockEntity>> NEXUS_PORTAL = BLOCK_ENTITY_TYPES.register("nexus_portal",
            () -> new BlockEntityType<>(NexusPortalBlockEntity::new, Set.copyOf(NexusLists.NEXUS_PORTAL_BLOCKS), null));

    public static void init() {
        Nexus.LOGGER.info("Registering: Block Entities - Start");
        BLOCK_ENTITY_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Block Entities - End");
    }
}
