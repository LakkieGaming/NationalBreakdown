package net.lakkie.breakdown.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FilenameUtils;

import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.LoggingSystem;
import net.lakkie.breakdown.internal.rep.NationBreakdown;

public class BreakdownMenuManage extends JFrame
{

	private static final long serialVersionUID = -4260141100442272841L;
	private JPanel contentPane;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BreakdownMenuManage()
	{
		setTitle("Manage Existing Nations");
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(350, 300);
		this.setResizable(false);
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblLocallyStoredNations = new JLabel("Locally Stored Nations:");
		lblLocallyStoredNations.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocallyStoredNations.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocallyStoredNations.setBounds(77, 11, 190, 37);
		this.contentPane.add(lblLocallyStoredNations);

		JButton btnSelect = new JButton("Open");
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelect.setBounds(10, 223, 112, 37);
		this.contentPane.add(btnSelect);

		JList list = new JList();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		for (String file : this.getFiles())
		{
			listModel.addElement(file);
		}
		list.setBounds(66, 59, 211, 153);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(66, 59, 211, 153);
		this.contentPane.add(scrollPane);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(222, 223, 112, 37);
		this.contentPane.add(btnDelete);
		btnDelete.addActionListener((event) -> {
			if (event.getActionCommand().equals("Delete"))
			{
				if (list.getSelectedValuesList().size() > 1)
				{
					JOptionPane.showMessageDialog(null, "You can only select 1 at a time", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (list.getSelectedValuesList().size() == 0)
				{
					JOptionPane.showMessageDialog(null, "Select 1 nation breakdown file", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String selection = (String) list.getSelectedValue();
				File file = new File(BasicUtility.getDatabaseFolder(), selection);
				int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to delete " + selection + "?", "Delete " + selection,
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION)
				{
					file.delete();
					listModel.removeElement(selection);
				}
			}
		});

		btnSelect.addActionListener((event) -> {
			if (event.getActionCommand().equals("Open"))
			{
				if (list.getSelectedValuesList().size() > 1)
				{
					JOptionPane.showMessageDialog(null, "You can only select 1 at a time", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (list.getSelectedValuesList().size() == 0)
				{
					JOptionPane.showMessageDialog(null, "Select 1 nation breakdown file", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (NationBreakdown.active != null)
				{
					JOptionPane.showMessageDialog(null, "There is already an open nation.", "Invalid Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String selection = (String) list.getSelectedValue();
				File file = new File(BasicUtility.getDatabaseFolder(), selection);
				try
				{
					new NationBreakdown(new FileInputStream(file), file);
					new BreakdownMenuNation();
					dispose();
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		});

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private String[] getFiles()
	{
		LoggingSystem.log("Searching for local nation breakdowns");
		File[] fileList = BasicUtility.getDatabaseFolder().listFiles((dir, filename) -> {
			return FilenameUtils.getExtension(filename).equals("nationdb");
		});
		String[] filenames = new String[fileList.length];
		for (int i = 0; i < fileList.length; i++)
		{
			filenames[i] = fileList[i].getName();
		}
		return filenames;
	}
}
