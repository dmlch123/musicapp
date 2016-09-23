package music_03_JAMM;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_03_메뉴링크 extends Utilites{
	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	@Test
	public void 메뉴링크_HOT() throws Exception{
		System.out.println("메뉴링크_HOT_테스트 시작!!");
		//태그end 최초진입시 가이드창 닫기
		driver.findElementById(Module.TagTitleID).click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.navigate().back();
		
		util.위아래_스크롤(0.76);
		Thread.sleep(1500);
		
		driver.findElementByXPath(Module.TagHomeFooterPopularID).click();
		WebElement 메뉴링크_페이지 = driver.findElementById(Module.TitleTextID);
		assertEquals("JAMM", 메뉴링크_페이지.getText());
		
		Thread.sleep(2000);
		
        WebElement HOT = driver.findElementByXPath(Module.TextClass+"[@text='HOT']");         
        util.선택확인(HOT);
		util.노출확인(Module.ListviewClass);

		if(!driver.findElements(By.id(Module.TagIndexID)).isEmpty()){//순위가 노출되는지 확인
			System.out.println("HOT태그 순위노출 확인");
		}else{
			System.out.println("HOT태그 순위 비노출!!!!");
		}
		
		util.태그타이틀_진입확인(TagNameID, TagNameID);
		driver.navigate().back();
		util.노출확인(Module.ListviewClass);

		driver.findElementByXPath(Module.TagMakeButtonID).click();
		util.노출확인(Module.TagNameEditID);
		
		driver.findElementByXPath(Module.ClosTagEditiorBtnID).click();
		util.노출확인(Module.ListviewClass);

	}
	@AfterClass
	public void tearDown() throws Exception {
		
		driver.quit();
	}

}
