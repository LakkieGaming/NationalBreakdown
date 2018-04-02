package net.lakkie.breakdown.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lakkie.breakdown.internal.rep.NationBreakdown;
import net.lakkie.breakdown.internal.rep.NationalIdeologyType;
import net.lakkie.breakdown.menu.pods.BreakdownMenuInterpreter;
import net.lakkie.complexini.rep.CINIConfiguration;

public class NationalProfile
{

	private static final List<NationalProfile> profiles = new ArrayList<NationalProfile>();

	private File file;
	private CINIConfiguration conf;
	private String name, filename;
	private String alliance;
	private List<String> rivals, allies;
	private String focusName;
	private float diploScale, indusScale, interScale, militScale, ideolScale, navalScale;
	private NationalIdeologyType economic, government;
	private int factories, shipyards;
	private float mobilization;
	private float publicSupport, stability, warSupport;
	private int totalTroops, reserve, stockpile;
	private float strengthRate;
	private int convoys;
	private int shipRegular, shipCapital;

	public NationalProfile(String path)
	{
		this.file = new File(BasicUtility.getRoot(), path);
		this.conf = CINIConfiguration.load(this.file);
		this.readPresetFile();
		BreakdownMenuInterpreter.waitForEvent("scenario:load", (args) -> {
			if (args[0].equals(this.file.getName()))
			{
				this.loadPreset();
			}
		});
		profiles.add(this);
	}

	private void readPresetFile()
	{
		this.name = this.conf.getString("/nation#name");
		this.filename = this.conf.getString("/nation#filename");
		this.alliance = this.conf.getString("/nation/diplomatic#alliance");
		this.rivals = this.conf.getAllString("/nation/diplomatic/rivals#rival");
		this.allies = this.conf.getAllString("/nation/diplomatic/allies#ally");
		this.focusName = this.conf.getString("/nation/focus#name");
		this.diploScale = (float) this.conf.getDouble("/nation/focus/scales#diplomatic");
		this.indusScale = (float) this.conf.getDouble("/nation/focus/scales#industrial");
		this.interScale = (float) this.conf.getDouble("/nation/focus/scales#internal");
		this.militScale = (float) this.conf.getDouble("/nation/focus/scales#military");
		this.ideolScale = (float) this.conf.getDouble("/nation/focus/scales#ideological");
		this.navalScale = (float) this.conf.getDouble("/nation/focus/scales#naval");
		this.economic = NationalIdeologyType.valueOf(this.conf.getString("/nation/ideology#economic"));
		this.government = NationalIdeologyType.valueOf(this.conf.getString("/nation/ideology#government"));
		this.factories = (int) this.conf.getDouble("/nation/industrial#factories");
		this.shipyards = (int) this.conf.getDouble("/nation/industrial#naval_shipyards");
		this.mobilization = (float) this.conf.getDouble("/nation/industrial#mobilization_rate");
		this.publicSupport = (float) this.conf.getDouble("/nation/internal#public_support");
		this.stability = (float) this.conf.getDouble("/nation/internal#stability");
		this.warSupport = (float) this.conf.getDouble("/nation/internal#war_support");
		this.totalTroops = (int) this.conf.getDouble("/nation/military#total_troops");
		this.reserve = (int) this.conf.getDouble("/nation/military#reserve");
		this.stockpile = (int) this.conf.getDouble("/nation/military#stockpile");
		this.strengthRate = (float) this.conf.getDouble("/nation/military#strength_rate");
		this.convoys = (int) this.conf.getDouble("/nation/navy#convoy_ships");
		this.shipRegular = (int) this.conf.getDouble("/nation/navy/battleships#regular");
		this.shipCapital = (int) this.conf.getDouble("/nation/navy/battleships#capital");
	}

