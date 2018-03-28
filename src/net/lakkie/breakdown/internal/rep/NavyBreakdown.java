package net.lakkie.breakdown.internal.rep;

import java.io.Serializable;

import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class NavyBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = -6126258815957769103L;
	private int convoy;
	private int shipCapital;
	private int shipRegular;

	private final NationBreakdown nation;

	public NavyBreakdown(NationBreakdown nation)
	{
		this.nation = nation;
		this.convoy = 25;
		this.shipCapital = 0;
		this.shipRegular = 0;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("naval".toCharArray());
		object.addField(SSField.asInt("convo", this.convoy));
		object.addField(SSField.asInt("capit", this.shipCapital));
		object.addField(SSField.asInt("regul", this.shipRegular));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject navyObject = holder.getObject("naval");
		this.convoy = navyObject.getField("convo").getInt();
		this.shipCapital = navyObject.getField("capit").getInt();
		this.shipRegular = navyObject.getField("regul").getInt();
	}

	public int getShips()
	{
		return this.shipCapital + this.shipRegular;
	}

	public int getConvoy()
	{
		return convoy;
	}

	public int getShipCapital()
	{
		return shipCapital;
	}

	public int getShipRegular()
	{
		return shipRegular;
	}

	public void setConvoy(int convoy)
	{
		this.convoy = convoy;
	}

	public void setShipCapital(int shipCapital)
	{
		this.shipCapital = shipCapital;
	}

	public void setShipRegular(int shipRegular)
	{
		this.shipRegular = shipRegular;
	}

	public String getSummary()
	{
		return String.format(NationSummaryHelper.navyFormat, this.getAssessment(), BasicUtility.getCommaFormat().format(this.convoy),
				BasicUtility.getCommaFormat().format(this.shipRegular), BasicUtility.getCommaFormat().format(this.shipCapital));
	}

	public int getScore()
	{
		int score = 0;
		score += this.shipRegular;
		score += this.shipCapital * 3;
		return score;
	}

	public String getAssessment()
	{
		int score = this.getScore();
		if (score >= 350) { return "Enormous"; }
		if (score >= 250) { return "Huge"; }
		if (score >= 200) { return "Very Large"; }
		if (score >= 150) { return "Large"; }
		if (score >= 100) { return "Modestly Large"; }
		if (score >= 75) { return "Modest"; }
		if (score >= 35) { return "Small"; }
		if (score >= 10) { return "Very Small"; }
		return "Insignificant";
	}

}
