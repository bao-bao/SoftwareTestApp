/* Created by AMXPC on 2017/3/16. */

abstract class Test {
    String testDataFile;
    String testResultFile;

    Test(String testDataFile, String testResultFile) {
        this.testDataFile = testDataFile;
        this.testResultFile = testResultFile;
    }

    abstract AnalysisResult doTest();
    abstract protected Object invoke(Object args) throws Exception;
}