	public void loadPreset()
	{
		NationBreakdown nation = new NationBreakdown(this.name);
		nation.getDiplomatic().setAlliance(this.alliance);
		nation.getDiplomatic().setRivals(this.rivals);
		nation.getDiplomatic().setAllies(this.allies);
		nation.getFocus().setName(this.focusName);
		nation.getFocus().setDiplomaticScale(this.diploScale);
		nation.getFocus().setIndustrialScale(this.indusScale);
		nation.getFocus().setInternalScale(this.interScale);
		nation.getFocus().setMilitaryScale(this.militScale);
		nation.getFocus().setIdeologicalScale(this.ideolScale);
		nation.getFocus().setNavalScale(this.navalScale);
		nation.getIdeology().setEconomicType(this.economic);
		nation.getIdeology().setGovernmentType(this.government);
		nation.getIndustry().setFactories(this.factories);
		nation.getIndustry().setNaval(this.shipyards);
		nation.getIndustry().setMobilizationRate(this.mobilization);
		nation.getInternal().setPublicSupportRate(this.publicSupport);
		nation.getInternal().setStabilityRate(this.stability);
		nation.getInternal().setWarSupportRate(this.warSupport);
		nation.getMilitary().setTotalTroops(this.totalTroops);
		nation.getMilitary().setReserve(this.reserve);
		nation.getMilitary().setStockpile(this.stockpile);
		nation.getMilitary().setStrengthRate(this.strengthRate);
		nation.getNavy().setConvoy(this.convoys);
		nation.getNavy().setShipRegular(this.shipRegular);
		nation.getNavy().setShipCapital(this.shipCapital);
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public CINIConfiguration getC()
	{
		return conf;
	}

	public void setC(CINIConfiguration c)
	{
		this.conf = c;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getAlliance()
	{
		return alliance;
	}

	public void setAlliance(String alliance)
	{
		this.alliance = alliance;
	}

	public List<String> getRivals()
	{
		return rivals;
	}

	public void setRivals(List<String> rivals)
	{
		this.rivals = rivals;
	}

	public List<String> getAllies()
	{
		return allies;
	}

	public void setAllies(List<String> allies)
	{
		this.allies = allies;
	}

	public String getFocusName()
	{
		return focusName;
	}

	public void setFocusName(String focusName)
	{
		this.focusName = focusName;
	}

	public float getDiploScale()
	{
		return diploScale;
	}

	public void setDiploScale(float diploScale)
	{
		this.diploScale = diploScale;
	}

	public float getIndusScale()
	{
		return indusScale;
	}

	public void setIndusScale(float indusScale)
	{
		this.indusScale = indusScale;
	}

	public float getInterScale()
	{
		return interScale;
	}

	public void setInterScale(float interScale)
	{
		this.interScale = interScale;
	}

	public float getMilitScale()
	{
		return militScale;
	}

	public void setMilitScale(float militScale)
	{
		this.militScale = militScale;
	}

	public float getIdeolScale()
	{
		return ideolScale;
	}

	public void setIdeolScale(float ideolScale)
	{
		this.ideolScale = ideolScale;
	}

	public float getNavalScale()
	{
		return navalScale;
	}

	public void setNavalScale(float navalScale)
	{
		this.navalScale = navalScale;
	}

	public NationalIdeologyType getEconomic()
	{
		return economic;
	}

	public void setEconomic(NationalIdeologyType economic)
	{
		this.economic = economic;
	}

	public NationalIdeologyType getGovernment()
	{
		return government;
	}

	public void setGovernment(NationalIdeologyType government)
	{
		this.government = government;
	}

	public int getFactories()
	{
		return factories;
	}

	public void setFactories(int factories)
	{
		this.factories = factories;
	}

	public int getShipyards()
	{
		return shipyards;
	}

	public void setShipyards(int shipyards)
	{
		this.shipyards = shipyards;
	}

	public float getMobilization()
	{
		return mobilization;
	}

	public void setMobilization(float mobilization)
	{
		this.mobilization = mobilization;
	}

	public float getPublicSupport()
	{
		return publicSupport;
	}

	public void setPublicSupport(float publicSupport)
	{
		this.publicSupport = publicSupport;
	}

	public float getStability()
	{
		return stability;
	}

	public void setStability(float stability)
	{
		this.stability = stability;
	}

	public float getWarSupport()
	{
		return warSupport;
	}

	public void setWarSupport(float warSupport)
	{
		this.warSupport = warSupport;
	}

	public int getTotalTroops()
	{
		return totalTroops;
	}

	public void setTotalTroops(int totalTroops)
	{
		this.totalTroops = totalTroops;
	}

	public int getReserve()
	{
		return reserve;
	}

	public void setReserve(int reserve)
	{
		this.reserve = reserve;
	}

	public int getStockpile()
	{
		return stockpile;
	}

	public void setStockpile(int stockpile)
	{
		this.stockpile = stockpile;
	}

	public float getStrengthRate()
	{
		return strengthRate;
	}

	public void setStrengthRate(float strengthRate)
	{
		this.strengthRate = strengthRate;
	}

	public int getConvoys()
	{
		return convoys;
	}

	public void setConvoys(int convoys)
	{
		this.convoys = convoys;
	}

	public int getShipRegular()
	{
		return shipRegular;
	}

	public void setShipRegular(int shipRegular)
	{
		this.shipRegular = shipRegular;
	}

	public int getShipCapital()
	{
		return shipCapital;
	}

	public void setShipCapital(int shipCapital)
	{
		this.shipCapital = shipCapital;
	}

	public static List<NationalProfile> getProfiles()
	{
		return profiles;
	}

	static
	{
		LoggingSystem.log("Loading national profiles");
		long milliTime = System.currentTimeMillis();
		CINIConfiguration profileList = CINIConfiguration.load(new File(BasicUtility.getRoot(), "Config/Presets/PresetList.cini"));
		for (String path : profileList.getAllString("/presets#path"))
		{
			new NationalProfile(path);
		}
		LoggingSystem.log("Finished loading profiles in " + (System.currentTimeMillis() - milliTime) + "ms");
	}

}
