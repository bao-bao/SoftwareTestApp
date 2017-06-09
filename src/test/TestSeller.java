package test;

/* Created by AMXPC on 2017/6/9. */

import source.Seller;

import java.io.File;
import java.util.ArrayList;

public class TestSeller extends Test {

    public TestSeller(File testDataFile, String testResultFilePath, String tester) {
        super(testDataFile, testResultFilePath, tester);
    }

    @Override
    public AnalysisResult doBatchTest() {
        Executor executor = new Executor(testDataFile, testResultFilePath, "Seller");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestSeller.class.getMethod("invoke", Object.class), 2);
            analysisResult = executor.write(result, 4, tester);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    @Override
    protected Object invoke(Object args) throws Exception {
        String[] argdata = (String[]) args;
        double arg1 = Double.valueOf(argdata[0]);
        int arg2 = Integer.valueOf(argdata[1]);
        double arg3 = Double.valueOf(argdata[2]);
        return Seller.commission(arg1, arg2, arg3);
    }
}
