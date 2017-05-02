package test;

/* Created by AMXPC on 2017/5/2. */

import source.MobilePhone;

import java.io.File;
import java.util.ArrayList;

public class TestPhone extends Test {

    public TestPhone(File testDataFile, String testResultFilePath, String tester) {
        super(testDataFile, testResultFilePath, tester);
    }

    public AnalysisResult doTest() {
        Executor executor = new Executor(testDataFile, testResultFilePath, "MobilePhone");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestPhone.class.getMethod("invoke", Object.class), 2);
            analysisResult = executor.write(result, 3, tester);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    public Object invoke(Object args) throws NumberFormatException {
        String[] argdata = (String[]) args;
        int arg1 = Integer.valueOf(argdata[0]);
        int arg2 = Integer.valueOf(argdata[1]);
        return MobilePhone.expense(arg1, arg2);
    }
}
