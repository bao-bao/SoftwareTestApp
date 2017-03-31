/* Created by AMXPC on 2017/3/15. */

import java.util.ArrayList;

public class TestTriangle implements Test {
    public void doTest() {
        Executor executor = new Executor("testResource\\Triangle.xls", "testResource\\TriangleTest.xls", "Triangle");
        TestTriangle testTriangle = new TestTriangle();
        try {
            ArrayList<Object> result = executor.execute(testTriangle, TestTriangle.class.getMethod("invoke", Object.class), 3, 29);
            executor.write(result, 4);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String invoke(Object args) throws NumberFormatException {
        String[] argdata = (String[]) args;
        double arg1 = Double.valueOf(argdata[0]);
        double arg2 = Double.valueOf(argdata[1]);
        double arg3 = Double.valueOf(argdata[2]);
        return JudgeTriangle.judgeTriangle(arg1, arg2, arg3);
    }
}