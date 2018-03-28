package net.lakkie.breakdown.menu;

import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import net.lakkie.breakdown.internal.rep.NationBreakdown;

public class BreakdownMenuReport extends JFrame
{

	private static final long serialVersionUID = 6990713099916475989L;

	public BreakdownMenuReport(NationBreakdown nation)
	{
		this.setSize(800, 500);
		this.setTitle("National Report: " + nation.getName());
		JTextArea textarea = new JTextArea(nation.generateSummary());
		textarea.setEditable(false);
		textarea.setSize(800, 500);
		JScrollPane scrollPane = new JScrollPane(textarea);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textarea.setMargin(new Insets(10, 10, 10, 10));
		this.add(scrollPane);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
