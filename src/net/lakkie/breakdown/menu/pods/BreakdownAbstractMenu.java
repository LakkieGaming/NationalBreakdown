package net.lakkie.breakdown.menu.pods;

import java.util.List;

import net.lakkie.complexini.rep.CINIConfigNode;

public class BreakdownAbstractMenu
{

	private BreakdownAbstractTrigger trigger;
	private List<String> triggerDetails;
	private String filename;
	private String title;
	private String closingAction;
	private CINIConfigNode fileNode, listNode;

	public BreakdownAbstractMenu(CINIConfigNode listNode, CINIConfigNode fileNode)
	{
		this.fileNode = fileNode;
		this.listNode = listNode;
		this.filename = this.listNode.queryString("/#menu");
		this.trigger = BreakdownAbstractTrigger.getTrigger(this.listNode.queryString("/trigger#trigger"));
		this.triggerDetails = this.listNode.queryNodeExists("/trigger#details") ? this.listNode.queryAllString("/trigger#details") : null;
		this.title = this.fileNode.queryString("/#title");
		this.closingAction = this.fileNode.queryNodeExists("/#closing_action") ? this.fileNode.queryString("/#closing_action") : null;
	}

	public void open()
	{
		new BreakdownAbstractMenuJFrame(this);
	}

	public BreakdownAbstractTrigger getTrigger()
	{
		return trigger;
	}

	public List<String> getTriggerDetails()
	{
		return triggerDetails;
	}

	public String getFilename()
	{
		return filename;
	}

	public String getTitle()
	{
		return title;
	}

	public CINIConfigNode getFileNode()
	{
		return fileNode;
	}

	public CINIConfigNode getListNode()
	{
		return listNode;
	}

	public String getClosingAction()
	{
		return this.closingAction;
	}

}
