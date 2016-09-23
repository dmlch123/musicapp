package music_01_MusicHome;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_05_뮤직비디오 extends Utilites{

	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}

	@Test
	public void 뮤직비디오_테스트() throws Exception {
		System.out.println("뮤직비디오_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);

		// 뮤직비디오 탭 진입 > 타이틀이 뮤직비디오인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='뮤직비디오']").click();
		WebElement 뮤직비디오 = driver.findElementById(Module.TitleTextID);
		assertEquals("뮤직비디오", 뮤직비디오.getText());

		// 뮤직비디오 > 최신 탭 진입확인
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement 최신탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='최신']");
		util.선택확인(최신탭);
		util.노출확인(Module.ListviewClass);
		
		//첫번쨰 동영상 재생 후 확인
	    driver.findElementById(Module.VideoLineID).click();//동영상 재생
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        
		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if (팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();// 본인인증 팝업 노출시 취소tap
			} else {
				driver.findElementById(Module.PopupRightBtnID).click();// 그외의 팝업의 경우 확인tap
			}
		} else {
			if (!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { // 재샌되면 back으로 복귀

				driver.navigate().back();
			} else {
				System.out.println("동영상 재생되지 않음");
			}
		}
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// 뮤직비디오 > 최신 탭 진입확인
		Thread.sleep(2000);
		WebElement 인기탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='인기']");
		util.선택확인(인기탭);
		util.노출확인(Module.ListviewClass);

		// 첫번쨰 동영상 재생 후 확인
		driver.findElementById(Module.VideoLineID).click();// 동영상 재생
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if (팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();// 본인인증 팝업 노출시 취소tap
			} else {
				driver.findElementById(Module.PopupRightBtnID).click();// 그외의 팝업의 경우 확인tap
			}
		} else {
			if (!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { // 재샌되면 back으로 복귀

				driver.navigate().back();
			} else {
				System.out.println("동영상 재생되지 않음");
			}
		}
		
		
	}

	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
		util.appium_tearDown();
	}
}
