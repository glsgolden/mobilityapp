package com.simple.mobility.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.simple.mobility.domain.OLTRecord;

public class ExcelReader {

	public static List<OLTRecord> parseExcelFile(File file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<OLTRecord> records = new ArrayList<>();
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		try (Workbook workbook = WorkbookFactory.create(file)) {

			// Retrieving the number of sheets in the Workbook
			System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			sheet.forEach(row -> {
				int cellNumber = 0;
				String region = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String city = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String oltType = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String olt = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String status = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String fpp = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String odf = dataFormatter.formatCellValue(row.getCell(cellNumber++));
				String remarks = dataFormatter.formatCellValue(row.getCell(cellNumber++));

				OLTRecord record = new OLTRecord();

				record.city(city).region(region).oltType(oltType).olt(olt).status(status).fpp(fpp).odf(odf)
						.remarks(remarks);
				records.add(record);
			});
		}

		return records;
	}

}
