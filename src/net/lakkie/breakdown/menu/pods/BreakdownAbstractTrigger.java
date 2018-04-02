package net.lakkie.breakdown.menu.pods;

public enum BreakdownAbstractTrigger
{

	ON_START("start", "onstart");

	private final String[] triggerNames;

	private BreakdownAbstractTrigger(String... triggerNames)
	{
		this.triggerNames = triggerNames;
	}

	public String[] getTriggerNames()
	{
		return triggerNames;
	}

	public static BreakdownAbstractTrigger getTrigger(String name)
	{
		for (BreakdownAbstractTrigger trigger : BreakdownAbstractTrigger.values())
		{
			for (String triggerName : trigger.getTriggerNames())
			{
				if (triggerName.equalsIgnoreCase(name)) { return trigger; }
			}
		}
		return null;
	}
	
}
