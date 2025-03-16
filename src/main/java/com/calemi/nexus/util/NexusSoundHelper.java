package com.calemi.nexus.util;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class NexusSoundHelper {

    public static void playSuccessSound(Player player) {
        playSound(player, SoundEvents.CONDUIT_ACTIVATE);
    }

    public static void playErrorSound(Player player) {
        playSound(player, SoundEvents.CONDUIT_DEACTIVATE);
    }

    public static void playTeleportSound(Player player) {
        playSound(player, SoundEvents.ENDERMAN_TELEPORT);
    }

    public static void playSound(Player player, SoundEvent soundEvent) {
        player.level().playSeededSound(null, player.getX(), player.getY(), player.getZ(), Holder.direct(SoundEvent.createVariableRangeEvent(soundEvent.getLocation())), SoundSource.PLAYERS, 1, 1, new Random().nextInt());
    }
}
