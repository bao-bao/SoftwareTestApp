/* Created by AMXPC on 2017/3/15. */

import java.util.ArrayList;

public class TestTriangle extends Test {

    public TestTriangle(String testDataFile, String testResultFile) {
        super(testDataFile, testResultFile);
    }

    public AnalysisResult doTest() {
        Executor executor = new Executor(testDataFile, testResultFile, "Triangle");
        AnalysisResult analysisResult = null;
        try {
            ArrayList<Object> result = executor.execute(this, TestTriangle.class.getMethod("invoke", Object.class), 3);
            analysisResult = executor.write(result, 4);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return analysisResult;
    }

    public String invoke(Object args) throws NumberFormatException {
        String[] argdata = (String[]) args;
        double arg1 = Double.valueOf(argdata[0]);
        double arg2 = Double.valueOf(argdata[1]);
        double arg3 = Double.valueOf(argdata[2]);
        return JudgeTriangle.judgeTriangle(arg1, arg2, arg3);
    }
}