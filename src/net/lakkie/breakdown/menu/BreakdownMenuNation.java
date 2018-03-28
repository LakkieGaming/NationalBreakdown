package net.lakkie.breakdown.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.LoggingSystem;
import net.lakkie.breakdown.internal.rep.DiplomaticBreakdown;
import net.lakkie.breakdown.internal.rep.FocusBreakdown;
import net.lakkie.breakdown.internal.rep.IdeologyBreakdown;
import net.lakkie.breakdown.internal.rep.IndustrialBreakdown;
import net.lakkie.breakdown.internal.rep.InternalBreakdown;
import net.lakkie.breakdown.internal.rep.MilitaryBreakdown;
import net.lakkie.breakdown.internal.rep.NationBreakdown;
import net.lakkie.breakdown.internal.rep.NationalIdeologyType;
import net.lakkie.breakdown.internal.rep.NavyBreakdown;

public class BreakdownMenuNation extends JFrame implements WindowListener
{

	private static final long serialVersionUID = 3547631689023492502L;
	private JPanel contentPane;
	private final List<JButton> buttons = new ArrayList<JButton>();
	private JTextField textFieldAlliance;
	private JTextField textFieldRivals;
	private JTextField textFieldAllies;
	private JTextField textFieldActiveTroops;
	private JTextField textFieldReserve;
	private JTextField textFieldStockpile;
	private JTextField textFieldStrengthRate;
	private JTextField textFieldConvoy;
	private JTextField textFieldShipCapital;
	private JTextField textFieldShipRegular;
	private JTextField textFieldFactories;
	private JTextField textFieldShipyard;
	private JTextField textFieldMobilization;
	private JTextField textFieldFocusName;
	private JTextField textFieldDiploScale;
	private JTextField textFieldIndusScale;
	private JTextField textFieldInterScale;
	private JTextField textFieldMilitScale;
	private JTextField textFieldIdeolScale;
	private JTextField textFieldNavalScale;
	private JTextField textFieldPublicSupport;
	private JTextField textFieldStability;
	private JTextField textFieldWarSupport;

	public BreakdownMenuNation()
	{
		this.setTitle("National Breakdown - " + NationBreakdown.active.getName());
		this.setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);

		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener((event) -> {
			if (event.getActionCommand().equals("Generate Report"))
			{
				new BreakdownMenuReport(NationBreakdown.active);
			}
		});
		btnGenerateReport.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGenerateReport.setBounds(40, 40, 161, 39);
		this.contentPane.add(btnGenerateReport);

		JLabel lblJapan = new JLabel(NationBreakdown.active.getName());
		lblJapan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblJapan.setHorizontalAlignment(SwingConstants.CENTER);
		lblJapan.setBounds(388, 40, 372, 39);
		this.contentPane.add(lblJapan);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(40, 156, 720, 287);
		this.contentPane.add(tabbedPane);

		JPanel panelDiplo = new JPanel();
		tabbedPane.addTab("Diplomatic", null, panelDiplo, null);
		panelDiplo.setLayout(null);

		this.textFieldAlliance = new JTextField();
		this.textFieldAlliance.setBounds(173, 13, 197, 20);
		this.textFieldAlliance.setText(NationBreakdown.active.getDiplomatic().getAlliance());
		panelDiplo.add(this.textFieldAlliance);
		this.textFieldAlliance.setColumns(10);

		JLabel lblAlliance = new JLabel("Alliance");
		lblAlliance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlliance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlliance.setBounds(25, 14, 138, 14);
		panelDiplo.add(lblAlliance);

		this.textFieldRivals = new JTextField();
		this.textFieldRivals.setColumns(10);
		this.textFieldRivals.setBounds(173, 44, 197, 20);
		this.textFieldRivals.setText(BasicUtility.joinStrings(NationBreakdown.active.getDiplomatic().getRivals()));
		panelDiplo.add(this.textFieldRivals);

