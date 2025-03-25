package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class NexusWoodTypes {

    public static final WoodType WARPBLOSSOM = WoodType.register(
            new WoodType(
                    NexusRef.rl("warpblossom").toString(),
                    NexusBlockSetTypes.WARPBLOSSOM,
                    SoundType.CHERRY_WOOD,
                    SoundType.CHERRY_WOOD_HANGING_SIGN,
                    SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE,
                    SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN
            )
    );
}
