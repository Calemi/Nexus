package com.calemi.ccore.api.logger;

import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.Logger;

public class LogHelper2 {

    public static void logCommon(Logger logger, Level level, Object message) {
        logger.info(level.isClientSide() ? "[CLIENT] " : "[SERVER] ", message);
    }
}