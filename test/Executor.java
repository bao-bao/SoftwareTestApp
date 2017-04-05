/* Created by AMXPC on 2017/3/15. */

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

class Executor {
    private String dataFile = null;
    private String sheet = null;
    private String targetFile = null;

    Executor(String dataFile, String targetFile, String sheet) {
        this.dataFile = dataFile;
        this.targetFile = targetFile;
        this.sheet = sheet;
    }

    ArrayList<Object> execute(Object object, Method method, int argNum) {
        Workbook workbook = null;
        ArrayList<Object> testResult = new ArrayList<>();
        try {
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream(dataFile);
            workbook = Workbook.getWorkbook(instream);
            //Sheet的下标是从0开始
            //获取第一张Sheet表
            Sheet readsheet = workbook.getSheet(sheet);
            //获取指定单元格的对象引用
            for (int row = 1; row <= readsheet.getRows(); row++) {
                String[] args = new String[argNum];
                for (int column = 1; column <= argNum; column++) {
                    Cell cell = readsheet.getCell(column, row);
                    args[column - 1] = cell.getContents();
                    System.out.print(cell.getContents() + " ");
                }
                try {
                    testResult.add(method.invoke(object, (Object) args));
                } catch (Exception e) {
                    e.printStackTrace();
                    testResult.add("Error: Wrong arguments format");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return testResult;
    }

    AnalysisResult write(ArrayList<Object> result, int resultColumn, String tester) {
        WritableWorkbook workbook;
        Workbook data;
        AnalysisResult analysisResult = null;
        try {
            /* 方法一：直接从目标文件中读取 WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));
             * 方法二：将WritableWorkbook直接写入到输出流 WritableWorkbook wwb = Workbook.createWorkbook(outputStream);   */
            //创建Excel工作表 指定名称和位置
            data = Workbook.getWorkbook(new FileInputStream(dataFile));
            workbook = Workbook.createWorkbook(new File(targetFile), data);
            WritableSheet wSheet = workbook.getSheet(sheet);
            double rightNum = 0.0;

            for (int row = 0; row < result.size(); row++) {
            /* ************往工作表中添加数据**************** */
                //1.添加Label对象
                if (result.get(row) instanceof String) {
                    Label label = new Label(resultColumn, row + 1, (String) result.get(row));
                    wSheet.addCell(label);
                }
//            //添加带有字型Formatting对象
//            WritableFont wf = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,true);
//            WritableCellFormat wcf = new WritableCellFormat(wf);
//            Label labelcf = new Label(1,0,"this is a label test",wcf);
//            sheet.addCell(labelcf);
//
//            //添加带有字体颜色的Formatting对象
//            WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,
//                    UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.DARK_YELLOW);
//            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
//            Label labelCF = new Label(1,0,"Ok",wcfFC);
//            sheet.addCell(labelCF);
//
                //2.添加Number对象
                if (result.get(row) instanceof java.lang.Number) {
                    Number labelN = new Number(resultColumn, row + 1, (double) result.get(row));
                    wSheet.addCell(labelN);
                }
//            //添加带有formatting的Number对象
//            NumberFormat nf = new NumberFormat("#.##");
//            WritableCellFormat wcfN = new WritableCellFormat(nf);
//            Number labelNF = new jxl.write.Number(1,1,3.1415926,wcfN);
//            sheet.addCell(labelNF);

                //3.添加Boolean对象
                if (result.get(row) instanceof java.lang.Boolean) {
                    Boolean labelB = new Boolean(resultColumn, row + 1, (boolean) result.get(row));
                    wSheet.addCell(labelB);
                }

                //4.添加DateTime对象
                if (result.get(row) instanceof Date) {
                    DateTime labelDT = new DateTime(resultColumn, row + 1, (Date) (result.get(row)));
                    wSheet.addCell(labelDT);
                }
//            //5.添加带有formatting的DateFormat对象
//            DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
//            WritableCellFormat wcfDF = new WritableCellFormat(df);
//            DateTime labelDTF = new DateTime(1,3,new java.util.Date(),wcfDF);
//            sheet.addCell(labelDTF);
//
//            //6.添加图片对象,jxl只支持png格式图片
//            File image = new File("f:\\1.png");
//            WritableImage wimage = new WritableImage(0,4,6,17,image);
//            sheet.addImage(wimage);

                //7.判断是否正确
                Cell expected = wSheet.getCell(resultColumn + 1, row + 1);
                if (Objects.equals(expected.getContents(), result.get(row).toString())) {
                    rightNum++;
                    Boolean compare = new Boolean(resultColumn + 2, row + 1, true);
                    wSheet.addCell(compare);
                } else {
                    Boolean compare = new Boolean(resultColumn + 2, row + 1, false);
                    wSheet.addCell(compare);
                }
            }
            //8.添加正确率
            Number labelPercent = new Number(resultColumn + 3, 1, (rightNum / result.size()) * 100);
            wSheet.addCell(labelPercent);
            //9.添加时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Label label = new Label(resultColumn + 4, 1, sdf.format(new Date()));
            wSheet.addCell(label);
            //10.写入测试人
            Label ter = new Label(resultColumn + 5, 1, tester);
            wSheet.addCell(ter);
            //11.写入工作表
            workbook.write();
            workbook.close();
            //12.写入分析结果
            analysisResult = new AnalysisResult(result.size(), (int)rightNum, result.size() - (int)rightNum, (rightNum / result.size()) * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return analysisResult;
    }
}
