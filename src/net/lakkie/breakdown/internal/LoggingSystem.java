package net.lakkie.breakdown.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class LoggingSystem
{

	private static StringBuilder log = new StringBuilder();

	public static void log(String msg)
	{
		String evalMsg = "[" + BasicUtility.getDateFormatShort().format(new Date()) + " LOG]: " + msg;
		log.append(evalMsg);
		log.append(System.lineSeparator());
		System.out.println(evalMsg);
	}

	public static void printLog()
	{
		File logFile = new File(BasicUtility.getLogFolder(), "Log-" + BasicUtility.getDateFormatLong().format(new Date()) + ".log");
		try
		{
			FileOutputStream out = new FileOutputStream(logFile);
			out.write(log.toString().getBytes());
			out.flush();
			out.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
