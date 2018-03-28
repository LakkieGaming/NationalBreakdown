package net.lakkie.breakdown.internal.rep;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import net.battlemania.serialization.object.SSHolder;
import net.lakkie.breakdown.internal.LoggingSystem;

public class NationBreakdown implements Serializable
{

	private static final long serialVersionUID = -9020067431929749399L;

	public static NationBreakdown active;

	private DiplomaticBreakdown diplomatic;
	private MilitaryBreakdown military;
	private NavyBreakdown navy;
	private IndustrialBreakdown industry;
	private FocusBreakdown focus;
	private InternalBreakdown internal;
	private IdeologyBreakdown ideology;

	private String name;
	private String filePath;
	private transient SSHolder holder;

	public NationBreakdown(String name)
	{
		this.name = name;
		this.holder = new SSHolder("natio".toCharArray());
		this.diplomatic = new DiplomaticBreakdown(this);
		this.military = new MilitaryBreakdown(this);
		this.navy = new NavyBreakdown(this);
		this.industry = new IndustrialBreakdown(this);
		this.focus = new FocusBreakdown(this, "Strengthening the Nation");
		this.internal = new InternalBreakdown(this);
		this.ideology = new IdeologyBreakdown(this);
		active = this;
	}

	public NationBreakdown(FileInputStream input, File file)
	{
		try
		{
			this.deserialize(input, file);
			input.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		active = this;
	}

	public String generateSummary()
	{
		return String.format(NationSummaryHelper.nationFormat, this.name, this.ideology.toString(), this.focus.getSummary(), this.diplomatic.getSummary(),
				this.military.getSummary(), this.navy.getSummary(), this.industry.getSummary(), this.internal.getSummary());
	}

	public void serialize(OutputStream out)
	{
		try
		{
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(this);
			objectOut.flush();
			objectOut.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void deserialize(FileInputStream in, File file)
	{
		try
		{
			ObjectInputStream objectIn = new ObjectInputStream(in);
			NationBreakdown obj = (NationBreakdown) objectIn.readObject();
			this.name = obj.name;
			this.diplomatic = obj.diplomatic;
			this.military = obj.military;
			this.navy = obj.navy;
			this.industry = obj.industry;
			this.focus = obj.focus;
			this.internal = obj.internal;
			this.ideology = obj.ideology;
			this.filePath = file.getAbsolutePath();
			obj.filePath = file.getAbsolutePath();
			objectIn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		LoggingSystem.log("Loaded national breakdown: " + file.getName());
	}

	public DiplomaticBreakdown getDiplomatic()
	{
		return diplomatic;
	}

	public void setDiplomatic(DiplomaticBreakdown diplomatic)
	{
		this.diplomatic = diplomatic;
	}

	public MilitaryBreakdown getMilitary()
	{
		return military;
	}

	public void setMilitary(MilitaryBreakdown military)
	{
		this.military = military;
	}

	public NavyBreakdown getNavy()
	{
		return navy;
	}

	public void setNavy(NavyBreakdown navy)
	{
		this.navy = navy;
	}

	public IndustrialBreakdown getIndustry()
	{
		return industry;
	}

	public void setIndustry(IndustrialBreakdown industry)
	{
		this.industry = industry;
	}

	public FocusBreakdown getFocus()
	{
		return focus;
	}

	public void setFocus(FocusBreakdown focus)
	{
		this.focus = focus;
	}

	public InternalBreakdown getInternal()
	{
		return internal;
	}

	public void setInternal(InternalBreakdown internal)
	{
		this.internal = internal;
	}

	public IdeologyBreakdown getIdeology()
	{
		return ideology;
	}

	public void setIdeology(IdeologyBreakdown ideology)
	{
		this.ideology = ideology;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SSHolder getHolder()
	{
		return holder;
	}

	public void setHolder(SSHolder holder)
	{
		this.holder = holder;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

}
