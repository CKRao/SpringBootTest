package com.clarkrao.springboot.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/23 10:16
 * @Description: excel导入
 */
public class ExcelImportTest {
    public static final String FILE_PATH="C:\\Users\\user\\Desktop\\" ;
    public static final String EXPORT_NAME= "coos.xls";
   public static void importExcel(){
       File file = new File("C:\\Users\\user\\Desktop\\cpuid.xls");
       List<ExcelModel> excelModels = new ArrayList<>();
       Workbook workbook;
       try {
           //1.获取excel文件
           workbook = Workbook.getWorkbook(file);
           //2.获取sheet
           Sheet sheet = workbook.getSheet(0);
           //3.获取总行数
           int rows = sheet.getRows();
           for (int i = 0; i < rows; i++) {
               ExcelModel excelModel = new ExcelModel();
               if (!"NUMBER".equals(sheet.getCell(0,i).getContents())) {
                   System.out.println(sheet.getCell(0,i).getContents());
                   excelModel.setNumber(sheet.getCell(0,i).getContents());
               }
               if (!"CPUID".equals(sheet.getCell(1,i).getContents())){
                   System.out.println(sheet.getCell(1,i).getContents());
                   excelModel.setCpuId(sheet.getCell(1,i).getContents());
                   excelModels.add(excelModel);
               }

           }
           workbook.close();
           exportExcel(excelModels);
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

    public static void exportExcel(List<ExcelModel> excelModels) {

        try {
            File file = new File(FILE_PATH);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(file,EXPORT_NAME));
            WritableSheet writableSheet = writableWorkbook.createSheet("coos"+new Date(),1);
            writableSheet.addCell(new Label(0,0,"NUMBER"));
            writableSheet.addCell(new Label(1,0,"CPUID"));
            writableSheet.addCell(new Label(2,0,"COOSID"));
            writableSheet.addCell(new Label(3,0,"COOSKEY"));
            writableSheet.addCell(new Label(4,0,"QRCODE"));
            int length = excelModels.size();
            for (int i = 0; i < length; i++) {
                ExcelModel m = excelModels.get(i);
                try {
                    int row = i + 1;
                    writableSheet.addCell(new Label(0,row,m.getNumber()));
                    writableSheet.addCell(new Label(1,row,m.getCpuId()));
                    writableSheet.addCell(new Label(2,row,m.getCoosId()+"CoosId"+m.getNumber()));
                    writableSheet.addCell(new Label(3,row,m.getCoosKey()+"CoosKey"+m.getNumber()));
                    writableSheet.addCell(new Label(4,row,m.getORCodeImgUrl()+"ORCodeImgUrl"+m.getNumber()));
                } catch (WriteException e) {
                    e.printStackTrace();
                }

            }

            writableWorkbook.write();
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        importExcel();
    }

}
