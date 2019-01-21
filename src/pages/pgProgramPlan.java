package pages;
/**************************************************************************************/
/* Page class name: pgPracticeHistory                                                 */
/* Description    : This has objects identified in the Program Plan tab		          */
/* Created by	  : Reza T                                                            */
/* CHANGE HISTORY																	  */
/*------------------------------------------------------------------------------------*/
/* Change Summary : <description>                                                     */
/* Changed by	  : <name>          Changed On: dd/mm/yyyy                            */
/**************************************************************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class pgProgramPlan{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgProgramPlan(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void gotoProgramPlanTab(){     
    	driver.findElement(By.xpath("//span[@class='title' and text()='Program Plan'] ")).click();
	}
     	
}