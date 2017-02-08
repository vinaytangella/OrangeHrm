package org.qa.OrangeHrm;

import org.apache.log4j.Logger;

public class Sample {
	static public Logger log;
	public static void main(String[] args) {
		log = Logger.getLogger("vinay log");
		log.info("This is the log info");
	}

}
