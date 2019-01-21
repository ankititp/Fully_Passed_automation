package pages;
/**************************************************************************************/
/*                                                                                    */
/* Page class name: pgLearningObj                                                     */
/* Description    : This has objects identified for the popup of setting learning     */
/*                  objectives                                                        */
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

public class pgLearningObj{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgLearningObj(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void gotoLearningGoals(){     
		WebElement menuLearnGoals = driver.findElement(By.xpath("//button[@class='btn btn-cluey-contentnav' and text()='Learning Goals'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", menuLearnGoals);
	}
    
    public void enterAGoal(String strGoalName){     
    	driver.findElement(By.xpath("//input[@placeholder='Enter goal...'] ")).sendKeys(strGoalName);
    }
    
    public void addGoal(){                                            
		//WebElement btnPlus = driver.findElement(By.xpath("//div[@class='icon icon-cs-plus::after'] "));
		WebElement btnPlus = driver.findElement(By.xpath("//button[@class='btn btn-outline-success rounded-circle'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnPlus);
    }
            
    public void saveAndExitGoals(){     
		WebElement btnSaveGoals = driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='Save and exit'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnSaveGoals);
    }
	
}
