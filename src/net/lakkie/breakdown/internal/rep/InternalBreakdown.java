package net.lakkie.breakdown.internal.rep;

import static net.lakkie.breakdown.internal.BasicUtility.floatToPercentage;

import java.io.Serializable;

import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class InternalBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = -5493914609569708878L;
	private float publicSupportRate;
	private float stabilityRate;
	private float warSupportRate;
	private final NationBreakdown nation;

	public InternalBreakdown(NationBreakdown nation)
	{
		this.nation = nation;
		this.publicSupportRate = 0.6f;
		this.stabilityRate = 0.75f;
		this.warSupportRate = 0.5f;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("inter".toCharArray());
		object.addField(SSField.asFloat("pubsu", this.publicSupportRate));
		object.addField(SSField.asFloat("stabi", this.stabilityRate));
		object.addField(SSField.asFloat("warsu", this.warSupportRate));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject internalObject = holder.getObject("inter");
		this.publicSupportRate = internalObject.getField("pubsu").getFloat();
		this.stabilityRate = internalObject.getField("stabi").getFloat();
		this.warSupportRate = internalObject.getField("warsu").getFloat();
	}

	public float getPublicSupportRate()
	{
		return publicSupportRate;
	}

	public void setPublicSupportRate(float publicSupportRate)
	{
		this.publicSupportRate = publicSupportRate;
	}

	public float getStabilityRate()
	{
		return stabilityRate;
	}

	public void setStabilityRate(float stabilityRate)
	{
		this.stabilityRate = stabilityRate;
	}

	public float getWarSupportRate()
	{
		return warSupportRate;
	}

	public void setWarSupportRate(float warSupportRate)
	{
		this.warSupportRate = warSupportRate;
	}

	public String getSummary()
	{
		return String.format(NationSummaryHelper.internalFormat, this.getAssessment(), floatToPercentage(this.publicSupportRate),
				floatToPercentage(this.stabilityRate), floatToPercentage(this.warSupportRate));
	}

	public int getScore()
	{
		int score = 0;
		score += this.publicSupportRate * 100;
		score += this.stabilityRate * 100;
		return score;
	}

	public String getAssessment()
	{
		int score = this.getScore();
		// 0 to 200
		if (score >= 170) { return "Extremely Stable"; }
		if (score >= 140) { return "Very Stable"; }
		if (score >= 100) { return "Stable"; }
		if (score >= 70) { return "Moderately Unstable"; }
		if (score >= 40) { return "Unstable"; }
		if (score >= 20) { return "Very Unstable"; }
		return "Extremely Unstable";
	}

}
