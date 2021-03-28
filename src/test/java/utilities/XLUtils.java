package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
		public  FileInputStream fi;
		public  FileOutputStream fo;
		public  XSSFWorkbook workbook;
		public 	XSSFSheet sheet;
		public  XSSFRow row;
		public  XSSFCell cell;
		public  CellStyle style;
		String path;
	public XLUtils(String path)
	{
		this.path=path;
	}
		
		
		
	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
				
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int cellcount=sheet.getRow(rownum).getLastCellNum();
		workbook.close();
		fi.close();		
		return cellcount;		
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		cell=sheet.getRow(rownum).getCell(colnum);// cell has data
		
		// in excel--so many types of data--if you want to consider everything as string use DataFormatter class
		String data;				// not initialised any value here-----String data=""; it is empty-its return o/p blank-:::::------String data=null means unknown value o/p: null
		try
		{
			DataFormatter formatter=new DataFormatter();
			data=formatter.formatCellValue(cell);
			return data;
			
		}
		// if cell is empty the above code return exceptions then catch box will execute.----just to handle null pointer exception
		
		catch(Exception e)
		{
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;		
		
	}
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		// reading and writing both happening---means updating existing excel file
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row= sheet.getRow(rownum);
		cell=row.createCell(colnum);// create new cell where you can update the data
		cell.setCellValue(data);
		fo=new FileOutputStream(path);// open file on output stream
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
				
		
	}
	
	public  void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();	
			
	}
	

}
