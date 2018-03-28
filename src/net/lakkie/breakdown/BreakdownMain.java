package net.lakkie.breakdown;

import javax.swing.UIManager;

import net.lakkie.breakdown.internal.LoggingSystem;
import net.lakkie.breakdown.menu.BreakdownMenuMain;

public class BreakdownMain
{

	public static void main(String[] args)
	{
		LoggingSystem.log("Starting app...");
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// Open the main menu
		new BreakdownMenuMain();
	}

}
