package net.lakkie.breakdown.internal.rep;

import java.io.Serializable;

import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class IndustrialBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = -7172923819369767694L;
	private int factories;
	private int naval;
	private float mobilizationRate;
	private final NationBreakdown nation;

	public IndustrialBreakdown(NationBreakdown nation)
	{
		this.nation = nation;
		this.mobilizationRate = 0.25f;
		this.factories = 30;
		this.naval = 3;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("indus".toCharArray());
		object.addField(SSField.asInt("fact", this.factories));
		object.addField(SSField.asInt("nava", this.naval));
		object.addField(SSField.asFloat("mobi", this.mobilizationRate));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject industrialObject = holder.getObject("indus");
		this.factories = industrialObject.getField("fact").getInt();
		this.naval = industrialObject.getField("nava").getInt();
		this.mobilizationRate = industrialObject.getField("mobi").getFloat();
	}

	public int getIndustrialMight()
	{
		return this.factories + this.naval;
	}

	public int getMilitary()
	{
		return (int) ((float) (this.factories) * this.mobilizationRate);
	}

	public int getCivilian()
	{
		return this.factories - this.getMilitary();
	}

	public int getFactories()
	{
		return factories;
	}

	public void setFactories(int factories)
	{
		this.factories = factories;
	}

	public int getNaval()
	{
		return naval;
	}

	public void setNaval(int naval)
	{
		this.naval = naval;
	}

	public float getMobilizationRate()
	{
		return mobilizationRate;
	}

	public void setMobilizationRate(float mobilizationRate)
	{
		this.mobilizationRate = mobilizationRate;
	}

	public String getSummary()
	{
		return String.format(NationSummaryHelper.industrialFormat, this.nation.getName(), this.getAssessment(), this.factories,
				BasicUtility.floatToPercentage(this.mobilizationRate), this.naval);
	}

	public int getScore()
	{
		int score = 0;
		score += this.naval * 3;
		score += this.getCivilian() * 2;
		score += this.getMilitary() * 1;
		return score;
	}

	public String getAssessment()
	{
		int score = this.getScore();
		if (score >= 600) { return "Very Large"; }
		if (score >= 300) { return "Larger"; }
		if (score >= 200) { return "Large"; }
		if (score >= 125) { return "Well Sized"; }
		if (score >= 75) { return "Modest"; }
		if (score >= 50) { return "Small"; }
		if (score >= 20) { return "Smaller"; }
		return "Very Small";
	}

}
