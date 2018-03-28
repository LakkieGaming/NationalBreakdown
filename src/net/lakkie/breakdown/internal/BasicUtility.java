package net.lakkie.breakdown.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BasicUtility
{

	private static final File root;
	private static final File databaseFolder;
	private static final File logFolder;
	private static final DateFormat dateFormatShort;
	private static final DateFormat dateFormatLong;
	private static final DecimalFormat percentFormat;
	private static final DecimalFormat commaFormat;

	private BasicUtility()
	{
	}

	public static String floatToPercentage(float per)
	{
		return getPercentageFormat().format(per);
	}

	public static File getRoot()
	{
		return root;
	}

	public static File getDatabaseFolder()
	{
		if (!databaseFolder.exists())
		{
			databaseFolder.mkdirs();
		}
		return databaseFolder;
	}

	public static DecimalFormat getPercentageFormat()
	{
		return percentFormat;
	}

	public static DecimalFormat getCommaFormat()
	{
		return commaFormat;
	}

	public static String[] splitString(String input)
	{
		if (input.replaceAll("\\s", "").equals("")) { return new String[0]; }
		return input.split("\\s*,\\s*");
	}

	public static String joinStrings(List<String> strings)
	{
		StringBuilder result = new StringBuilder();
		int i = 0;
		for (String string : strings)
		{
			if (i++ != 0)
			{
				result.append(", ");
			}
			result.append(string);
		}
		return new String(result);
	}

	public static String readLocalFile(String path)
	{
		try
		{
			Scanner scanner = new Scanner(new File(root, path));
			StringBuilder result = new StringBuilder();
			while (scanner.hasNextLine())
			{
				result.append(scanner.nextLine());
				result.append(System.lineSeparator());
			}
			scanner.close();
			return new String(result);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static File getLogFolder()
	{
		return logFolder;
	}
	
	public static DateFormat getDateFormatShort()
	{
		return dateFormatShort;
	}
	
	public static DateFormat getDateFormatLong()
	{
		return dateFormatLong;
	}

	static
	{
		root = Paths.get(".").toFile();
		databaseFolder = new File(root, "NationDatabase");
		logFolder = new File(root, "Logs");
		percentFormat = new DecimalFormat("##.##%");
		commaFormat = new DecimalFormat("###,###,###,###,###,###");
		dateFormatShort = new SimpleDateFormat("kk:mm:ss");
		dateFormatLong = new SimpleDateFormat("MM-dd-yy-kk-mm-ss");
	}

}
