package net.lakkie.breakdown.internal.rep;

import java.util.ArrayList;
import java.util.List;

public enum NationalIdeologyType
{

	ANARCHISM("Anarchist", false), CENTRISM("Centrist", true), CONSERVATISM("Conservative", true), LIBERALISM("Liberal", true), SOCIALISM("Socialist",
			true), CAPITALISM("Capitalist", true), AUTHORITARIAN("Authoritarian", false), MIXED_ECONOMY("Mixed",
					true), FASCIST("Fascist", false), COMMUNIST("Communist", false), NON_ALIGNED("Non Aligned", false), DEMOCRACY("Democratic", false);

	private static final List<NationalIdeologyType> economicList, governmentList;

	private final String adjective;
	private final boolean economic;

	private NationalIdeologyType(String adjective, boolean economic)
	{
		this.adjective = adjective;
		this.economic = economic;
	}

	public String getAdjective()
	{
		return adjective;
	}

	public boolean isEconomic()
	{
		return economic;
	}

	public String toString()
	{
		char[] nameChars = this.name().toLowerCase().replaceAll("_", " ").toCharArray();
		nameChars[0] = Character.toUpperCase(nameChars[0]);
		return new String(nameChars);
	}

	public static List<NationalIdeologyType> getEconomic()
	{
		return economicList;
	}

	public static List<NationalIdeologyType> getGovernment()
	{
		return governmentList;
	}

	static
	{
		economicList = new ArrayList<NationalIdeologyType>();
		governmentList = new ArrayList<NationalIdeologyType>();
		for (NationalIdeologyType type : values())
		{
			if (type.isEconomic())
			{
				economicList.add(type);
			} else
			{
				governmentList.add(type);
			}
		}
	}

}
