package music_01_MusicHome;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_04_무료음악 extends Utilites{
	
	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	@Test
	public void 무료음악_테스트() throws Exception{
		System.out.println("무료음악_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);

		// 무료음악 탭 진입 > 타이틀이 무료음악인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='무료음악']").click();
		WebElement 무료음악 = driver.findElementById(Module.TitleTextID);
		assertEquals("무료음악", 무료음악.getText());

		util.노출확인(Module.ListviewClass);
		
		util.툴박스_듣기();
		util.툴박스_재생목록();
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.전체듣기();
		util.재생버튼();
		
		util.기능더보기_앨범상세정보();
		util.기능더보기_아티스트상세정보();
		util.기능더보기_저장();
		util.기능더보기_공유();
		util.기능더보기_태그에_이_곡을_추가();
			
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
		driver.quit();
		util.appium_tearDown();
	}
}
