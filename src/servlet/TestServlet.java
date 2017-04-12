package servlet;/* Created by AMXPC on 2017/3/31. */

import Util.FileUploader;
import test.AnalysisResult;
import test.TestDate;
import test.TestSalary;
import test.TestTriangle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "TestServlet", urlPatterns = {"/Test"})
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUploader fileUploader = new FileUploader(request);
        String resultFilePath = fileUploader.getFilePath() +"\\Test" + fileUploader.getFileName();
        String testedName = (String)request.getAttribute("project");
        String tester = (String)request.getAttribute("tester");
        AnalysisResult analysisResult = null;
        switch (testedName) {
            case "Triangle":
                TestTriangle testTriangle = new TestTriangle(fileUploader.getUploadFile(), resultFilePath, tester);
                analysisResult = testTriangle.doTest();
                break;
            case "Date":
                TestDate testDate = new TestDate(fileUploader.getUploadFile(), resultFilePath, tester);
                analysisResult = testDate.doTest();
                break;
            case "Salary":
                TestSalary testSalary = new TestSalary(fileUploader.getUploadFile(), resultFilePath, tester);
                analysisResult = testSalary.doTest();
                break;
            default:
                // TODO: more test source here
                break;
        }
        if(analysisResult != null) {
            analysisResult.setAttribute(request);
            String resultFileName = "Test" + fileUploader.getFileName();
            request.setAttribute("resultFile", resultFileName);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求文件名
        String filename = request.getParameter("filename");
        if(filename != null) {
            //设置文件MIME类型
            response.setContentType(getServletContext().getMimeType(filename));
            //设置Content-Disposition
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            //读取目标文件，通过response将目标文件写到客户端
            //获取目标文件的绝对路径
            String fullFileName = getServletContext().getRealPath("upload") + "\\" + filename;
            //读取文件
            InputStream in = new FileInputStream(fullFileName);
            OutputStream out = response.getOutputStream();
            //写文件
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        }
        request.getRequestDispatcher("SoftwareTesting.jsp").forward(request, response);
    }
}
