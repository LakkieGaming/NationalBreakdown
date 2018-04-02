package net.lakkie.breakdown.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import net.lakkie.breakdown.BreakdownMain;
import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.BreakdownFileOpener;
import net.lakkie.breakdown.internal.LoggingSystem;
import net.lakkie.breakdown.internal.NationalProfile;
import net.lakkie.breakdown.internal.rep.NationBreakdown;

public class BreakdownMenuMain extends JFrame implements WindowListener
{

	public static BreakdownMenuMain menu;

	public BreakdownMenuMain()
	{
		this.setTitle("National Breakdown | Main | " + BreakdownMain.versionInfo.getString("/app#version"));
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());

		this.addTitleText();
		this.addCreateNewButton();
		this.addManageButton();
		this.addExitButton();
		this.addMenuBar();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(this);
		this.setVisible(true);
		if (menu != null)
		{
			menu.dispose();
		}
		menu = this;
	}

	private void addTitleText()
	{
		GridBagConstraints c = new GridBagConstraints();
		JLabel titleText = new JLabel("National Breakdown Manager");
		titleText.setFont(BreakdownFontProvider.headerFont);
		c.gridx = 0;
		c.gridy = 0;
		this.add(titleText, c);
	}

	private void addCreateNewButton()
	{
		GridBagConstraints c = new GridBagConstraints();
		JButton createButton = new JButton("Create new");
		createButton.setFont(BreakdownFontProvider.largeButtonFont);
		createButton.setPreferredSize(new Dimension(250, 50));
		createButton.addActionListener((event) -> {
			if (event.getActionCommand().equals("Create new"))
			{
				new BreakdownMenuCreateNew();
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(50, 0, 0, 0);
		this.add(createButton, c);
	}

	private void addManageButton()
	{
		GridBagConstraints c = new GridBagConstraints();
		JButton manageButton = new JButton("Manage existing");
		manageButton.setFont(BreakdownFontProvider.largeButtonFont);
		manageButton.setPreferredSize(new Dimension(250, 50));
		manageButton.addActionListener((event) -> {
			if (event.getActionCommand().equals("Manage existing"))
			{
				new BreakdownMenuManage();
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 0, 0, 0);
		this.add(manageButton, c);
	}

	private void addExitButton()
	{
		GridBagConstraints c = new GridBagConstraints();
		JButton createButton = new JButton("Exit");
		createButton.setFont(BreakdownFontProvider.largeButtonFont);
		createButton.setPreferredSize(new Dimension(175, 50));
		createButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(15, 0, 0, 0);
		this.add(createButton, c);
	}

	private void addMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

		// Add file menu
		{
			JMenu fileMenu = new JMenu("File");

			JMenuItem openFileItem = new JMenuItem("Open...");
			openFileItem.addActionListener((event) -> {
				if (event.getActionCommand().equals("Open..."))
				{
					BreakdownFileOpener.askOpenFile();
				}
			});
			fileMenu.add(openFileItem);

			JMenuItem viewFileItem = new JMenuItem("View...");
			viewFileItem.addActionListener((event) -> {
				if (event.getActionCommand().equals("View..."))
				{
					BreakdownFileOpener.askViewFile();
				}
			});
			fileMenu.add(viewFileItem);

			menuBar.add(fileMenu);
		}

		// Add preset menu
		{
			JMenu presetMenu = new JMenu("Presets");
			for (NationalProfile profile : NationalProfile.getProfiles())
			{
				JMenuItem profileItem = new JMenuItem(profile.getFilename());
				profileItem.addActionListener((event) -> {
					int confirmStatus = JOptionPane.showConfirmDialog(null, "Open " + profile.getName() + " as " + profile.getFilename() + ".nationdb?",
							"Open Preset?", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (confirmStatus)
					{
					case JOptionPane.YES_OPTION:
						profile.loadPreset();
						try
						{
							NationBreakdown.active.serialize(new FileOutputStream(new File(BasicUtility.getDatabaseFolder(), FilenameUtils.getBaseName(profile.getFile().getName()) + ".nationdb")));
							NationBreakdown.active = null;
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						break;
					case JOptionPane.NO_OPTION:
						String newName = JOptionPane.showInputDialog("Enter new name");
						// If the newName field is null, that means the user has clicked "Cancel"
						if (newName == null) { return; }
						if (newName.equals("") || newName.replaceAll("\\s", "").matches("(\\\\|\\/|:|\\?|\\*|<|>|\"|\\|)"))
						{
							JOptionPane.showMessageDialog(null, "Invalid input name", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try
						{
							profile.loadPreset();
							NationBreakdown.active.serialize(new FileOutputStream(new File(BasicUtility.getDatabaseFolder(), newName + ".nationdb")));
							NationBreakdown.active = null;
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					}
				});
				presetMenu.add(profileItem);
			}
			menuBar.add(presetMenu);
		}

		this.setJMenuBar(menuBar);
	}

	private static final long serialVersionUID = -3533031439204337320L;

	public void windowOpened(WindowEvent e)
	{
		
	}

	public void windowClosing(WindowEvent e)
	{
		LoggingSystem.printLog();
	}

	public void windowClosed(WindowEvent e)
	{
		
	}

	public void windowIconified(WindowEvent e)
	{
		
	}

	public void windowDeiconified(WindowEvent e)
	{
		
	}

	public void windowActivated(WindowEvent e)
	{
		
	}

	public void windowDeactivated(WindowEvent e)
	{
		
	}
}
