package net.lakkie.breakdown.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;

import net.lakkie.breakdown.internal.rep.NationBreakdown;
import net.lakkie.breakdown.menu.BreakdownMenuNation;
import net.lakkie.breakdown.menu.BreakdownMenuReport;

public class BreakdownFileOpener
{

	private BreakdownFileOpener()
	{
	}

	public static void askOpenFile()
	{
		if (NationBreakdown.active != null)
		{
			JOptionPane.showMessageDialog(null, "There is already an open nation.", "Invalid Status", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LoggingSystem.log("Ask open");
		File file = askForFile();
		if (file == null) { return; }
		// Load the nation file
		try
		{
			new NationBreakdown(new FileInputStream(file), file);
		} catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Invalid input file, see console for more details", "Invalid file", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		new BreakdownMenuNation();
	}

	public static void askViewFile()
	{
		if (NationBreakdown.active != null)
		{
			JOptionPane.showMessageDialog(null, "There is already an open nation.", "Invalid Status", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LoggingSystem.log("Ask view");
		File file = askForFile();
		if (file == null) { return; }
		try
		{
			new NationBreakdown(new FileInputStream(file), file);
			new BreakdownMenuReport(NationBreakdown.active);
		} catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Invalid input file, see console for more details", "Invalid file", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static File askForFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter()
		{

			public String getDescription()
			{
				return "National Database Files (*.nationdb)";
			}

			public boolean accept(File file)
			{
				return FilenameUtils.getExtension(file.getName()).equals("nationdb") || file.isDirectory();
			}
		});
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}

}
