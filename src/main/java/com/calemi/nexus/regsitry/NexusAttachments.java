package com.calemi.nexus.regsitry;

import com.calemi.nexus.capability.UnlockedDimensionsList;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class NexusAttachments {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, NexusRef.MOD_ID);

    public static final Supplier<AttachmentType<UnlockedDimensionsList>> UNLOCKED_DIMENSIONS_ATTACHMENT = ATTACHMENT_TYPES.register("unlocked_dimensions", () ->
            AttachmentType.serializable(UnlockedDimensionsList::new).build());

    public static void init() {
        ATTACHMENT_TYPES.register(Nexus.MOD_EVENT_BUS);
    }
}
