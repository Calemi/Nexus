package com.calemi.ccore.api.sound2;

import com.calemi.ccore.api.location.Location;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Helper class for sounds.
 */
public class SoundHelper2 {

    /**
     * Players a Block's placing sound at a Location.
     * @param location The Location to play the sound at.
     * @param state The BlockState of the placed Block.
     */
    public static void playBlockPlace(Location location, BlockState state) {
        new SoundProfile2()
                .setEvent(state.getSoundType(location.getLevel(), location.getBlockPos(), null).getPlaceSound())
                .setSource(SoundSource.BLOCKS)
                .play();
    }

    /**
     * Players a Block's break sound at a Location.
     * @param location The Location to play the sound at.
     * @param state The BlockState of the broke Block.
     */
    public static void playBlockBreak(Location location, BlockState state) {
        new SoundProfile2()
                .setEvent(state.getSoundType(location.getLevel(), location.getBlockPos(), null).getBreakSound())
                .setSource(SoundSource.BLOCKS)
                .play();
    }
}
