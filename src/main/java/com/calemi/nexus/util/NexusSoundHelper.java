package com.calemi.nexus.util;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.sound.SoundHelper;
import com.calemi.ccore.api.sound.SoundProfile;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.Nullable;

public class NexusSoundHelper {

    public static void playSuccessSound(@Nullable Location location) {
        playSound(location, SoundEvents.CONDUIT_ACTIVATE);
    }

    public static void playErrorSound(@Nullable Location location) {
        playSound(location, SoundEvents.CONDUIT_DEACTIVATE);
    }

    public static void playTeleportSound(@Nullable Location location) {
        playSound(location, SoundEvents.ENDERMAN_TELEPORT);
    }

    public static void playSound(@Nullable Location location, SoundEvent soundEvent) {
        if (location != null) SoundHelper.playAtLocation(location, new SoundProfile(soundEvent).setSource(SoundSource.BLOCKS));
    }
}
