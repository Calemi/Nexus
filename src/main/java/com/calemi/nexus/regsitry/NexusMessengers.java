package com.calemi.nexus.regsitry;

import com.calemi.ccore.api.message.Messenger;
import com.calemi.nexus.main.NexusRef;

public class NexusMessengers {

    public static Messenger MAIN = new Messenger(NexusRef.NAME);
    public static Messenger NEXUS_PORTAL_CORE = new Messenger(NexusBlocks.NEXUS_PORTAL_CORE.get().getName().getString());
}
