package net.lakkie.breakdown.internal.rep;

import net.lakkie.breakdown.internal.BasicUtility;

public class NationSummaryHelper
{

	public static final String nationFormat;
	public static final String diplomaticFormat;
	public static final String focusFormat;
	public static final String industrialFormat;
	public static final String internalFormat;
	public static final String militaryFormat;
	public static final String navyFormat;

	static
	{
		nationFormat = BasicUtility.readLocalFile("Config/NationFormat.txt");
		diplomaticFormat = BasicUtility.readLocalFile("Config/DiplomaticFormat.txt");
		focusFormat = BasicUtility.readLocalFile("Config/FocusFormat.txt");
		industrialFormat = BasicUtility.readLocalFile("Config/IndustrialFormat.txt");
		internalFormat = BasicUtility.readLocalFile("Config/InternalFormat.txt");
		militaryFormat = BasicUtility.readLocalFile("Config/MilitaryFormat.txt");
		navyFormat = BasicUtility.readLocalFile("Config/NavalFormat.txt");
	}

}
