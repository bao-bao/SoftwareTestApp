package test;/* Created by AMXPC on 2017/3/15. */

import source.NextDate;

import java.io.File;
import java.util.ArrayList;

public class TestDate extends Test {

    public TestDate(File testDataFile, String testResultFile, String tester) {
        super(testDataFile, testResultFile, tester);
    }

    public AnalysisResult doTest() {
        Executor executor = new Executor(testDataFile, testResultFile, "Date");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestDate.class.getMethod("invoke", Object.class), 1);
            analysisResult = executor.write(result, 2, tester);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    public String invoke(Object args) throws Exception {
        String[] argdata = (String[]) args;
        String arg1 = argdata[0];
        return NextDate.getNextDate(arg1);
    }
}
