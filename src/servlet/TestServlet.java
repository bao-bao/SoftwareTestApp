package servlet;/* Created by AMXPC on 2017/3/31. */

import Util.FileUploader;
import test.AnalysisResult;
import test.TestDate;
import test.TestTriangle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = {"/Test"})
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUploader fileUploader = new FileUploader(request);
        String testedName = (String)request.getAttribute("project");
        String tester = (String)request.getAttribute("tester");
        AnalysisResult analysisResult = null;
        switch (testedName) {
            case "Triangle":
                TestTriangle testTriangle = new TestTriangle(fileUploader.getUploadFile(),
                        fileUploader.getFilePath() +"\\Test" + fileUploader.getFileName(), tester);
                analysisResult = testTriangle.doTest();
                break;
            case "Date":
                TestDate testDate = new TestDate(fileUploader.getUploadFile(),
                        fileUploader.getFilePath() +"\\Test" + fileUploader.getFileName(), tester);
                analysisResult = testDate.doTest();
                break;
            case "Salary":
                //TODO: add Salary calculate test
                break;
            default:
                break;
        }
        if(analysisResult != null) {
            analysisResult.setAttribute(request);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("SoftwareTesting.jsp").forward(request, response);
    }
}