		JLabel lblRivals = new JLabel("Rivals");
		lblRivals.setHorizontalAlignment(SwingConstants.CENTER);
		lblRivals.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRivals.setBounds(25, 45, 138, 14);
		panelDiplo.add(lblRivals);

		this.textFieldAllies = new JTextField();
		this.textFieldAllies.setColumns(10);
		this.textFieldAllies.setBounds(173, 75, 197, 20);
		this.textFieldAllies.setText(BasicUtility.joinStrings(NationBreakdown.active.getDiplomatic().getAllies()));
		panelDiplo.add(this.textFieldAllies);

		JLabel lblAllies = new JLabel("Allies");
		lblAllies.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAllies.setBounds(25, 76, 138, 14);
		panelDiplo.add(lblAllies);

		JButton btnUpdateValues = new JButton("Update Values");
		this.buttons.add(btnUpdateValues);
		btnUpdateValues.setBounds(10, 225, 112, 23);
		panelDiplo.add(btnUpdateValues);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setSize(507, 122);
		textArea.setLocation(172, 126);
		textArea.setLineWrap(true);
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textArea.setText(NationBreakdown.active.getDiplomatic().getSummary());
		panelDiplo.add(textArea);

		btnUpdateValues.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				NationBreakdown nation = NationBreakdown.active;
				DiplomaticBreakdown diplo = nation.getDiplomatic();
				diplo.setAlliance(this.textFieldAlliance.getText());
				diplo.setRivals(Arrays.asList(BasicUtility.splitString(this.textFieldRivals.getText())));
				diplo.setAllies(Arrays.asList(BasicUtility.splitString(this.textFieldAllies.getText())));
				textArea.setText(diplo.getSummary());
			}
		});

		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummary.setBounds(10, 172, 112, 31);
		panelDiplo.add(lblSummary);

		JPanel panelMilit = new JPanel();
		tabbedPane.addTab("Military", null, panelMilit, null);
		panelMilit.setLayout(null);

		JTextArea textAreaMilitary = new JTextArea();
		textAreaMilitary.setEditable(false);
		textAreaMilitary.setText((String) null);
		textAreaMilitary.setLineWrap(true);
		textAreaMilitary.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaMilitary.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textAreaMilitary.setBounds(172, 126, 507, 122);
		panelMilit.add(textAreaMilitary);

		JLabel labelMilitary = new JLabel("Summary");
		labelMilitary.setHorizontalAlignment(SwingConstants.CENTER);
		labelMilitary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelMilitary.setBounds(10, 172, 112, 31);
		panelMilit.add(labelMilitary);

		JButton buttonUpdateValues = new JButton("Update Values");
		this.buttons.add(buttonUpdateValues);
		buttonUpdateValues.setBounds(10, 225, 112, 23);
		panelMilit.add(buttonUpdateValues);

		this.textFieldActiveTroops = new JTextField();
		this.textFieldActiveTroops.setBounds(172, 11, 197, 20);
		panelMilit.add(this.textFieldActiveTroops);
		this.textFieldActiveTroops.setColumns(10);

		JLabel lblActiveTroops = new JLabel("Active Troops");
		lblActiveTroops.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActiveTroops.setHorizontalAlignment(SwingConstants.CENTER);
		lblActiveTroops.setBounds(10, 14, 152, 14);
		panelMilit.add(lblActiveTroops);

		this.textFieldReserve = new JTextField();
		this.textFieldReserve.setColumns(10);
		this.textFieldReserve.setBounds(172, 42, 197, 20);
		panelMilit.add(this.textFieldReserve);

		JLabel lblReserve = new JLabel("Reserve");
		lblReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblReserve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReserve.setBounds(10, 45, 152, 14);
		panelMilit.add(lblReserve);

		this.textFieldStockpile = new JTextField();
		this.textFieldStockpile.setColumns(10);
		this.textFieldStockpile.setBounds(172, 73, 197, 20);
		panelMilit.add(this.textFieldStockpile);

		JLabel lblWeaponStockpile = new JLabel("Weapon Stockpile");
		lblWeaponStockpile.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeaponStockpile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeaponStockpile.setBounds(10, 76, 152, 17);
		panelMilit.add(lblWeaponStockpile);

		this.textFieldStrengthRate = new JTextField();
		this.textFieldStrengthRate.setColumns(10);
		this.textFieldStrengthRate.setBounds(508, 11, 197, 20);
		panelMilit.add(this.textFieldStrengthRate);

		JLabel lblStrengthRate = new JLabel("Strength Rate");
		lblStrengthRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrengthRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStrengthRate.setBounds(386, 14, 112, 17);
		panelMilit.add(lblStrengthRate);

		MilitaryBreakdown milit = NationBreakdown.active.getMilitary();
		buttonUpdateValues.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				try
				{
					milit.setTotalTroops(Integer.parseInt(this.textFieldActiveTroops.getText().replaceAll(",", "")));
					milit.setReserve(Integer.parseInt(this.textFieldReserve.getText().replaceAll(",", "")));
					milit.setStockpile(Integer.parseInt(this.textFieldStockpile.getText().replaceAll(",", "")));
					milit.setStrengthRate(Float.parseFloat(this.textFieldStrengthRate.getText().replaceAll("%", "")) / 100f);
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid field data", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				textAreaMilitary.setText(milit.getSummary());
			}
		});

		this.textFieldActiveTroops.setText(BasicUtility.getCommaFormat().format(milit.getTotalTroops()));
		this.textFieldReserve.setText(BasicUtility.getCommaFormat().format(milit.getReserve()));
		this.textFieldStockpile.setText(BasicUtility.getCommaFormat().format(milit.getStockpile()));
		this.textFieldStrengthRate.setText(BasicUtility.getPercentageFormat().format(milit.getStrengthRate()));
		textAreaMilitary.setText(milit.getSummary());

		JPanel panelNaval = new JPanel();
		tabbedPane.addTab("Naval", null, panelNaval, null);
		panelNaval.setLayout(null);

		JTextArea textAreaNaval = new JTextArea();
		textAreaNaval.setText((String) null);
		textAreaNaval.setLineWrap(true);
		textAreaNaval.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaNaval.setEditable(false);
		textAreaNaval.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textAreaNaval.setBounds(172, 126, 507, 122);
		panelNaval.add(textAreaNaval);

		JLabel labelNaval = new JLabel("Summary");
		labelNaval.setHorizontalAlignment(SwingConstants.CENTER);
		labelNaval.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNaval.setBounds(10, 172, 112, 31);
		panelNaval.add(labelNaval);

		JButton buttonUpdateNaval = new JButton("Update Values");
		this.buttons.add(buttonUpdateNaval);
		buttonUpdateNaval.setBounds(10, 225, 112, 23);
		panelNaval.add(buttonUpdateNaval);

		this.textFieldConvoy = new JTextField();
		this.textFieldConvoy.setBounds(172, 11, 197, 20);
		panelNaval.add(this.textFieldConvoy);
		this.textFieldConvoy.setColumns(10);

		JLabel lblNewLabel = new JLabel("Convoy");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 14, 152, 17);
		panelNaval.add(lblNewLabel);

		this.textFieldShipCapital = new JTextField();
		this.textFieldShipCapital.setColumns(10);
		this.textFieldShipCapital.setBounds(172, 42, 197, 20);
		panelNaval.add(this.textFieldShipCapital);

		JLabel lblCapitalShips = new JLabel("Capital Ships");
		lblCapitalShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapitalShips.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCapitalShips.setBounds(10, 45, 152, 17);
		panelNaval.add(lblCapitalShips);

		this.textFieldShipRegular = new JTextField();
		this.textFieldShipRegular.setColumns(10);
		this.textFieldShipRegular.setBounds(172, 73, 197, 20);
		panelNaval.add(this.textFieldShipRegular);

		JLabel lblRegularShips = new JLabel("Regular Ships");
		lblRegularShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegularShips.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegularShips.setBounds(10, 76, 152, 17);
		panelNaval.add(lblRegularShips);

		NavyBreakdown navy = NationBreakdown.active.getNavy();

		buttonUpdateNaval.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				try
				{
					navy.setConvoy(Integer.parseInt(this.textFieldConvoy.getText().replaceAll(",", "")));
					navy.setShipCapital(Integer.parseInt(this.textFieldShipCapital.getText().replaceAll(",", "")));
					navy.setShipRegular(Integer.parseInt(this.textFieldShipRegular.getText().replaceAll(",", "")));
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid field data", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				textAreaNaval.setText(navy.getSummary());
			}
		});

		this.textFieldConvoy.setText(BasicUtility.getCommaFormat().format(navy.getConvoy()));
		this.textFieldShipCapital.setText(BasicUtility.getCommaFormat().format(navy.getShipCapital()));
		this.textFieldShipRegular.setText(BasicUtility.getCommaFormat().format(navy.getShipRegular()));
		textAreaNaval.setText(navy.getSummary());

		JPanel panelIndus = new JPanel();
		tabbedPane.addTab("Industrial / Economic", null, panelIndus, null);
		panelIndus.setLayout(null);

		JTextArea textAreaIndus = new JTextArea();
		textAreaIndus.setText((String) null);
		textAreaIndus.setLineWrap(true);
		textAreaIndus.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaIndus.setEditable(false);
		textAreaIndus.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textAreaIndus.setBounds(172, 126, 507, 122);
		panelIndus.add(textAreaIndus);

		JLabel label = new JLabel("Summary");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(10, 172, 112, 31);
		panelIndus.add(label);

		JButton buttonUpdateIndus = new JButton("Update Values");
		this.buttons.add(buttonUpdateIndus);
		buttonUpdateIndus.setBounds(10, 225, 112, 23);
		panelIndus.add(buttonUpdateIndus);

		this.textFieldFactories = new JTextField();
		this.textFieldFactories.setBounds(172, 11, 197, 20);
		panelIndus.add(this.textFieldFactories);
		this.textFieldFactories.setColumns(10);

		JLabel lblFactories = new JLabel("Factories");
		lblFactories.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFactories.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactories.setBounds(10, 14, 112, 14);
		panelIndus.add(lblFactories);

		this.textFieldShipyard = new JTextField();
		this.textFieldShipyard.setColumns(10);
		this.textFieldShipyard.setBounds(172, 42, 197, 20);
		panelIndus.add(this.textFieldShipyard);

		JLabel lblNavalShipyards = new JLabel("Naval Shipyards");
		lblNavalShipyards.setHorizontalAlignment(SwingConstants.CENTER);
		lblNavalShipyards.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNavalShipyards.setBounds(10, 45, 112, 17);
		panelIndus.add(lblNavalShipyards);

		this.textFieldMobilization = new JTextField();
		this.textFieldMobilization.setColumns(10);
		this.textFieldMobilization.setBounds(172, 73, 197, 20);
		panelIndus.add(this.textFieldMobilization);

		JLabel lblMobilizationRate = new JLabel("Mobilization Rate");
		lblMobilizationRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobilizationRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobilizationRate.setBounds(10, 76, 112, 17);
		panelIndus.add(lblMobilizationRate);

		IndustrialBreakdown indus = NationBreakdown.active.getIndustry();

		buttonUpdateIndus.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				try
				{
					indus.setFactories(Integer.parseInt(this.textFieldFactories.getText().replaceAll(",", "")));
					indus.setMobilizationRate(Float.parseFloat(this.textFieldMobilization.getText().replaceAll("%", "")) / 100f);
					indus.setNaval(Integer.parseInt(this.textFieldShipyard.getText().replaceAll(",", "")));
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid field data", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				textAreaIndus.setText(indus.getSummary());
			}
		});

		this.textFieldFactories.setText(BasicUtility.getCommaFormat().format(indus.getFactories()));
		this.textFieldShipyard.setText(BasicUtility.getCommaFormat().format(indus.getNaval()));
		this.textFieldMobilization.setText(BasicUtility.getPercentageFormat().format(indus.getMobilizationRate()));
		textAreaIndus.setText(indus.getSummary());

		JPanel panelFocus = new JPanel();
		tabbedPane.addTab("Focus", null, panelFocus, null);
		panelFocus.setLayout(null);

		JTextArea textAreaFocus = new JTextArea();
		textAreaFocus.setText((String) null);
		textAreaFocus.setLineWrap(true);
		textAreaFocus.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaFocus.setEditable(false);
		textAreaFocus.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textAreaFocus.setBounds(172, 126, 507, 122);
		panelFocus.add(textAreaFocus);

		JLabel labelFocusSummary = new JLabel("Summary");
		labelFocusSummary.setHorizontalAlignment(SwingConstants.CENTER);
		labelFocusSummary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelFocusSummary.setBounds(10, 172, 112, 31);
		panelFocus.add(labelFocusSummary);

		JButton buttonUpdateFocus = new JButton("Update Values");
		this.buttons.add(buttonUpdateFocus);
		buttonUpdateFocus.setBounds(10, 225, 112, 23);
		panelFocus.add(buttonUpdateFocus);

		this.textFieldFocusName = new JTextField();
		this.textFieldFocusName.setBounds(172, 11, 197, 20);
		panelFocus.add(this.textFieldFocusName);
		this.textFieldFocusName.setColumns(10);

		JLabel lblFocusName = new JLabel("Focus Name");
		lblFocusName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFocusName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFocusName.setBounds(10, 14, 112, 14);
		panelFocus.add(lblFocusName);

		this.textFieldDiploScale = new JTextField();
		this.textFieldDiploScale.setColumns(10);
		this.textFieldDiploScale.setBounds(172, 42, 197, 20);
		panelFocus.add(this.textFieldDiploScale);

		JLabel lblDiplomaticScale = new JLabel("Diplomatic Scale");
		lblDiplomaticScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiplomaticScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiplomaticScale.setBounds(10, 45, 112, 14);
		panelFocus.add(lblDiplomaticScale);

		this.textFieldIndusScale = new JTextField();
		this.textFieldIndusScale.setColumns(10);
		this.textFieldIndusScale.setBounds(172, 73, 197, 20);
		panelFocus.add(this.textFieldIndusScale);

		JLabel lblIndustrialScale = new JLabel("Industrial Scale");
		lblIndustrialScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndustrialScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIndustrialScale.setBounds(10, 76, 112, 14);
		panelFocus.add(lblIndustrialScale);

		this.textFieldInterScale = new JTextField();
		this.textFieldInterScale.setColumns(10);
		this.textFieldInterScale.setBounds(172, 104, 197, 20);
		panelFocus.add(this.textFieldInterScale);

		JLabel lblInternalScale = new JLabel("Internal Scale");
		lblInternalScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblInternalScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInternalScale.setBounds(10, 107, 112, 14);
		panelFocus.add(lblInternalScale);

		this.textFieldMilitScale = new JTextField();
		this.textFieldMilitScale.setColumns(10);
		this.textFieldMilitScale.setBounds(508, 11, 197, 20);
		panelFocus.add(this.textFieldMilitScale);

		JLabel lblMilitaryScale = new JLabel("Military Scale");
		lblMilitaryScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblMilitaryScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMilitaryScale.setBounds(386, 12, 112, 19);
		panelFocus.add(lblMilitaryScale);

		this.textFieldIdeolScale = new JTextField();
		this.textFieldIdeolScale.setColumns(10);
		this.textFieldIdeolScale.setBounds(508, 42, 197, 20);
		panelFocus.add(this.textFieldIdeolScale);

		JLabel lblIdeologicalScale = new JLabel("Ideological Scale");
		lblIdeologicalScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdeologicalScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdeologicalScale.setBounds(386, 43, 112, 19);
		panelFocus.add(lblIdeologicalScale);

		this.textFieldNavalScale = new JTextField();
		this.textFieldNavalScale.setColumns(10);
		this.textFieldNavalScale.setBounds(508, 73, 197, 20);
		panelFocus.add(this.textFieldNavalScale);

		JLabel lblNavalScale = new JLabel("Naval Scale");
		lblNavalScale.setHorizontalAlignment(SwingConstants.CENTER);
		lblNavalScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNavalScale.setBounds(386, 74, 112, 19);
		panelFocus.add(lblNavalScale);

		FocusBreakdown focus = NationBreakdown.active.getFocus();

		buttonUpdateFocus.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				try
				{
					focus.setName(this.textFieldFocusName.getText());
					focus.setDiplomaticScale(Float.parseFloat(this.textFieldDiploScale.getText().replaceAll("%", "")) / 100f);
					focus.setIndustrialScale(Float.parseFloat(this.textFieldIndusScale.getText().replaceAll("%", "")) / 100f);
					focus.setInternalScale(Float.parseFloat(this.textFieldInterScale.getText().replaceAll("%", "")) / 100f);
					focus.setMilitaryScale(Float.parseFloat(this.textFieldMilitScale.getText().replaceAll("%", "")) / 100f);
					focus.setIdeologicalScale(Float.parseFloat(this.textFieldIdeolScale.getText().replaceAll("%", "")) / 100f);
					focus.setNavalScale(Float.parseFloat(this.textFieldNavalScale.getText().replaceAll("%", "")) / 100f);
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid field data", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				textAreaFocus.setText(focus.getSummary());
			}
		});

		this.textFieldFocusName.setText(focus.getName());
		this.textFieldDiploScale.setText(BasicUtility.floatToPercentage(focus.getDiplomaticScale()));
		this.textFieldIndusScale.setText(BasicUtility.floatToPercentage(focus.getIndustrialScale()));
		this.textFieldInterScale.setText(BasicUtility.floatToPercentage(focus.getInternalScale()));
		this.textFieldMilitScale.setText(BasicUtility.floatToPercentage(focus.getMilitaryScale()));
		this.textFieldIdeolScale.setText(BasicUtility.floatToPercentage(focus.getIdeologicalScale()));
		this.textFieldNavalScale.setText(BasicUtility.floatToPercentage(focus.getNavalScale()));
		textAreaFocus.setText(focus.getSummary());

		{
			JPanel panelInter = new JPanel();
			tabbedPane.addTab("Internal", null, panelInter, null);
			panelInter.setLayout(null);

			JTextArea textAreaInter = new JTextArea();
			textAreaInter.setText((String) null);
			textAreaInter.setLineWrap(true);
			textAreaInter.setFont(new Font("Monospaced", Font.PLAIN, 11));
			textAreaInter.setEditable(false);
			textAreaInter.setBorder(new BevelBorder(BevelBorder.LOWERED));
			textAreaInter.setBounds(172, 126, 507, 122);
			panelInter.add(textAreaInter);

			JLabel label_1 = new JLabel("Summary");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			label_1.setBounds(10, 172, 112, 31);
			panelInter.add(label_1);

			JButton buttonUpdateInter = new JButton("Update Values");
			this.buttons.add(buttonUpdateInter);
			buttonUpdateInter.setBounds(10, 225, 112, 23);
			panelInter.add(buttonUpdateInter);

			this.textFieldPublicSupport = new JTextField();
			this.textFieldPublicSupport.setBounds(172, 11, 197, 20);
			panelInter.add(this.textFieldPublicSupport);
			this.textFieldPublicSupport.setColumns(10);

			JLabel lblPublicSupportRate = new JLabel("Public Support Rate");
			lblPublicSupportRate.setHorizontalAlignment(SwingConstants.CENTER);
			lblPublicSupportRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPublicSupportRate.setBounds(10, 14, 121, 17);
			panelInter.add(lblPublicSupportRate);

			this.textFieldStability = new JTextField();
			this.textFieldStability.setColumns(10);
			this.textFieldStability.setBounds(172, 42, 197, 20);
			panelInter.add(this.textFieldStability);

			JLabel lblStabilityRate = new JLabel("Stability Rate");
			lblStabilityRate.setHorizontalAlignment(SwingConstants.CENTER);
			lblStabilityRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblStabilityRate.setBounds(10, 45, 121, 17);
			panelInter.add(lblStabilityRate);

			this.textFieldWarSupport = new JTextField();
			this.textFieldWarSupport.setColumns(10);
			this.textFieldWarSupport.setBounds(172, 73, 197, 20);
			panelInter.add(this.textFieldWarSupport);

			JLabel lblWarSupportRate = new JLabel("War Support Rate");
			lblWarSupportRate.setHorizontalAlignment(SwingConstants.CENTER);
			lblWarSupportRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblWarSupportRate.setBounds(10, 76, 121, 17);
			panelInter.add(lblWarSupportRate);

			InternalBreakdown internal = NationBreakdown.active.getInternal();

			buttonUpdateInter.addActionListener((event) -> {

				if (event.getActionCommand().equals("Update Values"))
				{
					try
					{
						internal.setPublicSupportRate(Float.parseFloat(this.textFieldPublicSupport.getText().replaceAll("%", "")) / 100f);
						internal.setStabilityRate(Float.parseFloat(this.textFieldStability.getText().replaceAll("%", "")) / 100f);
						internal.setWarSupportRate(Float.parseFloat(this.textFieldWarSupport.getText().replaceAll("%", "")) / 100f);
					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "Invalid field data", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						return;
					}

					textAreaInter.setText(internal.getSummary());
				}

			});

			this.textFieldPublicSupport.setText(BasicUtility.floatToPercentage(internal.getPublicSupportRate()));
			this.textFieldStability.setText(BasicUtility.floatToPercentage(internal.getStabilityRate()));
			this.textFieldWarSupport.setText(BasicUtility.floatToPercentage(internal.getWarSupportRate()));
			textAreaInter.setText(internal.getSummary());

		}

		JPanel panelIdeol = new JPanel();
		tabbedPane.addTab("Ideological", null, panelIdeol, null);
		panelIdeol.setLayout(null);

		JTextArea textAreaIdeol = new JTextArea();
		textAreaIdeol.setText((String) null);
		textAreaIdeol.setLineWrap(true);
		textAreaIdeol.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaIdeol.setEditable(false);
		textAreaIdeol.setBorder(new BevelBorder(BevelBorder.LOWERED));
		textAreaIdeol.setBounds(172, 126, 507, 122);
		panelIdeol.add(textAreaIdeol);

		JLabel label_1 = new JLabel("Summary");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(10, 172, 112, 31);
		panelIdeol.add(label_1);

		JButton buttonUpdateIdeol = new JButton("Update Values");
		this.buttons.add(buttonUpdateIdeol);
		buttonUpdateIdeol.setBounds(10, 225, 112, 23);
		panelIdeol.add(buttonUpdateIdeol);

		JList<String> listEconomic = new JList<String>();
		listEconomic.setBounds(172, 14, 138, 99);
		DefaultListModel<String> ecoListModel = new DefaultListModel<String>();
		listEconomic.setModel(ecoListModel);
		for (NationalIdeologyType type : NationalIdeologyType.getEconomic())
		{
			ecoListModel.addElement(type.getAdjective());
		}
		JScrollPane ecoScroll = new JScrollPane(listEconomic);
		ecoScroll.setBounds(172, 14, 138, 99);
		panelIdeol.add(ecoScroll);

		JList<String> listGovernment = new JList<String>();
		listGovernment.setBounds(541, 14, 138, 99);
		DefaultListModel<String> govListModel = new DefaultListModel<String>();
		listGovernment.setModel(govListModel);
		for (NationalIdeologyType type : NationalIdeologyType.getGovernment())
		{
			govListModel.addElement(type.toString());
		}
		JScrollPane govScroll = new JScrollPane(listGovernment);
		govScroll.setBounds(541, 14, 138, 99);
		panelIdeol.add(govScroll);

		JLabel lblEconomicType = new JLabel("Economic Type");
		lblEconomicType.setHorizontalAlignment(SwingConstants.CENTER);
		lblEconomicType.setBounds(172, 0, 138, 14);
		panelIdeol.add(lblEconomicType);

		JLabel lblGovernmentType = new JLabel("Government Type");
		lblGovernmentType.setHorizontalAlignment(SwingConstants.CENTER);
		lblGovernmentType.setBounds(541, 0, 138, 14);
		panelIdeol.add(lblGovernmentType);

		IdeologyBreakdown ideology = NationBreakdown.active.getIdeology();

		buttonUpdateIdeol.addActionListener((event) -> {
			if (event.getActionCommand().equals("Update Values"))
			{
				NationalIdeologyType eco = null;
				for (NationalIdeologyType type : NationalIdeologyType.getEconomic())
				{
					if (type.getAdjective().equals(listEconomic.getSelectedValue()))
					{
						eco = type;
					}
				}
				NationalIdeologyType gov = null;
				for (NationalIdeologyType type : NationalIdeologyType.getGovernment())
				{
					if (type.getAdjective().equals(listGovernment.getSelectedValue()))
					{
						gov = type;
					}
				}
				if (eco == null || gov == null)
				{
					JOptionPane.showMessageDialog(null, "No selected government type", "No Selection", JOptionPane.ERROR_MESSAGE);
				}
				ideology.setEconomicType(eco);
				ideology.setGovernmentType(gov);
				textAreaIdeol.setText(ideology.toString());
			}
		});

		listEconomic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listGovernment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		int i = 0;
		for (NationalIdeologyType type : NationalIdeologyType.getEconomic())
		{
			if (ideology.getEconomicType() == type)
			{
				break;
			}
			i++;
		}
		int i2 = 0;
		for (NationalIdeologyType type : NationalIdeologyType.getGovernment())
		{
			if (ideology.getGovernmentType() == type)
			{
				break;
			}
			i2++;
		}
		listEconomic.setSelectionInterval(i, i);
		listGovernment.setSelectionInterval(i2, i2);
		textAreaIdeol.setText(ideology.toString());

		JLabel lblWhenEditingA = new JLabel("When editing a list, separate each elements with commas (spacing does not matter)");
		lblWhenEditingA.setIcon(new ImageIcon(BreakdownMenuNation.class.getResource("/images/info-32.png")));
		lblWhenEditingA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWhenEditingA.setBounds(40, 106, 546, 39);
		this.contentPane.add(lblWhenEditingA);

		JButton btnSaveAllValues = new JButton("Force Save");
		btnSaveAllValues.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSaveAllValues.setBounds(217, 40, 161, 39);
		btnSaveAllValues.addActionListener((event) -> {
			if (event.getActionCommand().equals("Force Save"))
			{
				this.saveValues();
				JOptionPane.showMessageDialog(null, "Saved changed values", "Saved Values", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.contentPane.add(btnSaveAllValues);
		this.setSize(800, 500);
		this.setResizable(false);
		this.addWindowListener(this);
		this.setVisible(true);
	}

	private void updateValues()
	{
		for (JButton button : this.buttons)
		{
			button.getActionListeners()[0].actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, button.getText()));
		}
	}

	private void saveValues()
	{
		try
		{
			File file = new File(NationBreakdown.active.getFilePath());
			if (file.exists())
			{
				file.delete();
				file.createNewFile();
			}
			FileOutputStream output = new FileOutputStream(file);
			NationBreakdown.active.serialize(output);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		LoggingSystem.log("Saved new values for " + NationBreakdown.active.getName());
	}

	public void windowActivated(WindowEvent e)
	{

	}

	public void windowClosed(WindowEvent e)
	{

	}

	public void windowClosing(WindowEvent e)
	{
		this.updateValues();
		this.saveValues();
		NationBreakdown.active = null;
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
