package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_07_구매_대여내역 extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void 구매_대여내역_테스트() throws Exception {
		System.out.println("구매_대여내역_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);

		// 구매_대여내역탭 진입 > 타이틀이 구매_대여내역인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='구매/대여 내역']").click();
		WebElement 구매_대여내역 = driver.findElementById(Module.TitleTextID);
		assertEquals("구매/대여 내역", 구매_대여내역.getText());
	
		util.노출확인(Module.ListviewClass);
		
		WebElement MP3탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='MP3']");
		util.선택확인(MP3탭);
		
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.재생버튼();
		
		util.기능더보기_앨범상세정보();
		util.기능더보기_아티스트상세정보();
		util.기능더보기_좋아요();
		util.기능더보기_저장();
		util.기능더보기_공유();
		
		WebElement 대여 = driver.findElementByXPath(Module.ButtonClass+"[@text='대여']");
		util.선택확인(대여);
		
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.재생버튼();
		
		util.기능더보기_앨범상세정보();
		util.기능더보기_아티스트상세정보();
		util.기능더보기_좋아요();
		util.기능더보기_저장();
		util.기능더보기_공유();
	}
	
	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}
}
