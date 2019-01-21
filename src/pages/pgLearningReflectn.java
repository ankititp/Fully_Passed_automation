package pages;
/**************************************************************************************/
/*                                                                                    */
/* Page class name: pgLearningReflectn                                                */
/* Description    : This has objects identified for the popup of learning reflection  */
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
import java.util.List;

public class pgLearningReflectn{
	WebDriver driver;
	JavascriptExecutor executor;
	
    public pgLearningReflectn(WebDriver driver)	{
        this.driver = driver;
    }
    
    public void gotoLearningReflectn(){     
		WebElement menuLearnreflection = driver.findElement(By.xpath("//button[@class='btn btn-cluey-contentnav' and text()='Learning reflection'] "));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", menuLearnreflection);
	}
    
    public void selectAllChkBoxes(){  
        List<WebElement> chkbxGoals = driver.findElements(By.xpath("//input[@type='checkbox']"));
        executor = (JavascriptExecutor)driver;
        for ( WebElement el : chkbxGoals ) {
            if ( !el.isSelected() ) {
                executor.executeScript("arguments[0].click()", el);
            }
        }
	}

    public void clickSaveNExit(){     
		WebElement btnSaveNExit = driver.findElement(By.xpath("//button[@class='btn btn-outline-primary']"));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnSaveNExit);
	}
    
    public void clickSaveNSetPractice(){     
		WebElement btnSaveNSetPractice = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", btnSaveNSetPractice);
	}
    
	
}