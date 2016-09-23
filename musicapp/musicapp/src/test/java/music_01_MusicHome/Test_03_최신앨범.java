package music_01_MusicHome;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_03_최신앨범 extends Utilites{

Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	@Test
	public void 최신앨범_테스트() throws Exception{
		System.out.println("최신앨범_테스트 시작!!");
		//태그end 최초진입시 가이드창 닫기
		driver.findElementById(Module.TagTitleID).click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.navigate().back();

		
		//사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		Thread.sleep(1000);
		
		//최신 앨범 탭 진입 > 타이틀이 최신앨범인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='최신 앨범']").click();
		WebElement 최신앨범 = driver.findElementById(Module.TitleTextID);
		assertEquals("최신 앨범",최신앨범.getText());
		
		//국내 첫번째 앨범 > 앨범명과 첫번째 앨범 진입하여 앨범명이 같은지 확인
		WebElement 최신앨범_국내 = driver.findElementByXPath(Module.RecentAlbumFirstContentID);
		String 국내_최신앨범명 = 최신앨범_국내.getAttribute("text");

		driver.findElementById(Module.FirstTrackID).click();//앨범 상세정보 진입
	
		util.앨범end_아티스트end진입();
		util.앨범end_좋아요tap();
		util.앨범end_공유tap();
		util.앨범end_연관태그tap();
		util.앨범end_필터();
	
		util.툴박스_듣기();
		util.툴박스_재생목록();
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.전체듣기();
		util.재생버튼();
		
		util.기능더보기_아티스트상세정보();
		util.기능더보기_저장();
		util.기능더보기_공유();
		util.기능더보기_태그에_이_곡을_추가();
		util.기능더보기_좋아요();
	
		WebElement 국내_앨범end = driver.findElementById(Module.AlbumTitleID);//앨범end 아티스트명 가져오기
		String 국내_앨범end_앨범명 = 국내_앨범end.getAttribute("text");
		assertEquals(국내_최신앨범명, 국내_앨범end_앨범명);
		
		driver.navigate().back();
		
		//해외 탭 진입
		WebElement 해외 = driver.findElementByXPath(Module.ButtonClass+"[@text='해외']");
		util.선택확인(해외);
		
		//해외 첫번째 앨범 > 앨범명과 첫번째 앨범 진입하여 앨범명이 같은지 확인
		WebElement 최신앨범_해외 = driver.findElementByXPath(Module.RecentAlbumFirstContentID);
		String 해외_최신앨범_앨범명 = 최신앨범_해외.getAttribute("text");
		
		driver.findElementById(Module.FirstTrackID).click();//앨범 상세정보 진입
				
		WebElement 해외_앨범end = driver.findElementById(Module.AlbumTitleID);//앨범end 아티스트명 가져오기
		String 해외_앨범end_앨범명 = 해외_앨범end.getAttribute("text");
		assertEquals(해외_최신앨범_앨범명, 해외_앨범end_앨범명);

		driver.findElementById(Module.AlbumNameID).click();//아티스트end 진입
		
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) { //아티스트가 여러명인 경우 첫번째 아티스트 진입
			WebElement 앨범_가수 = driver.findElementById(Module.ArtistID);//아티스트 목록에서 첫번째 아티스트명 가져오기
			String 앨범_가수명 = 앨범_가수.getAttribute("text");
			
			driver.findElementById(Module.ArtistID).click();
			
			WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);//아티스트end 아티스트명 가져오기
			String 아티스트_가수명 = 아티스트_가수.getAttribute("text");
			assertEquals(앨범_가수명, 아티스트_가수명);
			
		}
		else {
		   System.out.println("아티스트가 한명인 앨범");
		   
		   WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);//아티스트end 아티스트명 가져오기
		   String 아티스트_가수명 = 아티스트_가수.getAttribute("text");
			driver.navigate().back();
			
			WebElement 앨범_가수 = driver.findElementById(Module.ArtistNameID);//타이틀중 아티스트명 가져오기
			String 앨범_가수명 = 앨범_가수.getAttribute("text");				
			assertEquals(앨범_가수명, 아티스트_가수명);
			
			driver.findElementById(Module.ArtistNameID).click();
		}		
		
		util.아티스트end_팬tap();
		util.아티스트end_공유tap();
		util.아티스트end_연관태그tap();
//		util.아티스트end_앨범탭();
//		util.아티스트end_동영상탭();
//		util.아티스트end_곡탭();
		
		util.툴박스_듣기();
		util.툴박스_재생목록();
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.전체듣기();
		util.재생버튼();
		
//		util.기능더보기_앨범상세정보();
		util.기능더보기_저장();
		util.아티스트end_공유tap();
		util.기능더보기_태그에_이_곡을_추가();
		
		driver.navigate().back();
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
		driver.quit();
		util.appium_tearDown();
	}
}
