package music_02_MyMusic;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_01_MY좋아요 extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void MY좋아요_곡탭_테스트() throws Exception {
		System.out.println("MY좋아요_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2000);
		
		// MY좋아요 탭 진입 > 타이틀이 MY좋아요인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='MY좋아요']").click();
		WebElement MY좋아요 = driver.findElementById(Module.TitleTextID);
		assertEquals("MY좋아요", MY좋아요.getText());
		Thread.sleep(2000);
		
		//곡탭 > 진입확인
		WebElement 곡탭 = driver.findElement(By.id(Module.BtnTabLeftID));
		util.선택확인(곡탭);
		
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
		
		//편집모드 > 한곡삭제 확인
		driver.findElement(By.id(Module.EditBtnID)).click();
		
		WebElement 삭제전_첫번쨰곡 = driver.findElement(By.id(Module.TitleID));
		String 삭제전_첫번쨰곡명 = 삭제전_첫번쨰곡.getAttribute("text");
		
		삭제전_첫번쨰곡.click();
		
		WebElement 삭제버튼 = driver.findElement(By.id(Module.EditLeftBtnID));
		util.노출대기_후_클릭(삭제버튼);
		
		WebElement 삭제후_첫번쨰곡 = driver.findElement(By.id(Module.TitleID));
		String 삭제후_첫번쨰곡명 = 삭제후_첫번쨰곡.getAttribute("text");
		
		assertNotEquals(삭제전_첫번쨰곡명, 삭제후_첫번쨰곡명);
	}
	
	@Test
	public void MY좋아요_앨범_아티스트_동영상_확인() throws Exception{
/*
		// 사이드메뉴 펼침
		driver.findElementById("com.nhn.android.music:id/quickmenu_indicator_btn").click();
		module.노출확인("android.widget.ExpandableListView");
		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2500);
		
		// MY좋아요 탭 진입 > 타이틀이 MY좋아요인지 확인
		driver.findElementByXPath("//android.widget.TextView[@text='MY좋아요']").click();
		WebElement MY좋아요 = driver.findElementById("com.nhn.android.music:id/title_text");
		assertEquals("MY좋아요", MY좋아요.getText());
		Thread.sleep(2000);
*/
		// MY좋아요 > 앨범탭 진입 > 앨범목록 중 첫번째 앨범end진입해서 타이틀확인
		WebElement 앨범 = driver.findElementByXPath(Module.ButtonClass+"[@text='앨범']");
		util.선택확인(앨범);
		util.타이틀명_진입테스트(Module.TitleID, Module.AlbumTitleID);

		// MY좋아요 > 아티스트탭 진입 > 아티스트목록 중 첫번째 아티스트end진입해서 타이틀확인
		WebElement 아티스트 = driver.findElementByXPath(Module.ButtonClass+"[@text='아티스트']");
		util.선택확인(아티스트);
		util.타이틀명_진입테스트(Module.ArtistID, Module.MusicianNameID);

		// MY좋아요 > 동영상탭 진입확인
		WebElement 동영상 = driver.findElementByXPath(Module.ButtonClass+"[@text='동영상']");
		util.선택확인(동영상);
		
		// 첫번쨰 동영상 재생 후 확인
		driver.findElementById(Module.VideoLineID).click();// 동영상_재생
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if (팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();// 본인인증_팝업_노출시_취소tap
			} else {
				driver.findElementById(Module.PopupRightBtnID).click();// 그외의_팝업의_경우_확인tap
			}
		} else {
			if (!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { // 재샌되면_back으로_복귀

				driver.navigate().back();
			} else {
				System.out.println("동영상 재생되지 않음");
			}
		}
	}
	
	@Test
	public void MY좋아요_뮤지션리그탭_테스트() throws Exception{
/*		
		// 사이드메뉴 펼침
		driver.findElementById("com.nhn.android.music:id/quickmenu_indicator_btn").click();
		module.노출확인("android.widget.ExpandableListView");
		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2500);
		
		// MY좋아요 탭 진입 > 타이틀이 MY좋아요인지 확인
		driver.findElementByXPath("//android.widget.TextView[@text='MY좋아요']").click();
		WebElement MY좋아요 = driver.findElementById("com.nhn.android.music:id/title_text");
		assertEquals("MY좋아요", MY좋아요.getText());
		Thread.sleep(2000);
*/		
		// MY좋아요 > 앨범탭 진입 > 앨범목록 중 첫번째 앨범end진입해서 타이틀확인
		WebElement 뮤지션리그 = driver.findElementByXPath(Module.ButtonClass+"[@text='뮤지션리그']");
		util.선택확인(뮤지션리그);
				
		String[] 필터값 = new String[] { "동영상", "곡" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			driver.findElementById(Module.SortBtnID).click();
			util.노출확인(Module.ListviewClass);
			
			WebElement 변경된_필터 = driver.findElementById(Module.SortTextID);		
			assertEquals(필터값[i],변경된_필터.getAttribute("text"));
		}
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		util.툴박스_듣기();
		util.툴박스_재생목록();
		
		util.전체듣기();
		util.재생버튼();
		
		util.기능더보기_아티스트상세정보();
		util.기능더보기_공유();
		
		//my좋아요>뮤지션리그>동영상 페이지테스트
		driver.findElementById(Module.SortBtnID).click();
		util.노출확인(Module.ListviewClass);
		
		WebElement 동영상영역 = driver.findElementById(Module.SortTextID);		
		assertEquals("동영상",동영상영역.getAttribute("text"));
				
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
		
		//아티스트end 진입 > 아티스트명 확인
		WebElement 아티스트 = driver.findElementById(Module.NameViewID);
		String 아티스트명 = 아티스트.getAttribute("text");

		//뮤지션리그end 진입
		driver.findElementById(Module.ArtistImgID).click();
		util.노출확인(Module.ListviewClass);
		
		WebElement 뮤지션end_아티스트 = driver.findElementById(Module.MusicianNameID);
		String 뮤지션end_아티스트명 = 뮤지션end_아티스트.getAttribute("text");
		
		assertEquals(아티스트명, 뮤지션end_아티스트명);
		
		driver.navigate().back();

		//동영상tap하여 재생되는지 확인
		driver.findElementById(Module.MainImgID).click();
		Thread.sleep(1000);
		
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
