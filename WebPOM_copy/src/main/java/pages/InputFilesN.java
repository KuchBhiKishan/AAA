package pages;

public class InputFilesN
{
	public String fileSeperator = System.getProperty("file.separator");
	public String testData = System.getProperty("user.dir") + fileSeperator + "TestData" + fileSeperator +"NTestData" +fileSeperator;
	
	
	
	public String MaskImage = testData+"MaskImage.tif";
	public String PolygonTheme = testData+"PolygonTheme.geojson";
	public String HeavyInput = testData+"ImageStatisticsStop.tif";
	public String TestPolygon1 = testData+"TestPolygon1.geojson";
	public String TestPolygon2 = testData+"TestPolygon2.geojson";
	public String testlinetheme1 = testData+"testlinetheme1.geojson";
	public String testlinetheme2 = testData+"testlinetheme2.geojson";
	public String testpointtheme1 = testData+"testpointtheme1.geojson";
	public String testpointtheme2 = testData+"testpointtheme2.geojson";
	public String noboundarypoly1 = testData+"noboundarypoly1.geojson";
	public String noboundarypoly2 = testData+"noboundarypoly2.geojson";
	public String shp_diffprojectiontheme = testData+"diffprojectiontheme.shp";
	

}
