package servlet;/* Created by AMXPC on 2017/3/31. */

import Util.FileUploader;
import test.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "TestServlet", urlPatterns = {"/Test"})
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parameters for single test
        String testInput = request.getParameter("testInput");
        String[] singleTestParameters = null;
        boolean isSingleTest = false;
        String singleResult = null;
        // parameters for batch test
        FileUploader fileUploader = null;
        String resultFilePath = null;
        AnalysisResult analysisResult = null;

        if(request.getParameter("testInput") != null) {
            singleTestParameters = testInput.split(",");
            isSingleTest = true;
        }
        else {
            fileUploader = new FileUploader(request);
            resultFilePath = fileUploader.getFilePath() + "\\Test" + fileUploader.getFileName();;
        }
        // test which module
        String testedName = request.getAttribute("project") == null ? request.getParameter("project_") : (String) request.getAttribute("project");
        String tester = (String) request.getAttribute("tester");
        HttpSession session = request.getSession(true);
        session.setAttribute("project", testedName);
        session.setAttribute("sInput", testInput);

        switch (testedName) {
            case "Triangle":
                TestTriangle testTriangle = new TestTriangle(fileUploader != null ? fileUploader.getUploadFile() : null, resultFilePath, tester);
                if(isSingleTest) {
                    singleResult = testTriangle.doSingleTest(singleTestParameters).toString();
                } else {
                    analysisResult = testTriangle.doBatchTest();
                }
                break;
            case "Date":
                TestDate testDate = new TestDate(fileUploader != null ? fileUploader.getUploadFile() : null, resultFilePath, tester);
                if(isSingleTest) {
                    singleResult = testDate.doSingleTest(singleTestParameters).toString();
                } else {
                    analysisResult = testDate.doBatchTest();
                }
                break;
            case "Salary":
                TestSalary testSalary = new TestSalary(fileUploader != null ? fileUploader.getUploadFile() : null, resultFilePath, tester);
                if(isSingleTest) {
                    singleResult = testSalary.doSingleTest(singleTestParameters).toString();
                } else {
                    analysisResult = testSalary.doBatchTest();
                }
                break;
            case "Mobile":
                TestPhone testPhone = new TestPhone(fileUploader != null ? fileUploader.getUploadFile() : null, resultFilePath, tester);
                if(isSingleTest) {
                    singleResult = testPhone.doSingleTest(singleTestParameters).toString();
                } else {
                    analysisResult = testPhone.doBatchTest();
                }
                break;
            case "Seller":
                TestSeller testSeller = new TestSeller(fileUploader != null ? fileUploader.getUploadFile() : null, resultFilePath, tester);
                if(isSingleTest) {
                    singleResult = testSeller.doSingleTest(singleTestParameters).toString();
                } else {
                    analysisResult = testSeller.doBatchTest();
                }
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
        else {
            request.setAttribute("singleResult", singleResult);
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
