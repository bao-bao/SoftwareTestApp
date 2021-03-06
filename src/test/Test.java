package test;/* Created by AMXPC on 2017/3/16. */

import java.io.File;

abstract class Test {
    File testDataFile;
    String testResultFilePath;
    String tester;

    Test(File testDataFile, String testResultFilePath, String tester) {
        this.testDataFile = testDataFile;
        this.testResultFilePath = testResultFilePath;
        this.tester = tester;
    }

    abstract public AnalysisResult doBatchTest();

    public Object doSingleTest(String[] parameters) {
        Object result;
        try {
            result = this.invoke(parameters);
        } catch (Exception e) {
            return "Error: Wrong arguments format";
        }
        return result;
    }

    abstract protected Object invoke(Object args) throws Exception;
}
