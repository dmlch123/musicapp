package music_02_MyMusic;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_02_내리스트 extends Utilites{

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}
	
	@Test
	public void a_내리스트_end_테스트() throws Exception {
		System.out.println("내리스트_end_테스트 시작!!");
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		util.위아래_스크롤(0.73);
		Thread.sleep(2500);

		// 내리스트 탭 진입 > 타이틀이 내리스트인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='내 리스트']").click();
		WebElement 내리스트탭 = driver.findElementById(Module.TitleTextID);
		assertEquals("내 리스트", 내리스트탭.getText());
		
		util.노출확인(Module.ListviewClass);
		
		//내리스트 첫번쨰 리스트 진입 타이틀 확인
		WebElement 내리스트 = driver.findElementById(Module.TextViewTitleID);
		String 내리스트명 = 내리스트.getAttribute("text");
		
		내리스트.click();
		util.노출확인(Module.ListviewClass);
		
		WebElement 내리스트end = driver.findElementById(Module.TitleTextID);
		String 내리스트end명 = 내리스트end.getAttribute("text");
		assertEquals(내리스트명, 내리스트end명);
		
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
		
		//편집모드 진입
		driver.findElementById(Module.EditBtnID).click();
		
		WebElement 복사 = driver.findElementById(Module.EditRightBtnID);
		WebElement 이동 = driver.findElementById(Module.EditMidBtnID);
		WebElement 삭제 = driver.findElementById(Module.EditLeftBtnID);
				
		//편집>복사
		try{
		int i;
		for(i=1; i <=4; i++){
			
			driver.findElementByXPath(Module.ListContentClass+"[" + i + "]").click();
		}
		util.노출대기_후_클릭(복사);
		Thread.sleep(1000);
		
		//새리스트에 복사
		driver.findElementById(Module.PopupRightBtnID).click();
		
		String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		driver.findElementById(Module.TextID).sendKeys(inTime);//현재시각으로 리스트명생성
		driver.findElementById(Module.PopupRightBtnID).click();
		Thread.sleep(500);

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("내리스트 복사 실패");
			Thread.sleep(500);

		}

		// 편집>이동
		try {
			int i;
			for (i = 5; i <= 8; i++) {
				driver.findElementByXPath(Module.ListContentClass+"[" + i + "]").click();	
			}
			util.노출대기_후_클릭(이동);
			Thread.sleep(1000);

			// 첫번쨰 리스트에 이동 
			driver.findElementById(Module.TextViewTitleID).click();	
			Thread.sleep(500);

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("내리스트 이동 실패");
			Thread.sleep(500);

		}

		//내리스트 이름변경
		String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		driver.findElementById(Module.SortBtnID).click();	
		driver.findElementById(Module.TextID).sendKeys(inTime);
		driver.findElementById(Module.PopupRightBtnID).click();

		//편집 > 전체삭제
		util.노출확인(Module.ListviewClass);
		driver.findElementByXPath(Module.TextClass+"[@text='전체선택']").click();//곡 전체선택버튼
		util.노출대기_후_클릭(삭제);
		
		//전체 삭제팝업
		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			driver.findElementById(Module.PopupRightBtnID).click();
		} else {
			driver.findElementById(Module.IDname+"/dialog_right_btn_text").click();// 그외의 팝업의 경우 확인tap
		}
			
		WebElement 빈_내리스트 = driver.findElementById(Module.IDname+"/empty_view_title");
		String 빈_내리스트_문구 = 빈_내리스트.getAttribute("text");
		assertEquals("곡 목록이 없습니다.", 빈_내리스트_문구);

		//변경된 이름 확인
		WebElement 이름변경 = driver.findElementById(Module.TitleTextID);
		String 이름변경_후_내리스트명 = 이름변경.getAttribute("text");
		assertEquals(inTime, 이름변경_후_내리스트명);
		
		driver.navigate().back();
		
		//편집한 내리스트 곡수 확인
		WebElement 내리스트_편집_곡 = driver.findElementById(Module.IDname+"/textview_mylist_count");
		String 내리스트_편집_곡수 = 내리스트_편집_곡.getAttribute("text");
		assertEquals("8곡", 내리스트_편집_곡수);
	}
	
	@Test
	public void b_내리스트_목록_테스트() throws Exception{
/*
		// 사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		driver.swipe(647, 1677, 647, 150, 1527);
		Thread.sleep(2500);

		// 내리스트 탭 진입 > 타이틀이 내리스트인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='내 리스트']").click();
		WebElement 내리스트탭 = driver.findElementById(Module.TitleTextID);
		assertEquals("내 리스트", 내리스트탭.getText());

		util.노출확인(Module.ListviewClass);
*/
		//내리스트 만들기
		driver.findElementById(Module.IDname+"/btn_make_list").click();
		
		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
			driver.findElementById(Module.IDname+"/text").sendKeys(inTime);
			driver.findElementById(Module.PopupRightBtnID).click();
		} else {
			System.out.println("내리스트 만들기 팝업 비노출");
		}
		
		//내리스트 목록 편집모드 진입
		driver.findElementById(Module.EditBtnID).click();
		
		//편집모드에서 내리스트 생성하기
		driver.findElementById(Module.SortBtnID).click();
		
		if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
			driver.findElementById(Module.TextID).sendKeys(inTime);
			driver.findElementById(Module.PopupRightBtnID).click();
		} else {
			System.out.println("내리스트 만들기 팝업 비노출");
		}
		
		WebElement 삭제 = driver.findElementById(Module.EditLeftBtnID);
		
		try {
			int i, j;
			for (j = 1; j <= 2; j++) {
				for (i = 1; i <= 6; i++) {
					driver.findElementByXPath(Module.ListContentClass+"[" + i + "]").click();
				}
				util.노출대기_후_클릭(삭제);

				if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
					driver.findElementById(Module.PopupRightBtnID).click();
				} else {
					System.out.println("삭제 팝업 비노출");
				}
			}
			driver.findElementById(Module.TextViewTitleID).click();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("내리스트 목록 삭제 실패");
		}

	}

	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}

}
