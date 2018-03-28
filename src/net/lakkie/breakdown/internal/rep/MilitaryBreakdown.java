package net.lakkie.breakdown.internal.rep;

import java.io.Serializable;

import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.BasicUtility;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class MilitaryBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = 5800388572185405371L;
	/**
	 * Measured in the thousands
	 */
	private int totalTroops;
	private int reserve;
	private int stockpile;
	private float strengthRate;
	private final NationBreakdown nation;

	public MilitaryBreakdown(NationBreakdown nation)
	{
		this.nation = nation;
		this.totalTroops = 100000;
		this.reserve = 500000;
		this.stockpile = 10000;
		this.strengthRate = 0.5f;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("milit".toCharArray());
		object.addField(SSField.asInt("tottr", this.totalTroops));
		object.addField(SSField.asInt("reser", this.reserve));
		object.addField(SSField.asInt("stock", this.stockpile));
		object.addField(SSField.asFloat("stren", this.strengthRate));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject militaryObject = holder.getObject("milit");
		this.totalTroops = militaryObject.getField("tottr").getInt();
		this.reserve = militaryObject.getField("reser").getInt();
		this.stockpile = militaryObject.getField("stock").getInt();
		this.strengthRate = militaryObject.getField("stren").getFloat();
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

	public String getSummary()
	{
		return String.format(NationSummaryHelper.militaryFormat, this.getAssessment(), BasicUtility.getCommaFormat().format(this.totalTroops),
				BasicUtility.getCommaFormat().format(this.reserve), BasicUtility.getCommaFormat().format(this.stockpile),
				BasicUtility.floatToPercentage(this.strengthRate));
	}

	public int getScore()
	{
		int score = 0;
		score += totalTroops * 2;
		// Deployable troops
		score += Math.min(this.reserve, this.stockpile);
		score = (int) ((float) (score) * this.strengthRate);
		return score;
	}

	public String getAssessment()
	{
		return BasicUtility.getCommaFormat().format(this.getScore());
	}

}
