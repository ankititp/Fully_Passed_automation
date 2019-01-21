package lppackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;      //~for explicit waits
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.pgLearningObj;
import pages.pgLearningReflectn;
import pages.pgChatWindow;
import pages.pgPracticeAssignment;
import pages.pgPracticeHistory;
import pages.pgWhiteboard;
import pages.pgWhiteboardView;
import pages.pgProgramPlan;


public class LpPoCTest{
	
	String sessionurl="https://staging-demo.clueylearning.com/generator/index.html";
	String tutorUrl;
	String studentUrl;
	ExtentReports extent;
	ExtentTest logger;
	WebDriverWait myWaitVar;
	WebDriver driver;
	WebDriver driversession;
	ChromeOptions options;
	DateFormat dateFormat = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSS");
	Date date = new Date();
	
	@BeforeTest
	public void beforeTest()  throws Exception {
		
		//Create tokens from demo
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		// Create object of ChromeOption class
		ChromeOptions optionse = new ChromeOptions();
		driversession =new ChromeDriver(optionse);
		driversession.get(sessionurl);
		driversession.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driversession.manage().window().maximize();
		Select select = new Select(driversession.findElement(By.id("packageMasterCode")));
		select.selectByVisibleText("Demo Primary Maths");
		Select selectlength = new Select(driversession.findElement(By.id("duration")));
		selectlength.selectByValue("45");
		//Click on generate tokens button
		        
		driversession.findElement(By.xpath("//button[@id='submit']")).click();
		driversession.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Thread.sleep(2000); 
		tutorUrl   = driversession.findElement(By.xpath("/html/body/div/div/ul[1]/li/a")).getText();
		studentUrl = driversession.findElement(By.xpath("/html/body/div/div/ul[2]/li/a")).getText();

		driversession.close();
		
        // Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();
        // Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();
        // Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver =new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(tutorUrl);
		
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		extent
         .addSystemInfo("Host Name", "Cluey Learning")
         .addSystemInfo("Environment", "STAGING")
         .addSystemInfo("User Name", "Tutor_RTQA");
         //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
         //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
         extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
		
	@Test(priority = 0, enabled = true)
	public void verifyLandingPageTitle() throws Exception  {
		//String expTutWelcome = "Hello, TutTester!";
		//String expTutsStudent = "Your student today is Tester";
		logger = extent.startTest("Log into a learning session");
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		//myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Give Access']")));
		Thread.sleep(4000); 
		driver.findElement(By.xpath("//button[text()='Give Access']")).click();
		driver.manage().timeouts().implicitlyWait(43,TimeUnit.SECONDS);
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//button[text()='Save Settings & Get Started']")).click();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		//Verify landing page title is "Cluey"
		Assert.assertEquals("Cluey", driver.getTitle(), "Session landing page Title in not Cluey");
		logger.log(LogStatus.PASS, "Session landing page loaded with the page title Cluey");
		Thread.sleep(2000);
	}
		
	@Test(priority = 1, enabled = true)
	public void enteringLEARNING_GOALS() throws Exception  {
		logger = extent.startTest("Entering a Learning goal");
		pgLearningObj objLearningGaols = new pgLearningObj(driver);
		Thread.sleep(2000);
		objLearningGaols.gotoLearningGoals();
		Thread.sleep(2000);
        objLearningGaols.enterAGoal("Automated goal entry 3 on " + dateFormat.format(date));  //Enters text to goal 
        Thread.sleep(2000);
		//objLearningGaols.addGoal();    /*UNDER CONSTRUCTION: The (+) button click does not work, cannot determine the on hover object*/
		objLearningGaols.saveAndExitGoals();
		logger.log(LogStatus.PASS, "A learning goal entered and saved");
		Thread.sleep(1000);
	}	 


	@Test(priority = 3, enabled = true)
	public void verifyNoPracticeAssigned() throws InterruptedException  {
		logger = extent.startTest("Verify No Practice is assigned in the first session");
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//span[@class='title' and text()='Practice'] ")).click();
		pgPracticeHistory objPracticeHistory = new pgPracticeHistory(driver);
		objPracticeHistory.gotoPracticeTab();
		Thread.sleep(2000);
		String strActualPractice = driver.findElement(By.xpath("//div[@class='text-center p-4']")).getText();
		String strExpectedPractice = "No practice set has ever been assigned yet.\nGoing forward, you will access here the practice history.";
		Assert.assertEquals(strActualPractice,strExpectedPractice, "Verify message when no practice assigned");
		logger.log(LogStatus.PASS, "Since no practice has been assiged the message: \'No practice set has ever been assigned yet.\nGoing forward, you will access here the practice history.\' is displayed.");
	
	
	}
	
	@Test(priority = 4, enabled = true)
	public void assginPracticeVia_PRACTICE_ASSIGNMENT() throws Exception  {
		logger = extent.startTest("Assign Practice via Practice Assignment");
		
		pgProgramPlan objProgramPlan= new pgProgramPlan(driver);
		objProgramPlan.gotoProgramPlanTab();
		
		pgPracticeAssignment objPracticeAssignment = new pgPracticeAssignment(driver);
		objPracticeAssignment.gotoPracticeAssignment();
		Thread.sleep(2000);
		objPracticeAssignment.setPracticeQtns();
		logger.log(LogStatus.PASS, "1 Question each for every concept selected");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "PracticeQuestionsTicked")));
		Thread.sleep(2000);
		objPracticeAssignment.clickSaveNExit();
		Thread.sleep(1000);
		logger.log(LogStatus.PASS, "Practice assigned via practice assignment");
		//verify 4 questions selected
		//objPracticeAssignment.gotoPracticeAssignment();
		//String strActQuesAssigned = driver.findElement(By.xpath("//span[@class='float-right text-primary']")).getText();  /*not working*/
		//String strExpQuesAssigned = "6\n questions\n selected";
		//System.out.print("Message:  " + strActQuesAssigned);
		//Assert.assertEquals(strActQuesAssigned,strExpQuesAssigned, "Verify the number of questions selected in practice assignment");
		//Thread.sleep(1000);
		//objPracticeAssignment.clickSaveNExit();
	}
	 
	@Test(priority = 5, enabled = true)
	public void verifyNextPracticeHistSummary() throws InterruptedException  {
		logger = extent.startTest("Verify Next Practice Summary");
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//span[@class='title' and text()='Practice'] ")).click();
		pgPracticeHistory objPracticeHistory = new pgPracticeHistory(driver);
		objPracticeHistory.gotoPracticeTab();
		Thread.sleep(2000);
		
		String strActPracticeLine1 = driver.findElement(By.xpath("//div[@class='container-text-first-line']")).getText();
		String strExpPracticeLine1 = "Next practice";
		Assert.assertEquals(strActPracticeLine1,strExpPracticeLine1, "Verify Next Practice is displayed");	
		logger.log(LogStatus.PASS, "\'Next Practice\' text is displayed as the first line of the practice history summary tab");
		
		String strActPracticeLine2 = driver.findElement(By.xpath("//div[@class='container-text-second-line']")).getText();
		String strExpPracticeLine2 = "3 questions across 3 concepts";
		Assert.assertEquals(strActPracticeLine2,strExpPracticeLine2, "Verify Next Practice questions and course counts");
		logger.log(LogStatus.PASS, "\'3 questions across 3 concepts\' text is displayed as the second line of the practice history summary tab");
		Thread.sleep(2000);
		pgProgramPlan objProgramPlan= new pgProgramPlan(driver);
		objProgramPlan.gotoProgramPlanTab();
		Thread.sleep(1000);
	}
	  
	@Test(priority = 6, enabled = true)
	public void drawOnCanvasWithPen() throws Exception  {
		logger = extent.startTest("Whiteboard Activity");
		Thread.sleep(1000);
		pgWhiteboard objWhiteboard = new pgWhiteboard(driver);
		//Enter text with Multiline text editor
		objWhiteboard.drawWithTextEditor(400,200,"This text is written by:\nTHE AUTOMATION SUITE\n\nLine 1\nLine 2\nLine 3");
		logger.log(LogStatus.PASS, "Multi line text written");
		Thread.sleep(1000);
		objWhiteboard.drawWithPen(150,150,200,200);
		Thread.sleep(1000);
		objWhiteboard.drawWithPen(150,350,200,-200);
		Thread.sleep(1000);
		logger.log(LogStatus.PASS, "X drawn with the pen");
	}
	
	@Test(priority = 7, enabled = true)
	public void undoFunctionalities() throws Exception  {
		logger = extent.startTest("Undo redo functionalities in the whiteboard");
		pgWhiteboardView objWhiteboardView = new pgWhiteboardView(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "beforeUndo")));
		objWhiteboardView.undo();
		logger.log(LogStatus.PASS, "Undo was done");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "afterUndo")));
		Thread.sleep(1000);
		objWhiteboardView.redo();
		logger.log(LogStatus.PASS, "Redo was done");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "afterRedo")));
	}
	
	@Test(priority = 8, enabled = true)
	public void zoomFunctionalities() throws Exception  {
		logger = extent.startTest("Zooming functionalities in the whiteboard");
		pgWhiteboardView objWhiteboardView = new pgWhiteboardView(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "beforeZooms")));
		objWhiteboardView.zoomOut(2);
		logger.log(LogStatus.PASS, "Zoom out was done");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "zoomOut")));
		Thread.sleep(2000);
		objWhiteboardView.zoomIn(4);
		logger.log(LogStatus.PASS, "Zoom in was done");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "zoomIn")));
	} 
	
	@Test(priority = 9, enabled = true)
	public void ChatFunctionality() throws InterruptedException  {
		logger = extent.startTest("Tutor sending chats");
		pgChatWindow objChatWindow = new pgChatWindow(driver);
		objChatWindow.clickChatIcon();
		Thread.sleep(2000);
		objChatWindow.enterChatText("Hi Student, Im your automated tutor for the session...");
		logger.log(LogStatus.INFO, "Tutor sent: \'Hi Student, Im your automated tutor for the session...\'");
		Thread.sleep(1000);
		objChatWindow.enterChatText("Do you have any question(s)");
		logger.log(LogStatus.INFO, "Tutor sent: \'Do you have any question(s)\'");
		Thread.sleep(1000);
		objChatWindow.clickChatIcon(); /*UNDER CONSTRUCTION: Clicking on the minimize window icon*/
		logger.log(LogStatus.PASS, "Tutor sent two chat messages");
	}
	
	@Test(priority = 10, enabled = true)
	public void submitingLEARNING_REFLECTION() throws Exception  {
		logger = extent.startTest("Submiting Learning Reflection");
		
		pgProgramPlan objProgramPlan= new pgProgramPlan(driver);
		objProgramPlan.gotoProgramPlanTab();
		
		pgLearningReflectn objLearningReflectn = new pgLearningReflectn(driver);
		
		objLearningReflectn.gotoLearningReflectn();
		Thread.sleep(5000);
		objLearningReflectn.selectAllChkBoxes();
		logger.log(LogStatus.PASS, "All Learning goals ticked");
		logger.log(LogStatus.INFO, logger.addScreenCapture(LpPoCTest.getScreenshot(driver, "LearningGoalsTicked")));
		Thread.sleep(1000);
		objLearningReflectn.clickSaveNExit();
		logger.log(LogStatus.PASS, "All Learning goals saved");
		Thread.sleep(1000);	
	}
	
	@AfterMethod
	public void getResult(ITestResult result)throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = LpPoCTest.getScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			String screenshotPath = LpPoCTest.getScreenshot(driver, result.getName());
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}
		
	@AfterTest
	public void afterTest() {
		extent.flush();
		extent.close();
	    driver.close();
	}
}
