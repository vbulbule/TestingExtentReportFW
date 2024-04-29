package reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.DriverInstannce;
import wrapper.GenericWrappers;

public class HtmlReporter extends DriverInstannce {
	
	// to generat e the extent HTML report
	// capture the screenshot
	// capture the every performing step
	private static ExtentReports extent;
	public String testcaseName,testcaseDec, author, category,Status="FAIL";
	static long snapNumber = 100000L;
	
	// extent report config
	
	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> parent = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> child = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ITestContext> context = new ThreadLocal<ITestContext>();
	
	String Time=getCurrentTime().replace("-","_").replace(":","_").replace(" ","_");
	
	protected static final ThreadLocal<String> tName= new ThreadLocal<String>();
	
	protected static final ThreadLocal<String> errorMessag= new ThreadLocal<String>();
	protected static final ThreadLocal<Integer>sStepNumber= new ThreadLocal<>();
	protected static final ThreadLocal<String> skipped = new ThreadLocal<String>();
	protected static final ThreadLocal<String> primaryWindowHandle= new ThreadLocal<String>();
	protected static final ThreadLocal<Set<String>> primaryWindowHandles= new ThreadLocal<Set<String>>();
	
	protected static final ThreadLocal<LinkedHashMap<Integer, String[]>> reports= new ThreadLocal<LinkedHashMap<Integer, String[]>>();
	LinkedHashMap<Integer, String[]> report=new LinkedHashMap<Integer, String[]>();
	
	List<String> message=new ArrayList<>();
	protected static final ThreadLocal<List<String>> messages= new ThreadLocal<List<String>>();
	
	public static String suiteName;
	
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) {
		HtmlReporter.context.set(context);
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		System.out.println("Suite Name is :"+suiteName);
		//String fileName = "target/reports/"+suiteName+"_"+Time+".html";
		String fileName = "target/reports/"+suiteName+".html";
		
		File f = new File("./reports");
		if(! f.exists()) {
			f.mkdir();
		}
		
	     ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	     htmlReporter.config().setTheme(Theme.STANDARD);
	     htmlReporter.config().setDocumentTitle("FaceBook Automation");
	     htmlReporter.config().setEncoding("utf-8");
	     htmlReporter.config().setReportName("FB Automation");
	     extent = new ExtentReports();
	     extent.attachReporter(htmlReporter);
	     extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) {
		System.out.println(" -- in before class -----");
		errorMessag.set("");
		sStepNumber.set(1);
		skipped.set("");
		HtmlReporter.context.set(context);
		parent.set(extent.createTest(testcaseName, testcaseDec));
		parent.get().assignCategory(category);
		parent.get().assignAuthor(author);
		parentTest.set(parent.get());
		tName.set(testcaseName);
		tName.set(author);
		tName.set(testcaseName);
		reports.set(report);
		messages.set(message);
		setNode();
	}
	
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return sdf.format(date);
	}
	
	public synchronized void setNode() {
		child.set(parentTest.get().createNode(testcaseName));
		test.set(child.get());
	}
	public synchronized void assignCategory(String category)
	{
		parent.get().assignCategory(category);
	}
	public synchronized void createTest() {
		System.out.println(" in create test");
		parent.set(extent.createTest(testcaseName,testcaseDec));
		parent.get().assignCategory(category);
		parent.get().assignAuthor(author);
		parentTest.set(parent.get());
		
	}
	
	public void report (String dec, String status, boolean bSnap) {
		
		synchronized(tName) {
			Media img = null;
			if(bSnap && !status.equalsIgnoreCase("INFO")){
				long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
				File screenshotAs = getDriver().getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(screenshotAs, new File("./reports/images/"+number+".jpg"));
				}
				catch (IOException e1) {
					System.err.println("snap not able to capture: " + e1.getMessage());
				}
				
				try {
					img = MediaEntityBuilder.createScreenCaptureFromPath("../../reports/images/"+number+".jpg").build();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(status.equalsIgnoreCase("SUCCESS")) {
				test.get().pass(dec,img);
			}
			else if(status.equalsIgnoreCase("FAILURE")) {
				messages.get().add(dec);
				test.get().fail(dec,img);
				errorMessag.set(errorMessag.get()+","+dec);
				throw new RuntimeException("See the report there are failures");
			}
			else if(status.equalsIgnoreCase("WARNING")) {
				messages.get().add(dec);
				test.get().warning(dec,img);
				Status = "PARTIALLY PASS";
				errorMessag.set(errorMessag.get()+","+dec);
			}
			else if (status.equalsIgnoreCase("INFO"))
			{
				messages.get().add(dec);
				test.get().info(dec,img);
				Status = "INFO";
			}
			else {
				Status = "ERROR";
				test.get().fail(dec,img);
				throw new RuntimeException("There is Error in Test Case please check");
			}
			
		}
	}
	
	
	
	@AfterMethod(alwaysRun = true)
	public void updateStatus(ITestResult result) {
		
		if((Status.equalsIgnoreCase("PASS") && !errorMessag.get().trim().isEmpty()) ||(Status.equalsIgnoreCase("PARTIALLY PASSED"))) {
			Status = "PARTIALLY PASSED";
			test.get().log(com.aventstack.extentreports.Status.WARNING, "PARTIALLY PASSED");
			
		}
		else if(Status.equalsIgnoreCase("PASS")) {
			test.get().log(com.aventstack.extentreports.Status.PASS, "PASSED");
		}
		else if((Status.equals("FAIL") && errorMessag.get().trim().equals(""))||(Status.equals("ERROR"))) {
			Status = "ERROR";
			test.get().log(com.aventstack.extentreports.Status.FAIL, result.getThrowable());
		}
		else if(Status.equals("FAIL")){
			test.get().log(com.aventstack.extentreports.Status.FAIL, result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.get().log(com.aventstack.extentreports.Status.SKIP, "SKIPPED");
		}
	}
	
	
	@AfterClass(alwaysRun = true)
	public synchronized void afterClass() {
//		if(skipped.get().equals("")){
//		 Status="NOT EXECUTED";
//		 test.get().log(com.aventstack.extentreports.Status.SKIP, "Test Case is SKIPPED because previous test case is Not Executed");
//	    }
		
		extent.flush();
		
	}
	
	@AfterSuite(alwaysRun = true)
	public synchronized void afterSuite() {
		try
		{
			System.out.println("------before flush-------");
			extent.flush(); 
			System.out.println("------after flush-------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (getDriver() != null) {
				getDriver().quit();
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
	

