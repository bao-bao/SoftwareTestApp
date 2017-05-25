package test;/* Created by AMXPC on 2017/3/15. */

import source.NextDate;

import java.io.File;
import java.util.ArrayList;

public class TestDate extends Test {

    public TestDate(File testDataFile, String testResultFilePath, String tester) {
        super(testDataFile, testResultFilePath, tester);
    }

    public AnalysisResult doBatchTest() {
        Executor executor = new Executor(testDataFile, testResultFilePath, "Date");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestDate.class.getMethod("invoke", Object.class), 1);
            analysisResult = executor.write(result, 2, tester);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    public Object invoke(Object args) throws Exception {
        String[] argdata = (String[]) args;
        String arg1 = argdata[0];
        return NextDate.getNextDate(arg1);
    }
}
