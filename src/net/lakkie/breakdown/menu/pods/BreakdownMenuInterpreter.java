package net.lakkie.breakdown.menu.pods;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.complexini.rep.CINIConfigNode;
import net.lakkie.complexini.rep.CINIConfiguration;

public class BreakdownMenuInterpreter
{

	private static BreakdownMenuInterpreter instance;
	private List<BreakdownAbstractMenu> menus = new ArrayList<BreakdownAbstractMenu>();
	private Map<String, List<IEventListener>> eventQueue = new HashMap<String, List<IEventListener>>();

	public BreakdownMenuInterpreter(String file)
	{
		CINIConfiguration config = CINIConfiguration.load(new File(BasicUtility.getRoot(), file));
		for (CINIConfigNode node : config.getNodesWithName("menu"))
		{
			CINIConfiguration menuConfig = CINIConfiguration.load(new File(BasicUtility.getRoot(), node.getFirstString("menu")));
			menus.add(new BreakdownAbstractMenu(node, menuConfig.getNodesWithName("menu").get(0)));
		}
		instance = this;
	}

	public List<BreakdownAbstractMenu> getMenus()
	{
		return menus;
	}

	public Map<String, List<IEventListener>> getEventQueue()
	{
		return eventQueue;
	}

	public static BreakdownMenuInterpreter getInstance()
	{
		return instance;
	}

	public static void sendTrigger(BreakdownAbstractTrigger trigger, String... details)
	{
		for (BreakdownAbstractMenu menu : getInstance().getMenus())
		{
			if (menu.getTrigger() == trigger)
			{
				int i = 0;
				if (menu.getTriggerDetails() == null)
				{
					menu.open();
					continue;
				}
				for (String menuDetails : menu.getTriggerDetails())
				{
					if (menuDetails.equalsIgnoreCase(details[i++]))
					{
						menu.open();
					}
				}
			}
		}
	}

	public static void waitForEvent(String name, IEventListener r)
	{
		if (!getInstance().getEventQueue().containsKey(name))
		{
			getInstance().getEventQueue().put(name, new CopyOnWriteArrayList<IEventListener>());
		}
		getInstance().getEventQueue().get(name).add(r);
	}

	public static void callEvent(String name, String... args)
	{
		if (!getInstance().getEventQueue().containsKey(name)) { return; }
		for (IEventListener r : getInstance().getEventQueue().get(name))
		{
			r.run(args);
		}
	}

	public static void load()
	{
		new BreakdownMenuInterpreter("Config/Menus/MenuList.cini");
	}

}
