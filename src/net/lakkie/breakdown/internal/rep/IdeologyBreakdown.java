package net.lakkie.breakdown.internal.rep;

import java.io.Serializable;

import net.battlemania.serialization.object.SSArray;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;

public class IdeologyBreakdown implements Serializable
{

	private static final long serialVersionUID = 6839911349323039627L;
	private NationalIdeologyType economicType;
	private NationalIdeologyType governmentType;
	private final NationBreakdown nation;

	public IdeologyBreakdown(NationBreakdown nation)
	{
		this.economicType = NationalIdeologyType.MIXED_ECONOMY;
		this.governmentType = NationalIdeologyType.AUTHORITARIAN;
		this.nation = nation;
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("ideol".toCharArray());
		object.addArray(SSArray.asCharArray("econo", this.economicType.name().toCharArray()));
		object.addArray(SSArray.asCharArray("gover", this.governmentType.name().toCharArray()));
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject ideoObject = holder.getObject("ideol");
		this.economicType = NationalIdeologyType.valueOf(new String(ideoObject.getArray("econo").getChar()));
		this.governmentType = NationalIdeologyType.valueOf(new String(ideoObject.getArray("gover").getChar()));
	}

	public String toString()
	{
		return this.economicType.getAdjective() + " " + this.governmentType;
	}

	public NationalIdeologyType getEconomicType()
	{
		return economicType;
	}

	public NationalIdeologyType getGovernmentType()
	{
		return governmentType;
	}

	public void setEconomicType(NationalIdeologyType economicType)
	{
		this.economicType = economicType;
	}

	public void setGovernmentType(NationalIdeologyType governmentType)
	{
		this.governmentType = governmentType;
	}

}
