package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String reportFileName = "TestReport.html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Author", "Shivaji Bajpai");
        extent.setSystemInfo("Employee ID", "1851");
        extent.setSystemInfo("Company", "Atmecs Inc");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test with the test method name
        test = extent.createTest(result.getMethod().getMethodName());
        // Assign author for the test
        test.assignAuthor("Shivaji Bajpai");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test status as PASS with green color label
        test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test status as FAIL with red color label
        test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
        // Log the failure details
        test.fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the extent reports to generate the report
        extent.flush();
    }

    // Other methods omitted for brevity
}
