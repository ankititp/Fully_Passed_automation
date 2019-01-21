package pages;
/**************************************************************************************/
/* Page class name: pgPracticeHistory                                                 */
/* Description    : This has objects identified in the practice history tab           */
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
import org.openqa.selenium.Keys;

public class pgPracticeHistory{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgPracticeHistory(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void gotoPracticeTab(){     
    	driver.findElement(By.xpath("//span[@class='title' and text()='Practice'] ")).click();
	}
    
    public void enterChatText(String strChatTxt){ 
    	WebElement chatTextbox = driver.findElement(By.xpath("//input[@id='chat_input']"));
    	chatTextbox.sendKeys(strChatTxt);
    	chatTextbox.sendKeys(Keys.ENTER);
	}
    	
}





