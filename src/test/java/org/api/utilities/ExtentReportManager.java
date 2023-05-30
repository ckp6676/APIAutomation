package org.api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter; // this is UI
    public ExtentReports extent;
    public ExtentTest test;
    String repName;
    public void onStart(ITestContext testContext){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timeStamp+"html";

        sparkReporter = new ExtentSparkReporter(".\\extentreports\\"+repName);//location of report
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//title of report
        sparkReporter.config().setReportName("Pet store Uses API");//name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports(); // we are creating object of extent for creating reports
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Pet store Uses API");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Enviroment","QA");
        extent.setSystemInfo("user","chirag");

    }

    public void onTestSuccess(ITestResult result){
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");
    }
    public void onTestFailure(ITestResult result){
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }
    public void onTestSkipped(ITestResult result){
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }
    public void onFinish(ITestContext result){
        extent.flush();
    }
}
