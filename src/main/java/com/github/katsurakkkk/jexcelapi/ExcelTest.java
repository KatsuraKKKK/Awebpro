package com.github.katsurakkkk.jexcelapi;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * Created by KatsuraKKKK on 2016/6/13 0013.
 */
public class ExcelTest {
	public static void main(String[] args) {
		ExcelTest excelTest = new ExcelTest();
		excelTest.copyExcel();
		excelTest.readExcel();
		excelTest.writeExcel();
	}

	public void readExcel() {
		String sourceName = "aexcel.xls";
		System.out.println("Read " + sourceName);
		try {
			Workbook workbook = Workbook.getWorkbook(new File(sourceName));
			Sheet sheet0 = workbook.getSheet("贷款业务日报表");
			Cell cella1 = sheet0.getCell(0, 0);
			System.out.println(cella1.getContents());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeExcel() {
		try {
			String sourceName = "aexcel.xls";
			String toName = "modify.xls";
			System.out.println("Write " + toName);
			File source = new File(sourceName);
			Workbook workbook = Workbook.getWorkbook(source);
			File to = new File(toName);
			WritableWorkbook modify = Workbook.createWorkbook(to, workbook);
			WritableSheet sheet0 = modify.getSheet("贷款业务日报表");
			WritableCell lableA1 = new Label(0, 5, "HelloWord");
			sheet0.addCell(lableA1);

			modify.write();
			modify.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyExcel() {
		try {
			String sourceName = "aexcel.xls";
			String toName = "copy.xls";

			File source = new File(sourceName);
			Workbook workbook = Workbook.getWorkbook(source);
			File to = new File(toName);
			WritableWorkbook copy = Workbook.createWorkbook(to, workbook);
			System.out.println("Copy [" + source.getAbsolutePath() + "\\" + sourceName + "] to [" + to.getAbsolutePath() + "\\" + toName +
				"]");

			copy.write();
			copy.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
