package com.event.java.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.event.java.Entity.Person;
import com.event.java.service.PersonService;

@Controller
@RequestMapping(value="/SpreadSheet")
public class SpreadSheetController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewSpradSheetJsp() {
		return "uploadFile";
	}
	
	private final static String SUCCESS = "Success";
	
	@RequestMapping(value="/uploadSpreadSheet")
	@ResponseBody
	public String uploadSpreadSheet(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
		try {
			InputStream inputStream = file.getInputStream();
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Sheet1");			
			for (int rowCount = 1; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
				String email=sheet.getRow(rowCount).getCell(1).toString();
				boolean available=personService.checkByEmail(email);
				if(!available){
					Person person = new Person();
					person.setPersonName(sheet.getRow(rowCount).getCell(0).toString());
					person.setEmail(email);
					Cell cell=sheet.getRow(rowCount).getCell(2);
					cell.setCellType(cell.CELL_TYPE_STRING);
					BigDecimal bd = new BigDecimal(cell.getStringCellValue());
					person.setPhoneNo(Long.valueOf(bd.toString()));
					Cell cell3=sheet.getRow(rowCount).getCell(3);
					cell3.setCellType(cell3.CELL_TYPE_STRING);
					person.setAge(Long.valueOf(sheet.getRow(rowCount).getCell(3).toString()));
					person.setCity(sheet.getRow(rowCount).getCell(4).toString().toUpperCase());
					personService.addPerson(person);
				}
			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
