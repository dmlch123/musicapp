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

public class Test_06_뮤지션리그 extends Utilites{
	
	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	@Test
	public void a_뮤지션리그_곡() throws Exception{
		System.out.println("뮤지션리그_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		Thread.sleep(1000);

		// 뮤지션리그 탭 진입 > 타이틀이 뮤지션리그인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='뮤지션 리그']").click();
		WebElement 뮤지션리그 = driver.findElementById(Module.TitleTextID);
		assertEquals("뮤지션 리그", 뮤지션리그.getText());
		
		util.노출확인(Module.ListviewClass);
		
		// 뮤지션리그 필터 좋아요순,재생순,최신순 선택확인
		String[] 필터값 = new String[] { "좋아요순", "재생순", "최신순" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			util.노출대기_후_클릭(필터);
			
			if(!driver.findElements(By.id(Module.PopupID)).isEmpty())
			{
				driver.findElementByXPath(Module.TextClass+"[@text='" + 필터값[i] + "']").click();
				
				WebElement 필터명 = driver.findElementById(Module.SortTextID);
				assertEquals(필터값[i], 필터명.getAttribute("text"));

				Thread.sleep(500);
			}
			else
			{
			   System.out.println("정렬순 팝업 비노출");
			}
		}

			util.툴박스_듣기();
			util.툴박스_재생목록();
			
			util.전체듣기();
			
			util.재생버튼();
			util.기능더보기_아티스트상세정보();
			util.기능더보기_공유();
	}
	
	@Test
	public void b_뮤지션리그_동영상() throws Exception{
/*		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		module.노출확인(Module.SidemenuClass);
		driver.swipe(647, 1677, 647, 1160, 500);
		// driver.swipe(430, 1050, 430, 550, 500);
		Thread.sleep(2500);

		// 뮤지션리그 탭 진입 > 타이틀이 뮤지션리그인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='뮤지션 리그']").click();
		WebElement 뮤지션리그 = driver.findElementById(Module.TitleTextID);
		assertEquals("뮤지션 리그", 뮤지션리그.getText());
		
		module.노출확인(Module.ListviewClass);
*/
		// 뮤지션 리그 > 동영상 탭 진입확인
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement 동영상탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='동영상']");
		util.선택확인(동영상탭);
		
		// 뮤지션리그 필터 좋아요순,재생순,최신순 선택확인
		String[] 필터값 = new String[] { "재생순", "최신순", "좋아요순" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			util.노출대기_후_클릭(필터);

			if(!driver.findElements(By.id(Module.PopupID)).isEmpty())
			{
				driver.findElementByXPath(Module.TextClass+"[@text='" + 필터값[i] + "']").click();
				
				WebElement 필터명 = driver.findElementById(Module.SortTextID);
				assertEquals(필터값[i], 필터명.getAttribute("text"));

				Thread.sleep(500);
			}
			else
			{
			   System.out.println("정렬순 팝업 비노출");    
			}
		}
		
		util.앨범end_좋아요tap();
		
		//공유버튼tap
		driver.findElementById(Module.ShareBtnID).click();//공유버튼 tap
		
		// 공유할 sns리스트 팝업 노출
		if(!driver.findElements(By.id(Module.SharePopupID)).isEmpty())//공유 팝업 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();//공유팝업 닫기 tap
		}
		else
		{
		   System.out.println("공유팝업 비노출");   
		}
		
		//아티스트명 확인
		util.아티스트명_진입테스트(Module.NameViewID, Module.MusicianNameID, Module.ArtistImgID);

		//동영상tap하여 재생되는지 확인
		driver.findElementById(Module.MainImgID).click();
		Thread.sleep(2000);
		
		if(!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { //재샌되면 back으로 복귀
			
			driver.navigate().back();
		}
		else {
		   System.out.println("동영상 재생되지 않음");    
		}
	}
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		util.appium_tearDown();

	}
}
