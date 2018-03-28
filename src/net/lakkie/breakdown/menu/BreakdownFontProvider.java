package net.lakkie.breakdown.menu;

import java.awt.Font;

public class BreakdownFontProvider
{

	public static Font robotoFont;
	public static Font headerFont;
	public static Font buttonFont;
	public static Font largeButtonFont;

	static
	{
		try
		{
			robotoFont = Font.createFont(Font.TRUETYPE_FONT, BreakdownFontProvider.class.getResourceAsStream("/fonts/roboto/Roboto-Regular.ttf"));
			headerFont = new Font(robotoFont.getFontName(), Font.PLAIN, 24);
			buttonFont = new Font(robotoFont.getFontName(), Font.PLAIN, 12);
			largeButtonFont = new Font(robotoFont.getFontName(), Font.PLAIN, 18);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
