package net.lakkie.breakdown.internal.rep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.battlemania.serialization.object.SSArray;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;
import net.lakkie.breakdown.internal.rep.i.IScoreable;
import net.lakkie.breakdown.internal.rep.i.ISummarizable;

public class DiplomaticBreakdown implements IScoreable, ISummarizable, Serializable
{

	private static final long serialVersionUID = 4790716563505785714L;
	private String alliance;
	private List<String> rivals;
	private List<String> allies;
	private final NationBreakdown nation;

	public DiplomaticBreakdown(NationBreakdown nation)
	{
		this.nation = nation;
		this.alliance = "No";
		this.rivals = new ArrayList<String>();
		this.allies = new ArrayList<String>();
	}

	public NationBreakdown getNation()
	{
		return nation;
	}

	public void serialize(SSHolder holder)
	{
		SSObject object = new SSObject("diplo".toCharArray());
		object.addArray(SSArray.asCharArray("alliance", this.alliance.toCharArray()));
		int i = 0;
		for (String rival : this.rivals)
		{
			object.addArray(SSArray.asCharArray("rival" + i++, rival.toCharArray()));
		}
		i = 0;
		for (String ally : this.allies)
		{
			object.addArray(SSArray.asCharArray("ally" + i++, ally.toCharArray()));
		}
		holder.addObject(object);
	}

	public void deserialize(SSHolder holder)
	{
		SSObject diploObject = holder.getObject("diplo");
		this.alliance = new String(diploObject.getArray("alliance").getChar());
		for (SSArray array : diploObject.getArrays())
		{
			String arrayName = new String(array.name);
			this.rivals.clear();
			if (arrayName.startsWith("rival"))
			{
				this.rivals.add(new String(array.getChar()));
			}
			this.allies.clear();
			if (arrayName.startsWith("ally"))
			{
				this.allies.add(new String(array.getChar()));
			}
		}
	}

	public String getAlliance()
	{
		return alliance;
	}

	public List<String> getAllies()
	{
		return allies;
	}

	public List<String> getRivals()
	{
		return rivals;
	}

	public void setAlliance(String alliance)
	{
		this.alliance = alliance;
	}

	public void setAllies(List<String> allies)
	{
		this.allies = allies;
	}

	public void setRivals(List<String> rivals)
	{
		this.rivals = rivals;
	}

	public String getSummary()
	{
		return String.format(NationSummaryHelper.diplomaticFormat, this.nation.getName(), this.getAssessment(), this.allies.size(), this.rivals.size(), this.alliance);
	}

	public int getScore()
	{
		int score = 0;
		score += this.alliance.equals("None") ? 0 : 40;
		score += this.allies.size() * 10;
		score += this.rivals.size() * 15;
		return score;
	}

	public String getAssessment()
	{
		int score = this.getScore();
		if (score == 0) { return "No"; }
		if (score >= 90) { return "Very Strong"; }
		if (score >= 60) { return "Strong"; }
		if (score >= 40) { return "Modest"; }
		if (score >= 20) { return "Weak"; }
		return "Very Weak";
	}

}
