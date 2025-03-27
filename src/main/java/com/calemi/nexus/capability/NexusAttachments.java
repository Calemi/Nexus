package com.calemi.nexus.capability;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class NexusAttachments {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, NexusRef.ID);

    public static final Supplier<AttachmentType<UnlockedDimensionsList>> UNLOCKED_DIMENSIONS_ATTACHMENT = ATTACHMENT_TYPES.register("unlocked_dimensions", () ->
            AttachmentType.serializable(UnlockedDimensionsList::new).build());

    public static void init() {
        Nexus.LOGGER.info("Registering: Attachment Types - Start");
        ATTACHMENT_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Attachment Types - End");
    }
}
