package net.lakkie.breakdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.UIManager;

import org.apache.commons.io.FilenameUtils;

import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.LoggingSystem;
import net.lakkie.breakdown.internal.NationalProfile;
import net.lakkie.breakdown.internal.rep.NationBreakdown;
import net.lakkie.breakdown.menu.BreakdownMenuMain;
import net.lakkie.breakdown.menu.BreakdownMenuNation;
import net.lakkie.breakdown.menu.pods.BreakdownAbstractTrigger;
import net.lakkie.breakdown.menu.pods.BreakdownMenuInterpreter;

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
		LoggingSystem.log("Loading abstract menus");
		BreakdownMenuInterpreter.load();
		// Open the main menu on splash close
		BreakdownMenuInterpreter.waitForEvent("app:start", (params) -> {
			new BreakdownMenuMain();
		});
		// nation:open event
		BreakdownMenuInterpreter.waitForEvent("scenario:load", (params) -> {
			new NationalProfile(params[0]).loadPreset();
			try
			{
				NationBreakdown.active
						.serialize(new FileOutputStream(new File(BasicUtility.getDatabaseFolder(), FilenameUtils.getBaseName(params[0]) + ".nationdb")));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			NationBreakdown.active = null;
		});
		BreakdownMenuInterpreter.waitForEvent("nation:open", (params) -> {
			try
			{
				File file = new File(BasicUtility.getRoot(), params[0]);
				new NationBreakdown(new FileInputStream(file), file);
				new BreakdownMenuNation();
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		});
		BreakdownMenuInterpreter.waitForEvent("nation:delete", (params) -> {
			try
			{
				File file = new File(BasicUtility.getRoot(), params[0]);
				if (NationBreakdown.active != null && !new File(NationBreakdown.active.getFilePath()).equals(file))
				{
					// This is active
					throw new Exception("Trying to delete " + params[0] + ", but is open!");
				}
				file.delete();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		BreakdownMenuInterpreter.sendTrigger(BreakdownAbstractTrigger.ON_START);
	}

}
