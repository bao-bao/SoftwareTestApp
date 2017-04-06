package test;/* Created by AMXPC on 2017/3/16. */

import java.io.File;

abstract class Test {
    File testDataFile;
    String testResultFile;
    String tester;

    Test(File testDataFile, String testResultFile, String tester) {
        this.testDataFile = testDataFile;
        this.testResultFile = testResultFile;
        this.tester = tester;
    }

    abstract AnalysisResult doTest();
    abstract protected Object invoke(Object args) throws Exception;
}
