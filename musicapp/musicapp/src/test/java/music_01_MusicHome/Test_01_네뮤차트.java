package music_01_MusicHome;

import static org.junit.Assert.*;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nhncorp.api.MobileDevice;

import music_00_Reference.Module;
import music_00_Reference.Utilites;
import music_00_Reference.nMobile;

public class Test_01_네뮤차트 extends Utilites {
	
	Utilites util = new Utilites();

	
	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	
	
	@Test
	public void a_네이버뮤직차트_테스트() throws Exception{
		System.out.println("네이버뮤직차트_테스트 시작!!");
		//네뮤차트 진입 확인
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
		
		driver.findElementByXPath(Module.TextClass+"[@text='네이버뮤직 차트']").click();
		util.노출확인(Module.ListviewClass);
		
		WebElement 네뮤차트 = driver.findElementById(Module.TitleTextID);
		assertEquals("네이버뮤직 차트",네뮤차트.getText());

		util.툴박스_듣기();
		util.툴박스_재생목록();
		util.툴박스_내리스트();
		util.툴박스_저장();
		util.전체듣기();
		util.재생버튼();
	
		//top100 필터 종합,국내,해외 선택확인
		util.드롭박스형_필터_확인(TOP100);
		
		//top100 > 세대별 진입 > 각 세대별탭 진입확인
		WebElement 세대별 = driver.findElementByXPath(Module.TextClass+"[@text='세대별']");
        util.선택확인(세대별);
		
		String[] 세대별탭 = new String[] {"20대","30대","40대","50대 이상","10대" };
		   
		for (int i = 0; i < 세대별탭.length; i++) { 

            System.out.println("진입 세대 출력:" + 세대별탭[i]); 
            
            WebElement 세대명 = driver.findElementByXPath(Module.TextClass+"[@text='"+ 세대별탭[i] +"']");         
            util.선택확인(세대명);
        } 

		//top100으로 복귀
		WebElement top100 = driver.findElementByXPath(Module.TextClass+"[@text='TOP 100']");
        util.선택확인(top100);

		util.기능더보기_앨범상세정보();
		util.기능더보기_아티스트상세정보();
		util.기능더보기_저장();
		util.기능더보기_공유();
		util.기능더보기_태그에_이_곡을_추가();
        util.기능더보기_좋아요();

	}
	
	@Test
	public void b_뮤지션리그_100_테스트() throws Exception{

		// 네뮤차트 진입 확인
		driver.findElementById(Module.MenuopenID).click();
		util.노출확인(Module.SidemenuClass);
	
		driver.findElementByXPath(Module.TextClass+"[@text='네이버뮤직 차트']").click();
		util.노출확인(Module.ListviewClass);

		WebElement 네뮤차트 = driver.findElementById(Module.IDname+"/title_text");
		assertEquals("네이버뮤직 차트", 네뮤차트.getText());

		// top100 > 뮤지션리그탭 진입
		WebElement 뮤지션리그_100 = driver.findElementByXPath(Module.TextClass + "[@text='뮤지션 리그 100']");
		util.선택확인(뮤지션리그_100);
		
		util.노출확인(Module.ListviewClass);

		util.드롭박스형_필터_확인(뮤지션리그);

		util.음원만_재생();
		util.영상만_재생();
		util.뮤지션리그_재생버튼();
	
		util.기능더보기_아티스트상세정보();
		util.기능더보기_공유();
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
		driver.quit();
		util.appium_tearDown();
		
		// commit 테스트를 위한 주석
	}
	
}


