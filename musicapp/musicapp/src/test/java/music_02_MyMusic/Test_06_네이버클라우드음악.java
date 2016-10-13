package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_06_네이버클라우드음악  extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void 네이버클라우드_음악_테스트() throws Exception {
		System.out.println("네이버클라우드_음악_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);

		// 네이버클라우드 탭 진입 > 타이틀이 네이버클라우드 인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='네이버 클라우드 음악']").click();

		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			//클라우드 팝업 확인 tap
			driver.findElementById(Module.PopupRightBtnID).click();
		} else {
			System.out.println("팝업 비노출");
		}
		
		WebElement 네이버_클라우드_음악 = driver.findElementById(Module.TitleTextID);
		assertEquals("네이버 클라우드 음악", 네이버_클라우드_음악.getText());
		
		util.노출확인(Module.ListviewClass);
		
		// 네이버클라우드 필터 최신수정순,가나다순 선택확인
		util.팝업형_필터_확인(네이버클라우드);

		util.툴박스_듣기();
		util.툴박스_재생목록();
		
		util.전체듣기();
		util.재생버튼();
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
		util.appium_tearDown();

	}

}
