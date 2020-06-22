package Ravi_Auto.Auto_Car;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*; 
import java.io.*; 

public class Cartest {
	WebDriver driver;
	String rno;
	@BeforeClass
	public void first()
	{
		 System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chomedriver\\chromedriver.exe");
		   driver=new ChromeDriver();
		  driver.get("https://cartaxcheck.co.uk/");
	}
	 @BeforeTest
	  public void second() throws IOException {
		  PrintWriter pw = new PrintWriter("output.txt"); 
		  
	      // Regular expression for mobile number 
		  Pattern p = Pattern.compile("([A-Z]+[0-9]{2}+\\s+[A-Z]+{3})|([A-Z]+[0-9]{2}+[A-Z]+{3})"); 

	      // BufferedReader for reading from input.txt file 
	      BufferedReader br = new BufferedReader 
	                              (new FileReader("C:\\Program Files\\Selenium\\car_input.txt")); 
	      String line = br.readLine(); 
	        
	      while (line != null)  
	      { 
	          Matcher m = p.matcher(line); 

	          while (m.find())  
	          { 
	              // Write the mobile number to output.txt file 
	              pw.println(m.group()); 
	          } 

	          line = br.readLine(); 
	      } 
	      pw.flush(); 
	  }

  @Test(dataProvider = "dp")
  public void third(String regno) throws InterruptedException {
	 Thread.sleep(3000);
	  driver.findElement(By.id("vrm-input")).sendKeys(regno);
	  driver.findElement(By.className("jsx-3655351943")).click();
	  rno=regno;
	  Thread.sleep(10000);
}
  
  @DataProvider
  public Object[] dp() throws IOException {
	 ArrayList<String> inputData=new ArrayList<String>();	
	  BufferedReader br = new BufferedReader(new FileReader("output.txt")); 
String line;
while ((line= br.readLine()) != null)  
{
inputData.add(line);
}
Object[] data = inputData.toArray(); 
return data;
}
 
  @AfterMethod()
  public void fourth() throws IOException
  {
	  BufferedReader br = new BufferedReader(new FileReader("C:\\Program Files\\Selenium\\car_output.txt")); 
		 String line;
		 List<String>list=new ArrayList<>();
		 
		 br.readLine();
		 while ((line= br.readLine()) != null)  
		 {
			list.add(br.readLine());
			 
		
			 }
	  
	  List<WebElement>ddval=driver.findElements(By.xpath("//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl/dd"));
	 String vv=rno;
	  for(int i=1;i<5;i++)
	  {
		  vv+=","+ddval.get(i).getText();
		  	  
	  }
	  
	   
		 
		 if(list.contains(vv))
		 {
			System.out.println("pass"+vv);
		 }
		 else
		 {
			 
		 System.out.println("fail"+vv);
		 }
 	  driver.navigate().back();
}
   
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}

