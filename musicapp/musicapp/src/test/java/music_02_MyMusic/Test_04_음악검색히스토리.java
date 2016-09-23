package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_04_음악검색히스토리 extends Utilites {
	
	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}

	@Test
	public void 음악검색_히스토리_테스트() throws Exception {
		System.out.println("음악검색_히스토리_테스트 시작!!");
		// 네뮤차트 진입 후 전체듣기
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
//		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2500);
		
		driver.findElementByXPath(Module.TextClass + "[@text='음악검색 히스토리']").click();
		WebElement 음악검색히스토리 = driver.findElementById(Module.TitleTextID);
		assertEquals("음악검색 히스토리", 음악검색히스토리.getText());
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
        util.기능더보기_좋아요();
	}

	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}
}
