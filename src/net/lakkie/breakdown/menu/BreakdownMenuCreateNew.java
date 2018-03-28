package net.lakkie.breakdown.menu;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.rep.NationBreakdown;

public class BreakdownMenuCreateNew extends JFrame implements KeyListener
{

	private static final long serialVersionUID = 5577728685280822532L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblName;
	private JTextField textField_1;

	public BreakdownMenuCreateNew()
	{
		this.setResizable(false);
		this.setTitle("Create new National Profile");
		this.setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);

		this.textField = new JTextField();
		this.textField.setBounds(116, 84, 216, 20);
		this.textField.addKeyListener(this);
		this.contentPane.add(textField);
		this.textField.setColumns(10);
		this.textField.setHorizontalAlignment(JTextField.CENTER);

		JLabel lblEnterNationalProfile = new JLabel("Create new Profile");
		lblEnterNationalProfile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEnterNationalProfile.setBounds(123, 11, 196, 62);
		this.contentPane.add(lblEnterNationalProfile);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(126, 169, 190, 36);
		btnOk.addActionListener((event) -> {
			if (event.getActionCommand().equals("OK"))
			{
				if (this.textField.getText().replaceAll("\\s", "").equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nation Name is Empty", "Empty Name", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (this.textField_1.getText().replaceAll("\\s", "").equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nation Filename is Empty", "Empty Filename", JOptionPane.ERROR_MESSAGE);
					return;
				}
				dispose();
				new NationBreakdown(this.textField.getText());
				File nationFile = new File(BasicUtility.getDatabaseFolder(), this.textField_1.getText() + ".nationdb");
				if (nationFile.exists())
				{
					JOptionPane.showMessageDialog(null, "This nation breakdown already exists", "Already Exists", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try
				{
					NationBreakdown.active.serialize(new FileOutputStream(nationFile));
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error creating nation, see console for more details", "Error Creating", JOptionPane.ERROR_MESSAGE);
					if (nationFile.exists())
					{
						nationFile.delete();
					}
					e.printStackTrace();
					System.exit(0);
				}

			}
		});
		btnOk.addKeyListener(this);
		this.contentPane.add(btnOk);

		this.lblName = new JLabel("Name");
		this.lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.lblName.setBounds(70, 84, 35, 17);
		this.contentPane.add(this.lblName);

		this.textField_1 = new JTextField();
		this.textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(116, 115, 216, 20);
		this.contentPane.add(this.textField_1);

		JLabel lblFilename = new JLabel("Filename");
		lblFilename.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFilename.setBounds(53, 115, 52, 17);
		this.contentPane.add(lblFilename);
		this.setSize(455, 255);
		this.addKeyListener(this);
		this.setVisible(true);
	}

	// XXX

	public void keyTyped(KeyEvent e)
	{

	}

	public void keyPressed(KeyEvent e)
	{

	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			this.dispose();
		}
	}
}
