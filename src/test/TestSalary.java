package test;

/* Created by AMXPC on 2017/4/12. */
import source.CalculateSalary;

import java.io.File;
import java.util.ArrayList;

public class TestSalary extends Test {

    public TestSalary(File testDataFile, String testResultFilePath, String tester) {
        super(testDataFile, testResultFilePath, tester);
    }

    public AnalysisResult doTest() {
        Executor executor = new Executor(testDataFile, testResultFilePath, "Salary");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestSalary.class.getMethod("invoke", Object.class), 3);
            analysisResult = executor.write(result, 4, tester);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    public Object invoke(Object args) throws Exception {
        String[] argdata = (String[]) args;
        int arg1 = Integer.valueOf(argdata[0]);
        int arg2 = Integer.valueOf(argdata[1]);
        int arg3 = Integer.valueOf(argdata[2]);
        return CalculateSalary.calculateSalary(arg1, arg2, arg3);
    }
}
