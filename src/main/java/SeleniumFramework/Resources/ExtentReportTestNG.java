package SeleniumFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentReports getReportObject() {
		// ExtentSparkReporter: -it is a helper attribute to create html file then send
		// it to ExtentReports
		// ExtentReports:It is responsible for creating extent reports

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Narasimha Kotakonda");
		
		return extent;
	}

}
