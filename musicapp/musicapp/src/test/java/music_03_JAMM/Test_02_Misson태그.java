package music_03_JAMM;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import music_00_Reference.Module;
import music_00_Reference.Utilites;

public class Test_02_Misson태그 extends Utilites {

	Utilites util = new Utilites();

	@BeforeClass
	public void setUp() throws Exception {

		util.단말정보();
	}

	@Test
	public void Misson태그_테스트() throws Exception {
		System.out.println("Misson태그_테스트 시작!!");
		// 태그end 최초진입시 가이드창 닫기
		driver.findElementById(Module.TagTitleID).click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.navigate().back();

		util.위아래_스크롤(0.66);
		Thread.sleep(1500);
		// Quest태그 진입 확인
		util.태그타이틀_진입확인(Module.QuestTitleID, Module.QuestTitleID);
		util.노출확인(Module.ListviewClass);

		// 도전태그 필터확인
		String[] 필터값 = new String[] { "인기순", "최근등록순" };

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			driver.findElementById(Module.SortBtnID).click();
			util.노출확인(Module.ListviewClass);

			WebElement 변경된_필터 = driver.findElementById(Module.SortTextID);
			assertEquals(필터값[i], 변경된_필터.getAttribute("text"));
		}
		// 도전태그 리스트중 첫번째 태그 진입확인
		util.태그타이틀_진입확인(Module.TagTitleID, Module.TagNameID);
		driver.navigate().back();

		// 첫번째 태그 프로필 이미지로 DJend진입 확인
		util.아티스트명_진입테스트(Module.UserNicknameID, Module.TagUserNicknameID, Module.ProFileImgID);

		// 도전태그 더보기 진입확인
		driver.findElementByXPath(Module.TextClass + "[@text='더보기']").click();
		util.노출확인(Module.ListviewClass);
		driver.navigate().back();

		// #참여하기 > 태그생성 > 노출까지 확인
		driver.findElementById(Module.QuestSubmitBtnID).click();
		util.태그생성_태그명설정();
		util.태그생성_검색어_입력();
		util.팝업체크형_필터_확인(태그생성페이지_검색결과);
		util.태그생성_곡추가_생성();

		Thread.sleep(2000);
		
		util.태그타이틀_진입확인(Module.TagTitleID, Module.TagNameID);

		String 태그곡수 = driver.findElementById(Module.TrackCountID).getAttribute("text");
		assertEquals(태그곡수, "3");
		
		for(int i=0;i < 3;i++){
			util.기능더보기_태그에서_곡삭제();
		}
	}

	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}
}
