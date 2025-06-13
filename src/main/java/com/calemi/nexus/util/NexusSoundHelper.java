package com.calemi.nexus.util;

import com.calemi.ccore.api.location.BlockLocation;
import com.calemi.ccore.api.sound.SoundProfile;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.Nullable;

public class NexusSoundHelper {

    public static void playSuccessSound(@Nullable BlockLocation location) {
        playSound(location, SoundEvents.CONDUIT_ACTIVATE);
    }

    public static void playErrorSound(@Nullable BlockLocation location) {
        playSound(location, SoundEvents.CONDUIT_DEACTIVATE);
    }

    public static void playTeleportSound(@Nullable BlockLocation location) {
        playSound(location, SoundEvents.ENDERMAN_TELEPORT);
    }

    public static void playSound(@Nullable BlockLocation location, SoundEvent soundEvent) {
        if (location != null) new SoundProfile().setEvent(soundEvent).setSource(SoundSource.BLOCKS).setLevelAndPosition(location).play();
    }
}
