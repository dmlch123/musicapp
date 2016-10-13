package music_01_MusicHome;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_02_장르별음악 extends Utilites {

	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
		
	}

	@Test
	public void 장르별_음악_국내장르() throws Exception{

		System.out.println("장르별_음악_테스트 시작!!");
		//사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		
		//장르별 음악 탭 진입 > 타이틀이 장르별 음악인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='장르별 음악']").click();
		WebElement 장르별음악 = driver.findElementById(Module.TitleTextID);
		assertEquals("장르별 음악",장르별음악.getText());
		Thread.sleep(2000);
		
		String[] 장르별탭 = new String[] {"발라드","댄스","힙합","록/포크","트로트" };
	
		for (int i = 0; i < 장르별탭.length; i++) { 

        System.out.println("진입 국내장르 출력:" + 장르별탭[i]); 
            
        WebElement 장르명 = driver.findElementByXPath(Module.TextClass+"[@text='"+ 장르별탭[i] +"']");
        장르명.click();
    	WebElement 장르_타이틀 = driver.findElementById(Module.TitleTextID);
        assertEquals(장르별탭[i],장르_타이틀.getText());
    	util.노출확인(Module.ListviewClass);
    	
    	driver.navigate().back();
		}
		
		driver.findElementByXPath(Module.TextClass+"[@text='발라드']").click();
		WebElement 발라드 = driver.findElementById(Module.TitleTextID);
		assertEquals("발라드",발라드.getText());
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
		
		//장르별 음악 > 최신앨범 탭 첫번째앨번end 진입확인
		Thread.sleep(1000);
		WebElement 최신앨범탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='최신 앨범']");
        util.선택확인(최신앨범탭);
        
        Thread.sleep(1000);
				
		WebElement 최신앨범_힙합 = driver.findElementByXPath(Module.RecentAlbumFirstContentID);
		String 힙합_최신앨범명 = 최신앨범_힙합.getAttribute("text");//최신 앨범리스트에서 앨범명가져오기
		
		driver.findElementById(Module.FirstTrackID).click();//앨범end 진입
		
		WebElement 힙합_앨범end = driver.findElementById(Module.AlbumTitleID);//앨범end 아티스트명 가져오기
		String 힙합_앨범end_앨범명 = 힙합_앨범end.getAttribute("text");
		assertEquals(힙합_최신앨범명, 힙합_앨범end_앨범명);
		
		driver.navigate().back();
		
		// 장르별 음악 > 아티스트 탭 첫번째 아티스트end 진입확인
		WebElement 아티스트탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='인기 아티스트']");
        util.선택확인(아티스트탭);

        Thread.sleep(1000);
		
		WebElement 아티스트_힙합 = driver.findElementById(Module.ArtistID);
		String 아티스트_힙합_아티스트명 = 아티스트_힙합.getAttribute("text");// 인기 아티스트에서 아티스트명가져오기

		driver.findElementById(Module.FirstTrackID).click();// 아티스트end 진입

		WebElement 힙합_아티스트end = driver.findElementById(Module.MusicianNameID);// 아티스트end 아티스트명 가져오기
		String 힙합_아티스트end_아티스트명 = 힙합_아티스트end.getAttribute("text");
		assertEquals(아티스트_힙합_아티스트명, 힙합_아티스트end_아티스트명);

		driver.navigate().back();
		
	}

	@Test
	public void 장르별_음악_해외장르() throws Exception{
		
		//사이드메뉴 펼침
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		
		//장르별 음악 탭 진입 > 타이틀이 장르별 음악인지 확인
		driver.findElementByXPath(Module.TextClass+"[@text='장르별 음악']").click();
		WebElement 장르별음악 = driver.findElementById("com.nhn.android.music:id/title_text");
		assertEquals("장르별 음악",장르별음악.getText());
		util.노출확인("//android.widget.ListView");
	
		util.위아래_스크롤(0.36);
//		driver.swipe(540, 1140, 540, 400, 740);
		Thread.sleep(2500);
		
		String[] 장르별탭 = new String[] {"POP","ROCK/FOLK","HIP-HOP","R&B","ELECTRONICA","J-POP","WORLD MUSIC" };
		
		for (int i = 0; i < 장르별탭.length; i++) { 

        System.out.println("진입 해외장르 출력:" + 장르별탭[i]); 
            
        WebElement 장르명 = driver.findElementByXPath(Module.TextClass+"[@text='"+ 장르별탭[i] +"']");
        장르명.click();
    	WebElement 장르_타이틀 = driver.findElementById(Module.TitleTextID);
        assertEquals(장르별탭[i],장르_타이틀.getText());
        
		util.노출확인(Module.ListviewClass);
		
    	driver.navigate().back();
		}
		
		driver.findElementByXPath(Module.TextClass+"[@text='HIP-HOP']").click();
		WebElement HIP_HOP = driver.findElementById(Module.TitleTextID);
		assertEquals("HIP-HOP",HIP_HOP.getText());
		util.노출확인(Module.ListviewClass);
		
		// 장르별 음악 > top100 탭 진입확인
		WebElement TOP100탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='TOP100']");
        util.선택확인(TOP100탭);

        Thread.sleep(1000);
		
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
		
		//장르별 음악 > 최신앨범 탭 첫번째앨번end 진입확인
		Thread.sleep(1000);
		WebElement 최신앨범탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='최신 앨범']");
        util.선택확인(최신앨범탭);
				
        Thread.sleep(1000);  
		WebElement 최신앨범_힙합 = driver.findElementByXPath(Module.RecentAlbumFirstContentID);
		String 힙합_최신앨범명 = 최신앨범_힙합.getAttribute("text");//최신 앨범리스트에서 앨범명가져오기
		
		driver.findElementById(Module.FirstTrackID).click();//앨범end 진입
		
		WebElement 힙합_앨범end = driver.findElementById(Module.AlbumTitleID);//앨범end 아티스트명 가져오기
		String 힙합_앨범end_앨범명 = 힙합_앨범end.getAttribute("text");
		assertEquals(힙합_최신앨범명, 힙합_앨범end_앨범명);
		
		driver.navigate().back();
		
		// 장르별 음악 > 아티스트 탭 첫번째 아티스트end 진입확인
		WebElement 아티스트탭 = driver.findElementByXPath(Module.ButtonClass+"[@text='인기 아티스트']");
        util.선택확인(아티스트탭);

        Thread.sleep(1000);
		WebElement 아티스트_힙합 = driver.findElementById(Module.ArtistID);
		String 아티스트_힙합_아티스트명 = 아티스트_힙합.getAttribute("text");// 인기 아티스트에서 아티스트명가져오기

		driver.findElementById(Module.FirstTrackID).click();// 아티스트end 진입

		WebElement 힙합_아티스트end = driver.findElementById(Module.MusicianNameID);// 아티스트end 아티스트명 가져오기
		String 힙합_아티스트end_아티스트명 = 힙합_아티스트end.getAttribute("text");
		assertEquals(아티스트_힙합_아티스트명, 힙합_아티스트end_아티스트명);

		driver.navigate().back();
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		util.appium_tearDown();

	}
	
}
