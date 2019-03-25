package com.hema.newretail.backstage.common.utils.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils.excel
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-15 14:11
 */
public class PoiExcelHelper {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String[] cellTitles;
    private String fileName;

    public PoiExcelHelper(String sheetName, String[] cellTitles) {
        workbook = new XSSFWorkbook();
        sheetName = StringUtils.isEmpty(sheetName) ? "test" : sheetName;
        sheet = workbook.createSheet(sheetName);
        this.cellTitles = cellTitles;
        this.fileName = sheetName + ".xlsx";
    }

    /**
     * 创建表头
     */
    public void createTitle() {
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeight((short) 600);
        sheet.setDefaultColumnWidth(20);

        //设置为居中加粗
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        titleRow.setRowStyle(style);
        for (int i = 0; i < cellTitles.length; i++) {
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(cellTitles[i]);
        }
    }

    /**
     * 创建数据
     *
     * @param rowNum   行号
     * @param contents 数据对象
     */
    public void createData(int rowNum, String[] contents) {
        XSSFRow row = sheet.createRow(rowNum);
        row.setHeight((short) 400);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        for (int i = 0; i < contents.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(contents[i]);
        }
    }

    /**
     * 浏览器下载excel
     *
     * @param response HttpServletResponse
     */
    public void buildExcelDocument(HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml");
//            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成excel文件
     */
    public void buildExcelFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
