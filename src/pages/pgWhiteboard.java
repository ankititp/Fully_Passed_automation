package pages;
/**************************************************************************************/
/* Page class name: pgWhiteboard                                                      */
/* Description    : This has objects identified in the tools that carries out         */
/*                  activities in editing the white board                             */
/* Created by	  : Reza T                                                            */
/* CHANGE HISTORY																	  */
/*------------------------------------------------------------------------------------*/
/* Change Summary : <description>                                                     */
/* Changed by	  : <name>          Changed On: dd/mm/yyyy                            */
/**************************************************************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class pgWhiteboard{
	WebDriver driver;
	JavascriptExecutor executor;
    int xcord1;
    int ycord1;
	
    public pgWhiteboard(WebDriver driver)	{
        this.driver = driver;
    }
    
    //Drawing with a pen a straight line from the start coordinate to the end coordinate
    public void drawWithPen(int startX, int startY, int endX, int endY){     	
    	executor = (JavascriptExecutor)driver;
        WebElement canvas = driver.findElement(By.xpath("//div/div/div/canvas[@id='temp_canvas']"));
        //org.openqa.selenium.Point point = canvas.getLocation();
        WebElement pen =driver.findElements(By.xpath("//button[@class='cluey-tools-button']")).get(1);
        executor.executeScript("arguments[0].click()", pen);
        //xcord1 = point.getX()-450+startX;
        //ycord1 = point.getY()+startY;
        
        Actions builder2 = new Actions(driver);
        org.openqa.selenium.interactions.Action drawAction;
        //drawAction = builder2.moveToElement(canvas,xcord1,ycord1).clickAndHold().moveByOffset(endX,endY).release().build();
        drawAction = builder2.moveToElement(canvas,startX,startY).clickAndHold().moveByOffset(endX,endY).release().build(); 
        drawAction.perform();	
	}
    
    public void drawWithTextEditor(int cordX, int cordY, String textEntry) throws Exception{ 
		//clicking text button in whiteboard
		WebElement btnTextEditor =driver.findElements(By.xpath("//button[@class='cluey-tools-button']")).get(3);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnTextEditor);
		
		//Clicking in whiteboard 
		WebElement canvas = driver.findElement(By.xpath("//div/div/div/canvas[@id='temp_canvas']")); 
		//org.openqa.selenium.Point point = canvas.getLocation();
		//int xcord = point.getX();
		//int ycord = point.getY();
		Actions builder = new Actions(driver);   
		builder.moveToElement(canvas, cordX, cordY).click().build().perform();
		 
		Thread.sleep(2000);
		 
		//entering text in text area
		driver.findElement(By.xpath("//textarea[@id='textinput']")).sendKeys(textEntry);
    }
    	
}