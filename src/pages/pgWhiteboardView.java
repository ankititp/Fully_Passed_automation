package pages;
/**************************************************************************************/
/* Page class name: pgWhiteboardView                                                  */
/* Description    : This has objects that zoom in/out undo/re-do the white board      */
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

public class pgWhiteboardView{
	WebDriver driver;
	JavascriptExecutor executor;

    public pgWhiteboardView(WebDriver driver)	{
        this.driver = driver;
    }

    public void undo(){     	
    	executor = (JavascriptExecutor)driver;     
	    WebElement undo = driver.findElement(By.xpath("//*[@class='d-flex one-third justify-content-end align-self-center align-items-center flex-grow pr-5 max-height']/i[1]"));
	    executor.executeScript("arguments[0].click()", undo);
	}
    
    public void redo(){     	
    	executor = (JavascriptExecutor)driver;     
	    WebElement redo = driver.findElement(By.xpath("//*[@class='d-flex one-third justify-content-end align-self-center align-items-center flex-grow pr-5 max-height']/i[2]"));
	    executor.executeScript("arguments[0].click()", redo);
	}
    
    public void zoomOut(int zoomCount){     	
    	executor = (JavascriptExecutor)driver;     
	    //zoom out (-) the white board
	    WebElement zoom_out = driver.findElement(By.xpath("//*[@class='d-flex one-third justify-content-end align-self-center align-items-center flex-grow pr-5 max-height']/i[3]"));
	    for(int i=1; i<=zoomCount; i++)
	    {
	    	executor.executeScript("arguments[0].click()", zoom_out);
	    }
	}
    
    public void zoomIn(int zoomCount){     	
    	executor = (JavascriptExecutor)driver;     
	    //zoom out (-) the white board
	    WebElement zoom_in = driver.findElement(By.xpath("//*[@class='d-flex one-third justify-content-end align-self-center align-items-center flex-grow pr-5 max-height']/i[4]"));
	    for(int i=1; i<=zoomCount; i++)
	    {
	    	executor.executeScript("arguments[0].click()", zoom_in);
	    }
	}
    
    public String getZoomVal(){   
    	/****************************** UNDER CONSTRUCTION */
    	executor = (JavascriptExecutor)driver;
	    //zoom out (-) the white board
	    WebElement zoom_in = driver.findElement(By.xpath("//*[@class='d-flex one-third justify-content-end align-self-center align-items-center flex-grow pr-5 max-height']/i[5]"));
	    String intZoomValue = (String) executor.executeScript("arguments[0].getText()", zoom_in);
	    return intZoomValue;
	}
    

    	
}