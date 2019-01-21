package pages;

/**************************************************************************************/
/* Page class name: pgPracticeAssignment                                              */
/* Description    : This has objects identified for the popup of practice assignment  */
/* Created by	  : Reza T                                                            */
/* CHANGE HISTORY																	  */
/*------------------------------------------------------------------------------------*/
/* Change Summary : <description>                                                     */
/* Changed by	  : <name>          Changed On: dd/mm/yyyy                            */
/**************************************************************************************/

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pgPracticeAssignment{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgPracticeAssignment(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void gotoPracticeAssignment(){     
		WebElement menuLearnreflection = driver.findElement(By.xpath("//button[@class='btn btn-cluey-contentnav' and text()='Practice Assignment'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", menuLearnreflection);
	}
    
    public void setPracticeQtns(){  
        List<WebElement> drpDwnQuestion = driver.findElements(By.xpath("//button[@class='dropdown-item' and text()='1']"));
        executor = (JavascriptExecutor)driver;
        for ( WebElement el : drpDwnQuestion ) {
            if ( !el.isSelected() ) {
                executor.executeScript("arguments[0].click()", el);
            }
        }
	}
    
    public void clickSaveNExit(){     
		WebElement btnSaveNExit = driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='Save and exit'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnSaveNExit);
	}
    	
}