package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_03_최근들은음악 extends Utilites {
		
	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
		
	@Test
	public void 최근들은음악_테스트() throws Exception {
		System.out.println("최근들은음악_테스트 시작!!");
		// 네뮤차트 진입 후 전체듣기
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);

		driver.findElementByXPath(Module.TextClass+"[@text='네이버뮤직 차트']").click();
		WebElement 네뮤차트 = driver.findElementById(Module.TitleTextID);
		assertEquals("네이버뮤직 차트", 네뮤차트.getText());
		util.노출확인(Module.ListviewClass);
		
		//전체듣기시 가장 처음 재생되는곡명 가져오기
		WebElement 네뮤차트_1위곡 = driver.findElementById(Module.TitleID);
		String 네뮤차트_1위곡명 = 네뮤차트_1위곡.getAttribute("text");
		
		util.전체듣기();
		        
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		// driver.swipe(430, 1050, 430, 550, 500);
		Thread.sleep(2500);

		// 최근 들은 음악 탭 진입 > 타이틀이 최근 들은 음악인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='최근 들은 음악']").click();
		WebElement 최근_들은_음악 = driver.findElementById(Module.TitleTextID);
		assertEquals("최근 들은 음악", 최근_들은_음악.getText());

		//네뮤차트에서 재생한곡과 최근들은음악에서의 곡이 동일한지 확인
		util.노출확인(Module.ListviewClass);
		WebElement 가장최근_들은_음악 = driver.findElementById(Module.TitleID);
		String 가장최근_들은_음악명 = 가장최근_들은_음악.getAttribute("text");
		
		assertEquals(네뮤차트_1위곡명, 가장최근_들은_음악명);

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
