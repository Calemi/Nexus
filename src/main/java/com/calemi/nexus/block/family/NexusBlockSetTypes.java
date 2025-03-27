package com.calemi.nexus.block.family;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class NexusBlockSetTypes {

    public static final BlockSetType WARPBLOSSOM = BlockSetType.register(
            new BlockSetType(
                    NexusRef.rl("warpblossom").toString(),
                    true,
                    true,
                    true,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    SoundType.CHERRY_WOOD,
                    SoundEvents.CHERRY_WOOD_DOOR_CLOSE,
                    SoundEvents.CHERRY_WOOD_DOOR_OPEN,
                    SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE,
                    SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN,
                    SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF,
                    SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON
            )
    );
}
