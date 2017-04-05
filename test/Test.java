/* Created by AMXPC on 2017/3/16. */

abstract class Test {
    String testDataFile;
    String testResultFile;
    String tester;

    Test(String testDataFile, String testResultFile, String tester) {
        this.testDataFile = testDataFile;
        this.testResultFile = testResultFile;
        this.tester = tester;
    }

    abstract AnalysisResult doTest();
    abstract protected Object invoke(Object args) throws Exception;
}
