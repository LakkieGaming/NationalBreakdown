package net.lakkie.breakdown.menu.pods;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.lakkie.complexini.rep.CINIConfigNode;

public class BreakdownAbstractMenuJFrame extends JFrame implements WindowListener
{

	private static final long serialVersionUID = 2707044444084759269L;

	public BreakdownAbstractMenu menu;

	public BreakdownAbstractMenuJFrame(BreakdownAbstractMenu menu)
	{
		this.menu = menu;
		this.setSize((int) this.menu.getFileNode().queryDouble("/size#width"), (int) this.menu.getFileNode().queryDouble("/size#height"));
		this.addWindowListener(this);
		this.setTitle(this.menu.getTitle());
		if (this.menu.getFileNode().queryBoolean("/#center"))
		{
			this.setLocationRelativeTo(null);
		}
		if (this.menu.getFileNode().queryNodeExists("/auto_close#time"))
		{
			new ScheduledThreadPoolExecutor(1).schedule(() -> {
				BreakdownMenuInterpreter.callEvent("window:close");
			}, (long) (this.menu.getFileNode().queryDouble("/auto_close#time")), TimeUnit.valueOf(this.menu.getFileNode().queryString("/auto_close#units")));
		}
		this.setResizable(false);
		this.setLayout(null);
		CINIConfigNode content = this.menu.getFileNode().getChildNodesWithName("content", true).get(0);
		BreakdownMenuInterpreter.waitForEvent("window:close", (args) -> {
			this.dispose();
			this.windowClosing(null);
		});
		for (CINIConfigNode component : content.getChildNodesWithName("component", false))
		{
			int x = (int) component.getFirstDouble("x");
			int y = (int) component.getFirstDouble("y");
			int w = (int) component.getFirstDouble("w");
			int h = (int) component.getFirstDouble("h");
			String type = component.getFirstString("type");
			String value = component.getFirstString("value");
			if (type.equals("text"))
			{
				JLabel label = new JLabel(value);
				label.setBounds(x, y, w, h);
				if (component.queryBoolean("/args#center_width"))
				{
					label.setHorizontalAlignment(JLabel.CENTER);
				} else
				{
					label.setHorizontalAlignment(JLabel.LEFT);
				}
				if (component.queryBoolean("/args#center_height"))
				{
					label.setVerticalAlignment(JLabel.CENTER);
				} else
				{
					label.setVerticalAlignment(JLabel.TOP);
				}
				if (component.queryNodeExists("/args#font_size"))
				{
					label.setFont(new Font(label.getFont().getFontName(), label.getFont().getStyle(), (int) component.queryDouble("/args#font_size")));
				}
				if (component.queryNodeExists("/args#font_name"))
				{
					label.setFont(new Font(component.queryString("/args#font_name"), label.getFont().getStyle(), label.getFont().getSize()));
				}
				this.add(label);
			}
			if (type.equals("button"))
			{
				JButton button = new JButton(value);
				button.setBounds(x, y, w, h);
				CINIConfigNode args = component.getFirstNode("args");
				if (args != null)
				{
					String ev = args.getFirstString("on_click");
					List<String> paramsList = args.getAllString("args");
					String[] params = new String[paramsList.size()];
					int i = 0;
					for (String paramList : paramsList)
					{
						params[i++] = paramList;
					}
					if (ev != null)
					{
						button.addActionListener((event) -> {
							// Params and args are not technically interchangeable but will be used as so
							// here
							BreakdownMenuInterpreter.callEvent(ev, params);
						});
					}
				}
				if (component.queryNodeExists("/args#font_size"))
				{
					button.setFont(new Font(button.getFont().getFontName(), button.getFont().getStyle(), (int) component.queryDouble("/args#font_size")));
				}
				if (component.queryNodeExists("/args#font_name"))
				{
					button.setFont(new Font(component.queryString("/args#font_name"), button.getFont().getStyle(), button.getFont().getSize()));
				}
				this.add(button);
			}
		}
		if (this.menu.getFileNode().getFirstBoolean("close_app"))
		{
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		this.setVisible(this.menu.getFileNode().queryBoolean("/#visible"));
	}

	public void windowActivated(WindowEvent e)
	{

	}

	public void windowClosed(WindowEvent e)
	{

	}

	public void windowClosing(WindowEvent e)
	{
		if (this.menu.getClosingAction() != null)
		{
			BreakdownMenuInterpreter.callEvent(this.menu.getClosingAction());
		}
	}

	public void windowDeactivated(WindowEvent e)
	{

	}

	public void windowDeiconified(WindowEvent e)
	{

	}

	public void windowIconified(WindowEvent e)
	{

	}

	public void windowOpened(WindowEvent e)
	{

	}

}
