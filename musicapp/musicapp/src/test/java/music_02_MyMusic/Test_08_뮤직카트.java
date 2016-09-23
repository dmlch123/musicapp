package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_08_뮤직카트  extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void a_뮤직카트_대여_테스트() throws Exception {
		System.out.println("뮤직카트_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.8);
		Thread.sleep(2500);

		// 뮤직카트 탭 진입 > 타이틀이 뮤직카트인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='뮤직카트']").click();
		util.노출확인(Module.ButtonClass+"[@text='대여 카트']");
		
		//노출되는 이용권 진입시 내이용권페이지 노출되는지
		util.뮤직카트_이용권_노출확인_및_진입();
	
		//대여카트 이용 팝업 노출 확인
		util.뮤직카트_이용안내_팝업확인();
		
		//대여.구매내역 버튼 진입확인
		util.뮤직카트_구매_대여내역_진입확인();
	}
	@Test
	public void b_뮤직카트_mp3_테스트() throws Exception {
/*		
		// 사이드메뉴 펼침
		driver.findElementById("com.nhn.android.music:id/quickmenu_indicator_btn").click();
		module.노출확인("//android.widget.ExpandableListView");
		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2500);

		// 뮤직카트 탭 진입 > 타이틀이 뮤직카트인지 확인
		driver.findElementByXPath("//android.widget.TextView[@text='뮤직카트']").click();
		module.노출확인("//android.widget.Button[@text='대여 카트']");
*/		
		// 뮤직카트 진입확인
		driver.findElementByXPath(Module.ButtonClass+"[@text='MP3 카트']").click();

		util.노출확인(Module.ButtonClass+"[@text='이용권으로 구매']");
		
		WebElement 이용권으로구매 = driver.findElementByXPath(Module.ButtonClass+"[@text='이용권으로 구매']");
		util.선택확인(이용권으로구매);
			
		// 노출되는 이용권 진입시 내이용권페이지 노출되는지
		util.뮤직카트_이용권_노출확인_및_진입();
		Thread.sleep(1000);
		
		// MP3카트 이용 팝업 노출 확인
		util.뮤직카트_이용안내_팝업확인();

		// MP3.구매내역 버튼 진입확인
		util.뮤직카트_구매_대여내역_진입확인();
		
		WebElement 개별곡구매 = driver.findElementByXPath(Module.ButtonClass+"[@text='개별곡 구매']");
		util.선택확인(개별곡구매);
		
		// MP3카트 이용 팝업 노출 확인
		util.MP3카트_개별곡_이용안내_팝업확인();

		// MP3.구매내역 버튼 진입확인
		util.뮤직카트_구매_대여내역_진입확인();
				
	}
	
	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}
}
