package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_05_휴대폰음악 extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void 휴대폰_음악_곡_테스트() throws Exception {
		System.out.println("휴대폰_음악_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);
		
		// 휴대폰음악 탭 진입 > 타이틀이 휴대폰음악인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='휴대폰 음악']").click();
		WebElement 휴대폰음악 = driver.findElementById(Module.TitleTextID);
		assertEquals("휴대폰 음악", 휴대폰음악.getText());
		Thread.sleep(2000);
		
		// 휴대폰음악 필터 최신순, 가나다순 선택확인
		WebElement 현재필터 = driver.findElementById(Module.SortTextID);
		assertEquals("최신순",현재필터.getAttribute("text"));
		
		String[] 필터값 = new String[] { "가나다순", "최신순" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			driver.findElementById(Module.SortBtnID).click();
			util.노출확인(Module.ListviewClass);
			
			WebElement 변경된_필터 = driver.findElementById(Module.SortTextID);		
			assertEquals(필터값[i],변경된_필터.getAttribute("text"));
		}
		
		util.툴박스_듣기();
		util.툴박스_재생목록();
		
		util.전체듣기();
		util.재생버튼();	
	}
	
	@Test
	public void 휴대폰_음악_앨범_아티스트_테스트() throws Exception{
/*	
		// 사이드메뉴 펼침
		driver.findElementById("com.nhn.android.music:id/quickmenu_indicator_btn").click();
		module.노출확인("android.widget.ExpandableListView");
		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2000);
		
		// 휴대폰음악 탭 진입 > 타이틀이 휴대폰음악인지 확인
		driver.findElementByXPath("//android.widget.TextView[@text='휴대폰 음악']").click();
		WebElement 휴대폰음악 = driver.findElementById("com.nhn.android.music:id/title_text");
		assertEquals("휴대폰 음악", 휴대폰음악.getText());
		Thread.sleep(2000);
*/		
		// 휴대폰음악 > 앨범탭 진입확인
		WebElement 앨범 = driver.findElementByXPath(Module.ButtonClass+"[@text='앨범']");
		util.선택확인(앨범);
		
		// 휴대폰음악_앨범탭 필터 최신순, 가나다순 선택확인
		WebElement 앨범탭_현재필터 = driver.findElementById(Module.SortTextID);
		assertEquals("최신순", 앨범탭_현재필터.getAttribute("text"));

		String[] 필터값 = new String[] { "가나다순", "최신순" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			driver.findElementById(Module.SortBtnID).click();
			util.노출확인(Module.ListviewClass);

			WebElement 변경된_필터 = driver.findElementById(Module.SortTextID);
			assertEquals(필터값[i], 변경된_필터.getAttribute("text"));
		}
		
		//앨범탭 > 앨범목록 중 첫번째 앨범end진입해서 타이틀확인
		util.타이틀명_진입테스트(Module.TitleID, Module.TitleTextID);
	
		// 휴대폰음악 > 아티스트탭 진입확인
		WebElement 아티스트 = driver.findElementByXPath(Module.ButtonClass+"[@text='아티스트']");
		util.선택확인(아티스트);
		
		// 휴대폰음악_아티스트탭 필터 최신순, 가나다순 선택확인
		WebElement 아티스트_현재필터 = driver.findElementById(Module.SortTextID);
		assertEquals("최신순", 아티스트_현재필터.getAttribute("text"));

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			driver.findElementById(Module.SortBtnID).click();
			util.노출확인(Module.ListviewClass);

			WebElement 변경된_필터 = driver.findElementById(Module.SortTextID);
			assertEquals(필터값[i], 변경된_필터.getAttribute("text"));
		}

		// 아티스트탭 > 아티스트목록 중 첫번째 아티스트end진입해서 타이틀확인
		util.타이틀명_진입테스트(Module.ArtistID, Module.TitleTextID);
	}
	
	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}
}
