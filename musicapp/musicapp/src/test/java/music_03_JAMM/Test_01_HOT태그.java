package music_03_JAMM;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_01_HOT태그 extends Utilites {
	
	Utilites util = new Utilites();
	
	@BeforeClass
	public void setUp() throws Exception {
		
		util.단말정보();
	}
	
	@Test
	public void HOT태그_테스트() throws Exception{
		System.out.println("HOT태그_테스트 시작!!");
		//태그end 최초진입시 가이드창 닫기
		driver.findElementById(Module.TagTitleID).click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.navigate().back();
		
		//HOT태그 진입 확인
		util.태그타이틀_진입확인(Module.TagTitleID, Module.TagNameID);		
		
		String 진입전_타이틀 = driver.findElementById(Module.ExtraID).getAttribute("text");
		System.out.println(진입전_타이틀);
		
		//공식계정 여부 판단하여 필터에 따라 테스트진행
		boolean 공식계정여부 = false;

		for (int i = 0; i < Module.공식계정.length; i++) {
			if (진입전_타이틀.equals(Module.공식계정[i])) {
				System.out.println(i + "번째에 " + 진입전_타이틀 + "을 발견 해당계정은 공식계정");
				공식계정여부 = true;
				break;
			}else{
				공식계정여부 = false;
			}
		}
		if(공식계정여부){
			util.태그end_공식계정_필터();
		}else{
			util.태그end_일반계정_필터();
		}


		util.태그end_DJ확인();
		util.태그end_qr코드();
		util.태그end_좋아요tap();
		util.태그end_공유tap();
		
		util.툴박스_듣기();
		util.툴박스_재생목록();
		util.툴박스_내리스트();
		util.툴박스_저장();
		
		util.전체듣기();
		
		util.기능더보기_앨범상세정보();
		util.기능더보기_아티스트상세정보();
		util.기능더보기_저장();
		util.기능더보기_공유();
		util.기능더보기_태그에_이_곡을_추가();
        util.기능더보기_좋아요();
		driver.navigate().back();

	}	
	
	@AfterClass
	public void tearDown() throws Exception {
		
		driver.quit();
	}

}
