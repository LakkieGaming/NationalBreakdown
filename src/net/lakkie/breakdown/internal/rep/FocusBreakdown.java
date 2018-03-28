package net.lakkie.breakdown.internal.rep;

import static net.lakkie.breakdown.internal.BasicUtility.floatToPercentage;

import java.io.Serializable;

import net.battlemania.serialization.object.SSArray;
import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class FocusBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = -5368528200971600658L;
	private String name;
	private float diplomaticScale;
	private float industrialScale;
	private float internalScale;
	private float militaryScale;
	private float ideologicalScale;
	private float navalScale;
	private final NationBreakdown nation;

	public FocusBreakdown(NationBreakdown nation, String name)
	{
		this.nation = nation;
		this.name = name;
		this.diplomaticScale = 0.3f;
		this.industrialScale = 0.3f;
		this.internalScale = 0.3f;
		this.militaryScale = 0.3f;
		this.ideologicalScale = 0.3f;
		this.navalScale = 0.3f;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("focus".toCharArray());
		object.addArray(SSArray.asCharArray("name", name.toCharArray()));
		object.addField(SSField.asFloat("diplo", this.diplomaticScale));
		object.addField(SSField.asFloat("indus", this.industrialScale));
		object.addField(SSField.asFloat("inter", this.internalScale));
		object.addField(SSField.asFloat("milit", this.militaryScale));
		object.addField(SSField.asFloat("ideol", this.ideologicalScale));
		object.addField(SSField.asFloat("naval", this.navalScale));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject focusObject = holder.getObject("focus");
		this.name = new String(focusObject.getArray("name").getChar());
		this.diplomaticScale = focusObject.getField("diplo").getFloat();
		this.industrialScale = focusObject.getField("indus").getFloat();
		this.internalScale = focusObject.getField("inter").getFloat();
		this.militaryScale = focusObject.getField("milit").getFloat();
		this.ideologicalScale = focusObject.getField("ideol").getFloat();
		this.navalScale = focusObject.getField("naval").getFloat();
	}

	public float getTotalScale()
	{
		return this.diplomaticScale + this.industrialScale + this.internalScale + this.militaryScale + this.ideologicalScale + this.navalScale;
	}

	public float getAverageScale()
	{
		return this.getTotalScale() / 6;
	}

	public float getDiplomaticScale()
	{
		return diplomaticScale;
	}

	public float getIdeologicalScale()
	{
		return ideologicalScale;
	}

	public float getIndustrialScale()
	{
		return industrialScale;
	}

	public float getInternalScale()
	{
		return internalScale;
	}

	public float getMilitaryScale()
	{
		return militaryScale;
	}

	public String getName()
	{
		return name;
	}

	public float getNavalScale()
	{
		return navalScale;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDiplomaticScale(float diplomaticScale)
	{
		this.diplomaticScale = diplomaticScale;
	}

	public void setIndustrialScale(float industrialScale)
	{
		this.industrialScale = industrialScale;
	}

	public void setInternalScale(float internalScale)
	{
		this.internalScale = internalScale;
	}

	public void setMilitaryScale(float militaryScale)
	{
		this.militaryScale = militaryScale;
	}

	public void setIdeologicalScale(float ideologicalScale)
	{
		this.ideologicalScale = ideologicalScale;
	}

	public void setNavalScale(float navalScale)
	{
		this.navalScale = navalScale;
	}

	public String getSummary()
	{
		return String.format(NationSummaryHelper.focusFormat, this.nation.getName(), this.name, floatToPercentage(this.diplomaticScale),
				floatToPercentage(this.industrialScale), floatToPercentage(this.internalScale), floatToPercentage(this.militaryScale),
				floatToPercentage(this.ideologicalScale), floatToPercentage(this.navalScale), Integer.toString((int) (this.getTotalScale() * 100)));
	}

	public int getScore()
	{
		int score = 0;
		score += this.diplomaticScale * 100;
		score += this.industrialScale * 100;
		score += this.internalScale * 100;
		score += this.militaryScale * 100;
		score += this.ideologicalScale * 100;
		score += this.navalScale * 100;
		return score;
	}

	public String getAssessment()
	{
		int score = this.getScore();
		if (score >= 450) { return "Very High"; }
		if (score >= 300) { return "High"; }
		if (score >= 180) { return "Modest"; }
		if (score >= 120) { return "Low"; }
		if (score >= 30) { return "Very Low"; }
		return "No";
	}

}
