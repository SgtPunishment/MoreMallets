package com.whammich.moremallets.utils;

import com.whammich.roadblock.utils.Config;
import com.whammich.roadblock.utils.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Modlogger {

	private static Logger logger = LogManager.getLogger(Reference.name);

	public static void info(Object info) {
		if (Config.debug)
			logger.info(info);
	}

	public static void error(Object error) {
		if (Config.debug)
			logger.error(error);
	}

	public static void debug(Object debug) {
		if (Config.debug)
			logger.debug(debug);
	}
}