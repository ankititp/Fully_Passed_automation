package pages;
/**************************************************************************************/
/* Page class name: pgChatWindow                                                      */
/* Description    : This has objects identified for the popup of the chat window      */
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

public class pgChatWindow{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgChatWindow(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void clickChatIcon(){     
    	driver.findElement(By.xpath("//span[@id='chat_but']")).click();
	}
    
    public void enterChatText(String strChatTxt){ 
    	WebElement chatTextbox = driver.findElement(By.xpath("//input[@id='chat_input']"));
    	chatTextbox.sendKeys(strChatTxt);
    	chatTextbox.sendKeys(Keys.ENTER);
	}
    	
}