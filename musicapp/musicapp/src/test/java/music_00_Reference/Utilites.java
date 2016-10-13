package music_00_Reference;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.nts.gt.appium.unit.adbCmd;

import io.appium.java_client.android.AndroidDriver;

public class Utilites extends Module {
	
	public adbCmd cmd = new adbCmd();	
	public nMobile nmc = new nMobile();	
	public ArrayList<String> deviceSerials = null;
	public String udid = null;
	public String deviceName = null;
	public String osVersion = null;
	public static AndroidDriver driver;

	
	public void 단말정보() throws Exception{
		
		nmc.setUp();
		nmc.findAvailableDevice_test();
		nmc.openDevice();
		
		File appDir = new File("C:/Users/NAVER/git/musicapp/musicapp/musicapp/App");
		File app = new File(appDir, "Music_3.3.6.5-qa-unaligned.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("platformName", "Android");
		System.out.println(cmd.getDeviceSerial().get(0));
		capabilities.setCapability("deviceName", cmd.getDeviceSerial().get(0)); //갤s4_4.4.2
		capabilities.setCapability("platformVersion",nmc.build);
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("apppackage", "com.nhn.android.music");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 400);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.RelativeLayout")));
		
		Dimension dimensions = driver.manage().window().getSize();
		System.out.println(dimensions);

		// 앱실행시 태그안내 팝업 닫기
		int i;
		for (i = 1; i <= 3; i++) {                                                                             
			좌우_스크롤(0.6);
			Thread.sleep(500);
		}
		
		driver.findElementById("com.nhn.android.music:id/tag_title").click();
	}
	
	public void appium_tearDown() throws Exception{
		nmc.tearDown();
	}
	public void 노출대기_후_클릭(WebElement 엘리먼트) throws Exception{
		int i;
		for(i=0; i<=20;i++){
			if(엘리먼트.isEnabled()){
				엘리먼트.click();
				break;
			}else{
				Thread.sleep(500);
			}
		}	
	}
	public void 선택확인(WebElement 엘리먼트) throws Exception{
		Thread.sleep(500);
		int i;
		for(i=0; i<=10;i++){
			if(엘리먼트.isSelected()){
				assertEquals("true",엘리먼트.getAttribute("selected"));
				break;
			}else{
				Thread.sleep(500);
				엘리먼트.click();
			}
		}	
	}
	public void 노출확인(String 엘리먼트값) throws Exception{
		Thread.sleep(1500);
		WebElement 엘리먼트 = driver.findElementByXPath(엘리먼트값);
		int i;
		for(i=0; i<=20;i++){
			if(엘리먼트.isDisplayed()){
				Thread.sleep(500);
				break;
			}else{
				Thread.sleep(500);
			}
		}	
	}
	public void 타이틀명_진입테스트(String 진입전,String 진입후) throws Exception{
		
		WebElement 진입전_타이틀 = driver.findElementById(진입전);
		String 진입전_타이틀명 = 진입전_타이틀.getAttribute("text");
		진입전_타이틀.click();
		Thread.sleep(1000);

		WebElement 진입후_타이틀 = driver.findElementById(진입후);
		String 진입후_타이틀명 = 진입후_타이틀.getAttribute("text");

		assertEquals(진입전_타이틀명, 진입후_타이틀명);
		driver.navigate().back();
	}
	public void 아티스트명_진입테스트(String 진입전,String 진입후,String 클릭) throws Exception{
		// 아티스트end 진입 > 아티스트명 확인
		WebElement 아티스트 = driver.findElementById(진입전);
		String 아티스트명 = 아티스트.getAttribute("text");

		// 아티스트end 진입
		driver.findElementById(클릭).click();
		Thread.sleep(1000);

		WebElement 뮤지션end_아티스트 = driver.findElementById(진입후);
		String 뮤지션end_아티스트명 = 뮤지션end_아티스트.getAttribute("text");

		assertEquals(아티스트명, 뮤지션end_아티스트명);
		driver.navigate().back();
	}
	
	public void 로그인() throws Exception{
		
		//설정 진입하여 다른아이디 로그인
		driver.findElementById("com.nhn.android.music:id/quickmenu_indicator_btn").click();
		driver.findElementById("com.nhn.android.music:id/setting_btn").click();
		driver.findElementById("com.nhn.android.music:id/account_login_info").click();
		driver.findElementById("com.nhn.android.music:id/nloginglobal_signin_info_tv_add_to_simple_id").click();
				
		driver.findElementById("com.nhn.android.music:id/nloginglobal_normal_signin_textview_id").sendKeys("nvmu_700452");
		driver.findElementById("com.nhn.android.music:id/nloginglobal_normal_signin_textview_pw").sendKeys("navernvmu");
		driver.findElementById("com.nhn.android.music:id/nloginglobal_normal_signin_bt_signin").click();
		
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
		driver.navigate().back();
				
	}
	public void 팝업형_필터_확인(String[] 필터값) throws Exception{
		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			노출대기_후_클릭(필터);
			Thread.sleep(1000);
			
			if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
				driver.findElementByXPath(Module.TextClass + "[@text='" + 필터값[i] + "']").click();

				WebElement 필터명 = driver.findElementById(Module.SortTextID);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				
				assertEquals(필터값[i], 필터명.getAttribute("text"));

				Thread.sleep(500);
			} else {
				System.out.println("정렬순 팝업 비노출");
			}
		}
	}
	public void 팝업체크형_필터_확인(String[] 필터값) throws Exception{
		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			노출대기_후_클릭(필터);
			Thread.sleep(1000);
			
			if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
				driver.findElementByXPath(Module.CheckedTextClass + "[@text='" + 필터값[i] + "']").click();
				노출확인(Module.ListviewClass);
				
				WebElement 필터명 = driver.findElementById(Module.SortTextID);
				assertEquals(필터값[i], 필터명.getAttribute("text"));

				Thread.sleep(500);
			} else {
				System.out.println("정렬순 팝업 비노출");
			}
		}
	}
	public void 드롭박스형_필터_확인(String[] 필터값) throws Exception{
		for (int i = 0; i < 필터값.length; i++) { 

            System.out.println("진입필터 출력:" + 필터값[i]); 
            
            WebElement 필터 = driver.findElementById(Module.SortBtnID);
    		노출대기_후_클릭(필터);

    		driver.findElementByXPath(Module.TextClass+"[@text='"+ 필터값[i] +"']").click();
    		
    		WebElement 필터명 = driver.findElementById(Module.SortTextID);
    		assertEquals(필터값[i],필터명.getAttribute("text"));
    		노출확인(Module.ListviewClass);
		} 
	}
	
	public void 툴박스_듣기() throws Exception{

		driver.findElementByXPath(Module.TextClass+"[@text='전체선택']").click();//곡 전체선택버튼
		Thread.sleep(500);
        
		driver.findElementById(Module.ListenID).click();//툴박스 > 듣기 tap

		// 팝업 노출시 설정변경 후 재생
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
		
		//듣기 tap시 재생버튼이 일시정지 버튼으로 변경되는것을 확인
		if(!driver.findElements(By.xpath(Module.ImageClass+"[@content-desc='일시정지']")).isEmpty()) {
			driver.findElementById(Module.MiniplayerAnimationBtnID).click();//재생이 수행되면 일시정지 tap
		}
		else {
		   System.out.println("툴박스 > 듣기tap시 재생되지 않음");    
		}
		
	}
	public void 툴박스_재생목록(){
		driver.findElementByXPath(Module.TextClass+"[@text='전체선택']").click();//곡 전체선택버튼
		
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById(Module.AddPlaylistID).click();//툴박스 > 재생목록 tap

		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
		
		//재생목록 추가된것 확인
		String 재생목록 = driver.findElementById(Module.PlayListBtnID).getAttribute("name");//재생목록버튼의 content-desc를 확인
		System.out.println(재생목록);
		
	}
	public void 툴박스_내리스트() throws Exception{
		
		String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		
		driver.findElementByXPath(Module.TextClass+"[@text='전체선택']").click();//곡 전체선택버튼
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById(Module.AddMylistID).click();//툴박스 > 내리스트 tap
		
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty())
		{
			driver.findElementById(Module.PopupRightBtnID).click();//내 리스트 곡담기 팝업 > 새리스트에 담기 tap
			driver.findElementById(Module.TextID).sendKeys(inTime);//현재시각으로 리스트명생성
			driver.findElementById(Module.PopupRightBtnID).click();
		}
		else
		{
		   System.out.println("내 리스트에 곡답기 팝업 비노출");    
		}
		Thread.sleep(1000);
	}	
	public void 툴박스_저장() throws Exception{
		driver.findElementByXPath(Module.TextClass+"[@text='전체선택']").click();//곡 전체선택버튼
		
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById(Module.SaveID).click();//툴박스 > 저장 tap
		
		//팝업 노출확인
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
				System.out.println("해당곡 19금 음원으로 저장플로우 실패");
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		   
		   WebElement 뮤직카드 = driver.findElementById(Module.CartTitlePurchaseHistoyID);
			assertEquals("대여·구매내역",뮤직카드.getText());
			
			if(!driver.findElements(By.xpath(Module.ButtonClass+"[@text='삭제']")).isEmpty()) {
				driver.findElementByXPath(Module.ButtonClass+"[@text='삭제']").click();//뮤직카트에 곡이있으면 삭제버튼 tap
			}
			else {
			   System.out.println("뮤직카트에 곡 추가되지 않음");
			}
			
			driver.navigate().back();
		}
	}
	
	public void 전체듣기(){
		
		driver.findElementByXPath(Module.TextClass+"[@text='전체듣기']").click();//곡 전체선택버튼
		
		// 이동통신망 팝업 노출시 설정변경 후 재생
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
		
		//듣기 tap시 재생버튼이 일시정지 버튼으로 변경되는것을 확인
		if(!driver.findElements(By.xpath(Module.ImageClass+"[@content-desc='일시정지']")).isEmpty())
		{
			driver.findElementById(Module.MiniplayerAnimationBtnID).click();//재생이 수행되면 일시정지 tap
		}
		else
		{
		   System.out.println("전체듣기 tap시 재생되지 않음");    
		}		
	}
	
	public void 기능더보기_앨범상세정보(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		WebElement 곡 = driver.findElementById(Module.MoreTitleID);//더보기 팝업 아티스트명 가져오기
		String 곡명 = 곡.getAttribute("text");
		
		driver.findElementByXPath(Module.TextClass+"[@text='앨범 상세정보']").click();//앨범 상세정보 진입
		
		if(!driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='"+ 곡명 +"']")).isEmpty()) { //앨범end에서 동일곡명찾기
			System.out.println("해당 앨범end진입 확인");
		}else{
			System.out.println("해당 앨범end에서 곡명 찾지 못함");
		}
		/*
		WebElement 앨범_가수 = driver.findElementById("com.nhn.android.music:id/artist_name");//앨범end 아티스트명 가져오기
		String 앨범_가수명 = 앨범_가수.getAttribute("text");
		assertEquals(곡명, 앨범_가수명);
		*/
		driver.navigate().back();
	}
	public void 기능더보기_아티스트상세정보(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		driver.findElementByXPath(Module.TextClass+"[@text='아티스트 상세정보']").click();//아티스트 상세정보 진입
		
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) { //아티스트가 여러명인 경우 첫번째 아티스트 진입
			WebElement 앨범_가수 = driver.findElementById(Module.ArtistID);//아티스트 목록에서 첫번째 아티스트명 가져오기
			String 앨범_가수명 = 앨범_가수.getAttribute("text");
			앨범_가수명 = 앨범_가수명.replace(" ", "");
			
			driver.findElementById(Module.ArtistID).click();
			
			WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);//아티스트end 아티스트명 가져오기
			String 아티스트_가수명 = 아티스트_가수.getAttribute("text");
			아티스트_가수명 = 아티스트_가수명.replace(" ", "");

			assertEquals(앨범_가수명, 아티스트_가수명);
			
			driver.navigate().back();
		}
		else {
		   System.out.println("아티스트가 한명인 앨범");
		   
		   WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);//아티스트end 아티스트명 가져오기
			String 아티스트_가수명 = 아티스트_가수.getAttribute("text");
			아티스트_가수명 = 아티스트_가수명.replace(" ", "");
			
			driver.navigate().back();
		
			driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
			
		   WebElement 앨범_가수 = driver.findElementById(Module.MoreSubtitleID);//기능더보기 팝업에서 아티스트명 가져오기
			String 앨범_가수명 = 앨범_가수.getAttribute("text");
			앨범_가수명 = 앨범_가수명.replace(" ", "");
			
			assertEquals(앨범_가수명, 아티스트_가수명);  
			driver.findElementById(Module.PopupLeftBtnID).click();//기능더보기 취소tap
		}		
	}
	public void 기능더보기_저장(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		driver.findElementByXPath(Module.TextClass+"[@text='저장']").click();//저장 진입
		
		//팝업 노출확인
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
					
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			
			if(팝업명 == "본인 인증 안내") {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
				System.out.println("해당곡 19금 음원으로 저장플로우 실패");
			}
		}
		else {
		   System.out.println("팝업 비노출");    
				   
		   WebElement 뮤직카드 = driver.findElementById(Module.CartTitlePurchaseHistoyID);
			assertEquals("대여·구매내역",뮤직카드.getText());
				
			if(!driver.findElements(By.xpath(Module.ButtonClass+"[@text='삭제']")).isEmpty()) {
				driver.findElementByXPath(Module.ButtonClass+"[@text='삭제']").click();//뮤직카트에 곡이있으면 삭제버튼 tap
			}
			else {
			   System.out.println("뮤직카트에 곡 추가되지 않음");    
			}
		
		driver.navigate().back();
		}
	}
	public void 기능더보기_공유(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		driver.findElementByXPath(Module.TextClass+"[@text='공유']").click();//공유 진입
		
		// 공유할 sns리스트 팝업 노출
		if(!driver.findElements(By.id(Module.PopupMusicShareBtnID)).isEmpty())//공유 팝업 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();//공유팝업 닫기 tap
		}
		else
		{
		   System.out.println("공유팝업 비노출");   
		}
	}
	public void 기능더보기_태그에_이_곡을_추가(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		driver.findElementByXPath(Module.TextClass+"[@text='태그에 이 곡을 추가']").click();//태그에 이 곡을 추가 진입
		
		// 태그
		if(!driver.findElements(By.id(Module.TagAttachTagContainnerID)).isEmpty())//태그 검색페이지 진입 확인
		{
			driver.findElementById(Module.AttachTagScreenClaseBtnID).click();//태그 검색 닫기
		}
		else
		{
		   System.out.println("태그검색 페이지 비노출");   
		}
	}
	public void 기능더보기_좋아요(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		if(!driver.findElements(By.xpath(Module.TextClass+"[@text='좋아요']")).isEmpty())//'좋아요'노출 확인
		{
			driver.findElementByXPath(Module.TextClass+"[@text='좋아요']").click();//좋아요버튼 tap
		}
		else
		{
		   WebElement 좋아요취소 = driver.findElementByXPath(Module.TextClass+"[@text='좋아요 취소']");
		   if(좋아요취소.isDisplayed()){
				System.out.println("좋아요 클릭 되어있슴");
				driver.findElementById(Module.PopupLeftBtnID).click();//기능더보기 취소tap
			}else{
				System.out.println("좋아요버튼 확인필요");
			}
		}
	}
	public void 기능더보기_태그에서_곡삭제(){
		driver.findElementById(Module.ImgTrackSubItemOnID).click();//기능더보기 tap
		
		driver.findElementByXPath(Module.TextClass+"[@text='태그에서 곡 삭제']").click();//태그에 이 곡을 추가 진입
		
		// 태그
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty())//태그 검색페이지 진입 확인
		{
			driver.findElementById(Module.PopupRightBtnID).click();//태그 검색 닫기
		}
		else
		{
		   System.out.println("곡삭제 팝업 비노출");   
		}
	}
	
	public void 재생버튼(){
		driver.findElementById(Module.BtnPlaySingleID).click();//재생버튼tap

		// 팝업 노출시 설정변경 후 재생
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
		
		//듣기 tap시 재생버튼이 일시정지 버튼으로 변경되는것을 확인
		if(!driver.findElements(By.xpath(Module.ImageClass+"[@content-desc='일시정지']")).isEmpty()) {
			driver.findElementById(Module.MiniplayerAnimationBtnID).click();//재생이 수행되면 일시정지 tap
		}
		else {
		   System.out.println("툴박스 > 듣기tap시 재생되지 않음");    
		}
	}
	
	public void 앨범end_아티스트end진입(){
		
		WebElement 아티스트 = driver.findElementById(Module.ArtistNameID);//아티스트end_진입가능한_아티스트인지_확인
		String 아티스트명 = 아티스트.getAttribute("text");
		
		if (아티스트명.equals("Various Artists")) {
			System.out.println("아티스트가 Various Artists인 경우");
		} else {
			driver.findElementById(Module.ArtistNameID).click();//아티스트end_진입
			
			if (!driver.findElements(By.id(Module.PopupID)).isEmpty()) { // 아티스트가_여러명인_경우_첫번째_아티스트_진입
				WebElement 앨범_가수 = driver.findElementById(Module.ArtistID);// 아티스트_목록에서_첫번째_아티스트명_가져오기
				String 앨범_가수명 = 앨범_가수.getAttribute("text");

				driver.findElementById(Module.ArtistID).click();

				WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);// 아티스트end_아티스트명_가져오기
				String 아티스트_가수명 = 아티스트_가수.getAttribute("text");
				assertEquals(앨범_가수명, 아티스트_가수명);

				driver.navigate().back();
			} else {
				System.out.println("아티스트가 한명인 앨범");

				WebElement 아티스트_가수 = driver.findElementById(Module.MusicianNameID);// 아티스트end_아티스트명_가져오기
				String 아티스트_가수명 = 아티스트_가수.getAttribute("text");

				driver.navigate().back();

				WebElement 앨범_가수 = driver.findElementById(Module.ArtistNameID);// 타이틀중_아티스트명_가져오기
				String 앨범_가수명 = 앨범_가수.getAttribute("text");
				assertEquals(앨범_가수명, 아티스트_가수명);

			}
		}
	}
	public void 앨범end_좋아요tap(){
		
		WebElement 좋아요 = driver.findElementById(Module.LikeTextID);
		String 좋아요_전 = 좋아요.getAttribute("text");//좋아요 전의 카운트 가져오기
		
		driver.findElementById(Module.LikeImageID).click();//좋아요 tap
		
		String 좋아요_후 = 좋아요.getAttribute("text");//좋아요 후 카운트 가져오기
		
		if(좋아요_전 != 좋아요_후) {
//			driver.findElementById("com.nhn.android.music:id/like_image").click(); //좋아요가 체크되었으면 다시 체크하여 원상복귀
			System.out.println("좋아요 카운트 변경됨");
		}
		else {
			System.out.println("좋아요 카운트 변경되지 않음");
		}
	}
	public void 앨범end_공유tap(){
		
		driver.findElementById(Module.ImageShareID).click();//공유버튼 tap
		
		// 공유할 sns리스트 팝업 노출
		if(!driver.findElements(By.id(Module.SharePopupID)).isEmpty())//공유 팝업 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();//공유팝업 닫기 tap
		}
		else
		{
		   System.out.println("공유팝업 비노출");   
		}
	}
	public void 앨범end_연관태그tap(){
		
		if(!driver.findElements(By.id(Module.RelatedTagID)).isEmpty()) { //삭제버튼이 노출되는지 확인 
			WebElement 연관태그 = driver.findElementById(Module.RelatedTagID);
			String 진입전_타이틀명 = 연관태그.getAttribute("text");//연관태그명 가져오기
			진입전_타이틀명 = 진입전_타이틀명.replace("_", "");
			진입전_타이틀명 = 진입전_타이틀명.replace("\n", "");
			System.out.println(진입전_타이틀명);
			연관태그.click();
			
//			driver.findElementById("com.nhn.android.music:id/titlebar_icon_more").click();//가이드창 닫기
			
			WebElement 태그end = driver.findElementById(Module.TagNameID);
			String 진입후_타이틀명 = 태그end.getAttribute("text");//태그end 태그명 가져오기
			진입후_타이틀명 = 진입후_타이틀명.replace("_", "");
			진입후_타이틀명 = 진입후_타이틀명.replace("\n", "");
			System.out.println(진입후_타이틀명);
			
			assertEquals(진입전_타이틀명, 진입후_타이틀명);
			driver.navigate().back();
		}
		else {
		   System.out.println("앨범 연관태그 존재하지 않음");    
		}
		

	}
	public void 앨범end_필터(){
		if(!driver.findElements(By.id(Module.TextviewAlbumIndexID)).isEmpty())//순위가 노출되는지 확인
		{
			WebElement 필터_초기값 = driver.findElementById(Module.SortTextID);//순위가 노출된다명 트랙순으로 되어있는지 확인
			assertEquals("트랙순", 필터_초기값.getAttribute("text"));
			
			driver.findElementById(Module.SortTextID).click(); //필터 변경
			assertEquals("인기순", 필터_초기값.getAttribute("text"));			
		}
		else //순위가 비노출되면 인기순으로 가정하고 click시 트랙순으로 변경되는지 확인
		{
			WebElement 필터_초기값 = driver.findElementById(Module.SortTextID);
			driver.findElementById(Module.SortTextID).click(); //필터 변경
			assertEquals("트랙순", 필터_초기값.getAttribute("text"));	 
		}
	}

	public void 아티스트end_팬tap(){
		
		WebElement 좋아요 = driver.findElementById(Module.LikeTextID);
		String 좋아요_전 = 좋아요.getAttribute("text");//좋아요 전의 카운트 가져오기
		String 졸아요_전_숫자 = 좋아요_전.substring(3);//팬글자 뺴고 숫자만 뽑기
		
		driver.findElementById(Module.LikeImageID).click();//좋아요 tap
		
		String 좋아요_후 = 좋아요.getAttribute("text");//좋아요 후 카운트 가져오기
		String 졸아요_후_숫자 = 좋아요_후.substring(3);//팬글자 뺴고 숫자만 뽑기

		if(졸아요_전_숫자 != 졸아요_후_숫자) {
//			driver.findElementById("com.nhn.android.music:id/like_image").click(); //좋아요가 체크되었으면 다시 체크하여 원상복귀
			System.out.println("좋아요 카운트 변경됨");
		}
		else {
			System.out.println("좋아요 카운트 변경되지 않음");
		}
		
	}
	public void 아티스트end_공유tap(){
		
		driver.findElementById(Module.ImageShareID).click();//공유버튼 tap
		
		// 공유할 sns리스트 팝업 노출
		if(!driver.findElements(By.id(Module.SharePopupID)).isEmpty())//공유 팝업 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();//공유팝업 닫기 tap
		}
		else
		{
		   System.out.println("공유팝업 비노출");   
		}
	}
	public void 아티스트end_연관태그tap(){
		
		if(!driver.findElements(By.id(Module.RelatedTagID)).isEmpty())//삭제버튼이 노출되는지 확인
		{
			WebElement 연관태그 = driver.findElementById(Module.RelatedTagID);
			String 진입전_타이틀명 = 연관태그.getAttribute("text");//연관태그명 가져오기
			진입전_타이틀명 = 진입전_타이틀명.replace("_", "");
			진입전_타이틀명 = 진입전_타이틀명.replace("\n", "");
			System.out.println(진입전_타이틀명);
			연관태그.click();
			
//			driver.findElementById("com.nhn.android.music:id/titlebar_icon_more").click();//가이드창 닫기
			
			WebElement 태그end = driver.findElementById(Module.TagNameID);
			String 진입후_타이틀명 = 태그end.getAttribute("text");//연관태그명 가져오기
			진입후_타이틀명 = 진입후_타이틀명.replace("_", "");
			진입후_타이틀명 = 진입후_타이틀명.replace("\n", "");
			System.out.println(진입후_타이틀명);
			assertEquals(진입전_타이틀명, 진입후_타이틀명);
			
			driver.navigate().back();
		}
		else
		{
		   System.out.println("아티스트 연관태그 존재하지 않음");    
		}
		
	}
	
	public void 아티스트end_앨범탭() throws Exception{
			
		WebElement 앨범탭 = driver.findElementByXPath(Module.TextClass+"[@text='앨범']");//앨범탭 진입
		앨범탭.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertEquals("true",앨범탭.getAttribute("selected"));
        
    if(!driver.findElementsByXPath(Module.TextClass+"[@bounds='[360,852][720,981]']").isEmpty()) {
    	WebElement 선택후_앨범탭 = driver.findElementByXPath(Module.TextClass+"[@bounds='[360,852][720,981]']");
    	String 앨범수 = 선택후_앨범탭.getAttribute("text");//앨범수 가져오기
    	System.out.println(앨범수);
        
    		if(앨범수.equals("앨범 0")){
    			WebElement 문구 = driver.findElementById(Module.EmptyViewTitleID);//컨텐츠 없는 경우 노출문구확인
    			assertTrue(문구.getAttribute("text").equals("앨범 정보가 없습니다."));
    		}
    		else{
    			WebElement 아티스트end_앨범 = driver.findElementById(Module.AlbumTitleID);//앨범end 진입
    			String 아티스트end_앨범명 = 아티스트end_앨범.getAttribute("text");
				아티스트end_앨범.click();
			
				WebElement 앨범end_앨범 = driver.findElementById(Module.AlbumTitleID);//아티스트end 아티스트명 가져오기
				String 앨범end_앨범명 = 앨범end_앨범.getAttribute("text");
				assertEquals(아티스트end_앨범명, 앨범end_앨범명);
			
				driver.navigate().back();
    			}
    	}
    	else{
    		WebElement 선택후_앨범탭 = driver.findElementByXPath(Module.TextClass+"[@selected='true']");
    		String 앨범수 = 선택후_앨범탭.getAttribute("text");//앨범수 가져오기
    		System.out.println(앨범수);
    	
    		if(앨범수.equals("앨범 0")){
    			WebElement 문구 = driver.findElementById(Module.EmptyViewTitleID);//컨텐츠 없는 경우 노출문구확인
    			assertTrue(문구.getAttribute("text").equals("앨범 정보가 없습니다."));
    		}
    		else{
    			WebElement 아티스트end_앨범 = driver.findElementById(Module.AlbumTitleID);//앨범end 진입
				String 아티스트end_앨범명 = 아티스트end_앨범.getAttribute("text");
				아티스트end_앨범.click();
			
				WebElement 앨범end_앨범 = driver.findElementById(Module.AlbumTitleID);//아티스트end 아티스트명 가져오기
				String 앨범end_앨범명 = 앨범end_앨범.getAttribute("text");
				assertEquals(아티스트end_앨범명, 앨범end_앨범명);
			
				driver.navigate().back();
    			}
    		}
		}
	public void 아티스트end_동영상탭() throws Exception{
		
		WebElement 동영상탭 = driver.findElementByXPath(Module.TextClass+"[@text='동영상']");//앨범탭 진입
		동영상탭.click();
		assertEquals("true",동영상탭.getAttribute("selected"));
		
	    if(!driver.findElementsByXPath(Module.TextClass+"[@bounds='[720,852][1080,981]']").isEmpty()) {
			WebElement 선택후_동영상탭 = driver.findElementByXPath(Module.TextClass+"[@bounds='[720,852][1080,981]']");
			String 동영상수 = 선택후_동영상탭.getAttribute("text");//동영상 수 가져오기
			System.out.println(동영상수);
	        
			if(동영상수.equals("동영상 0")){
				WebElement 문구 = driver.findElementById(Module.EmptyViewTitleID);//컨텐츠 없는 경우 노출문구확인
				assertTrue(문구.getAttribute("text").equals("동영상 정보가 없습니다."));
			}
			else{
				driver.findElementById(Module.VideoPlayTimeID).click();//동영상 재생
	            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	            
	    		// 팝업 노출시 설정변경 후 재생
	    		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
	    			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
	    			String 팝업명 = 팝업.getAttribute("text");
	    			System.out.println(팝업명);
	    			if(팝업명.equals("본인 인증 안내")) {
	    				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
	    			}
	    			else {
	    			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
	    			}
	    		}
	    		else {
	    		   System.out.println("팝업 비노출"); 
	    		   if(!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { //재샌되면 back으로 복귀
	    				
		    			driver.navigate().back();
		    		}
		    		else {
		    		   System.out.println("동영상 재생되지 않음");    
		    		}
	    		}	
			}
	    }
	    else{
			WebElement 선택후_동영상탭 = driver.findElementByXPath(Module.TextClass+"[@bounds='[720,768][1080,897]']");
			String 동영상수 = 선택후_동영상탭.getAttribute("text");//동영상 수 가져오기
			System.out.println(동영상수);
	    	
	    	if(동영상수.equals("동영상 0")){
	    		WebElement 문구 = driver.findElementById(Module.EmptyViewTitleID);//컨텐츠 없는 경우 노출문구확인
	    		assertTrue(문구.getAttribute("text").equals("동영상 정보가 없습니다."));
	    	}
	    	else{
	    		driver.findElementById(Module.VideoPlayTimeID).click();//동영상 재생
	            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	                
	    		// 팝업 노출시 설정변경 후 재생
	    		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
	    			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
	    			String 팝업명 = 팝업.getAttribute("text");
	    			System.out.println(팝업명);
	    			if(팝업명.equals("본인 인증 안내")) {
	    				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
	    			}
	    			else {
	    			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
	    			}
	    		}
	    		else {
	    		   System.out.println("팝업 비노출"); 
	    		   if(!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { //재샌되면 back으로 복귀
	    				
		    			driver.navigate().back();
		    		}
		    		else {
		    		   System.out.println("동영상 재생되지 않음");    
		    		}
	    		}	 		
	    	}
	   	}
		
	}
	public void 아티스트end_곡탭(){
		
		WebElement 곡탭 = driver.findElementByXPath(Module.TextClass+"[@text='곡']");//앨범탭 진입
		곡탭.click();
		assertEquals("true",곡탭.getAttribute("selected"));
		
		if(!driver.findElementsByXPath(Module.TextClass+"[@bounds='[0,852][360,981]']").isEmpty()) {
			WebElement 선택후_곡탭 = driver.findElementByXPath(Module.TextClass+"[@bounds='[0,852][360,981]']");
			String 곡수 = 선택후_곡탭.getAttribute("text");//곡수 가져오기
			System.out.println(곡수);
		}
		else{
			WebElement 선택후_곡탭 = driver.findElementByXPath(Module.TextClass+"[@bounds='[0,768][360,897]']");
			String 곡수 = 선택후_곡탭.getAttribute("text");//곡수 가져오기
			System.out.println(곡수);
		}
	}

	public void 태그end_qr코드(){
		driver.findElementById(Module.BtnGenQRcodeID).click();//qr코드 tap

		// 공유할 sns리스트 팝업 노출
		if (!driver.findElements(By.id(Module.QRcodeImageID)).isEmpty())//qr코드 팝업 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();//qr코드 팝업 닫기 tap
		} else {
			System.out.println("QR코드 비노출");
		}
	}
	public void 태그end_DJ확인() throws Exception{
		
		WebElement 진입전_타이틀 = driver.findElementById(Module.ExtraID);
		String 진입전_타이틀명 = 진입전_타이틀.getAttribute("text");
		진입전_타이틀.click();
		Thread.sleep(1000);

		WebElement 진입후_타이틀 = driver.findElementById(Module.TagUserNicknameID);
		String 진입후_타이틀명 = 진입후_타이틀.getAttribute("text");

		assertEquals(진입전_타이틀명, 진입후_타이틀명);
		driver.navigate().back();
	}
	public void 태그end_좋아요tap(){
		
		WebElement 좋아요 = driver.findElementById(Module.LikeTextID);
		String 좋아요_전 = 좋아요.getAttribute("text");//좋아요 전의 카운트 가져오기
		
		driver.findElementById(Module.LikeImageID).click();//좋아요 tap
		
		String 좋아요_후 = 좋아요.getAttribute("text");//좋아요 후 카운트 가져오기
		
		if(좋아요_전 != 좋아요_후) {
//			driver.findElementById("com.nhn.android.music:id/like_image").click(); //좋아요가 체크되었으면 다시 체크하여 원상복귀
			System.out.println("좋아요 카운트 변경됨");
		}
		else {
			System.out.println("좋아요 카운트 변경되지 않음");
		}
	}
	public void 태그end_공유tap() {

		driver.findElementById(Module.ImageShareID).click();// 공유버튼 tap

		// 공유할 sns리스트 팝업 노출
		if (!driver.findElements(By.id(Module.SharePopupID)).isEmpty())// 공유 팝업
																		// 노출확인
		{
			driver.findElementById(Module.PopupCloseID).click();// 공유팝업 닫기 tap
		} else {
			System.out.println("공유팝업 비노출");
		}
	}
	public void 태그end_공식계정_필터() throws Exception{
		//태그end필터 "인기순","선곡순" 선택확인
		String[] 필터값 = new String[] {"선곡순","인기순"};

		for (int i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			노출대기_후_클릭(필터);

			driver.findElementByXPath(Module.TextClass + "[@text='" + 필터값[i] + "']").click();

			WebElement 필터명 = driver.findElementById(Module.SortTextID);
			assertEquals(필터값[i], 필터명.getAttribute("text"));
			노출확인(Module.ListviewClass);
		}
	}
	public void 태그end_일반계정_필터() throws Exception{
		//태그end필터 "인기순","선곡순","최신순" 선택확인
		String[] 필터값 = new String[] {"최신순", "선곡순", "인기순" };

		for (int  i = 0; i < 필터값.length; i++) {

			System.out.println("진입필터 출력:" + 필터값[i]);

			WebElement 필터 = driver.findElementById(Module.SortBtnID);
			노출대기_후_클릭(필터);

			driver.findElementByXPath(Module.TextClass + "[@text='" + 필터값[i] + "']").click();

			WebElement 필터명 = driver.findElementById(Module.SortTextID);
			assertEquals(필터값[i], 필터명.getAttribute("text"));
			노출확인(Module.ListviewClass);
		}
	}
	
	public void 태그타이틀_진입확인(String 진입전, String 진입후) throws Exception {

		WebElement 진입전_타이틀 = driver.findElementById(진입전);
		String 진입전_타이틀명 = 진입전_타이틀.getAttribute("text");
		진입전_타이틀명 = 진입전_타이틀명.replace("_", "");
		진입전_타이틀명 = 진입전_타이틀명.replace("\n", "");
		System.out.println(진입전_타이틀명);
		진입전_타이틀.click();

		Thread.sleep(1500);

		WebElement 진입후_타이틀 = driver.findElementById(진입후);
		String 진입후_타이틀명 = 진입후_타이틀.getAttribute("text");
		진입후_타이틀명 = 진입후_타이틀명.replace("_", "");
		진입후_타이틀명 = 진입후_타이틀명.replace("\n", "");
		System.out.println(진입후_타이틀명);

		assertEquals(진입전_타이틀명, 진입후_타이틀명);
	}
	public void 태그생성_태그명설정() throws Exception{
		
		String inTime  = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());

		if(!driver.findElements(By.id(Module.TagNameEditID)).isEmpty())
		{
			driver.findElementById(Module.TagTitleInputID).sendKeys(inTime);//현재시각으로 태그생성
			driver.findElementById(Module.TagAddSongBtnID).click();
		}
		else
		{
		   System.out.println("태그 생성 페이지 비노출");    
		}
		Thread.sleep(1000);
	}
	public void 태그생성_검색어_입력() throws Exception{
		if(!driver.findElements(By.id(Module.TagBrowserLayoutID)).isEmpty())
		{
			driver.findElementById(Module.TagTrackSearchInputID).sendKeys("bigbang");//소녀시대 검색
			Thread.sleep(1000);
			
			driver.findElementById(Module.TagArtistSearchBtnID).click();
		}
		else
		{
		   System.out.println("검색어 입력 페이지 비노출");    
		}
	}
	public void 태그생성_곡추가_생성(){
		int i;
		for(i=1; i <=3; i++){
			driver.findElementByXPath(Module.ListContentClass+"[" + i + "]").click();
		}
		for(i=1; i <=3; i++){
			driver.findElementByXPath(Module.HlistEditclass).click();
		}
		for(i=4; i <=6; i++){
			driver.findElementByXPath(Module.ListContentClass+"[" + i + "]").click();
		}
		driver.findElementByXPath(Module.TextClass+"[@text='3 추가']").click();
		driver.findElementByXPath(Module.TextClass+"[@text='태그 만들기 완료']").click();
	}
	
	public void 음원만_재생() throws Exception{
		
		driver.findElementByXPath(Module.TextClass+"[@text='음원만 재생']").click();//곡 전체선택버튼
		
		// 이동통신망 팝업 노출시 설정변경 후 재생
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
		
		//음원만 재생 tap시 재생버튼이 일시정지 버튼으로 변경되는것을 확인
		if(!driver.findElements(By.xpath(Module.ImageClass+"[@content-desc='일시정지']")).isEmpty())
		{
			driver.findElementById(Module.MiniplayerAnimationBtnID).click();//재생이 수행되면 일시정지 tap
		}
		else
		{
		   System.out.println("음원만 재생 tap시 재생되지 않음");    
		}		
	}
	public void 영상만_재생() throws Exception{
		driver.findElementByXPath(Module.TextClass+"[@text='영상만 재생']").click();//곡 전체선택버튼
		
		// 이동통신망 팝업 노출시 설정변경 후 재생
		if(!driver.findElements(By.id(Module.PopupID)).isEmpty()) {
			WebElement 팝업 = driver.findElementById(Module.PopupTitleID);
			String 팝업명 = 팝업.getAttribute("text");
			System.out.println(팝업명);
			if(팝업명.equals("본인 인증 안내")) {
				driver.findElementById(Module.PopupLeftBtnID).click();//본인인증 팝업 노출시 취소tap
			}
			else {
			driver.findElementById(Module.PopupRightBtnID).click();//그외의 팝업의 경우 확인tap
			}
		}
		else {
		   System.out.println("팝업 비노출");    
		}
				
		//영상만 재생 tap시 재생되는것을 확인
		if(!driver.findElements(By.id(Module.VideoViewID)).isEmpty())
		{
			driver.navigate().back();//재생이 수행되면 뒤로가기로 빠져나옴
		}
		else
		{
		   System.out.println("영상만 재생 tap시 재생되지 않음");    
		}		
		Thread.sleep(1000);
	}
	public void 뮤지션리그_재생버튼(){
		driver.findElementById(Module.BtnPlaySingleID).click();// 재생버튼tap

		// 팝업 노출시 설정변경 후 재생
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
			System.out.println("팝업 비노출");
		}

		// 듣기 tap시 재생버튼이 일시정지 버튼으로 변경되는것을 확인
		if (!driver.findElements(By.id(Module.VideoViewID)).isEmpty()) { // 재샌되면 back으로 복귀
			driver.navigate().back();// 재생이 수행되면 뒤로가기로 빠져나옴
		} else {
			if (!driver.findElements(By.xpath(Module.ImageClass+"[@content-desc='일시정지']")).isEmpty()) {
				driver.findElementById(Module.MiniplayerAnimationBtnID).click();// 재생이 수행되면 일시정지tap
			} else {
				System.out.println("재생버튼tap시 재생되지 않음");
			}
		}
	}

	public void 뮤직카트_이용권_노출확인_및_진입() throws Exception{
		if(!driver.findElements(By.id(Module.TextviewTicketNameID)).isEmpty()) {
			WebElement 대여이용권 = driver.findElementById(Module.TextviewTicketNameID);
			대여이용권.click();
			Thread.sleep(1000);
			WebElement 이용권페이지 = driver.findElementById(Module.TitleTextID);
			String 이용권페이지_타이틀 = 이용권페이지.getAttribute("text");

			assertEquals("내 이용권 / 구매",이용권페이지_타이틀);
			driver.navigate().back();		
		}else{
			System.out.println("사용중인 이용권 없음");
		}
	}
	public void 뮤직카트_이용안내_팝업확인(){
		if(!driver.findElements(By.id(Module.TextviewTicketNameID)).isEmpty()) {
			driver.findElementById(Module.ImageviewCartHelpInAdditioninfoID).click();
			if(!driver.findElements(By.id(Module.HeaderContainerID)).isEmpty()) {
				driver.findElementById(Module.DialogBtnID).click();
			}else{
				System.out.println("이용안내 팝업 노출되지 않음");
			}
		}else{
			driver.findElementById(Module.ImageviewCartHelpID).click();
			if(!driver.findElements(By.id(Module.HeaderContainerID)).isEmpty()) {
				driver.findElementById(Module.DialogBtnID).click();
			}else{
				System.out.println("이용안내 팝업 노출되지 않음");
			}
		}
		
	}
	public void MP3카트_개별곡_이용안내_팝업확인(){
		driver.findElementById(Module.ImageviewCartHelpInAdditioninfoID).click();
		if(!driver.findElements(By.id(Module.HeaderContainerID)).isEmpty()) {
			driver.findElementById(Module.DialogBtnID).click();
		}else{
			System.out.println("이용안내 팝업 노출되지 않음");
		}
	}
	public void 뮤직카트_구매_대여내역_진입확인(){
		if (!driver.findElements(By.id(Module.CartTitlePurchaseHistoyID)).isEmpty()) {
			driver.findElementById(Module.CartTitlePurchaseHistoyID).click();
			WebElement 구매_대여내역 = driver.findElementById(Module.TitleTextID);
			String 구매_대여내역_타이틀 = 구매_대여내역.getAttribute("text");
			assertEquals("구매/대여 내역", 구매_대여내역_타이틀);
			driver.navigate().back();
		} else {
			System.out.println("구매.대여내역 버튼 비노출됨");
		}
	}

	public void 좌우_스크롤(int i){
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();
		int width = dimensions.getWidth();
		
		int x = width/2;
		int y = height/2;
		driver.swipe(x+i, y, x-(i), y, i*2);
	}
	public void 위아래_스크롤(int i){
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();
		int width = dimensions.getWidth();
		
		int x = width/2;
		int y = height/2;
		driver.swipe(x, y+i, x, y-(i), i*2);
	}
	
	public void 좌우_스크롤(double persent){
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();
		int width = dimensions.getWidth();
		
		int x = width/2;
		int y = height/2;
		int i = (int) (width*persent);
		driver.swipe(x+(i/2), y, x-(i/2), y, i);
	}	
	public void 위아래_스크롤(double persent){
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();
		int width = dimensions.getWidth();
		
		int x = width/2;
		int y = height/2;
		int i = (int) (height*persent);
		driver.swipe(x, y+(i/2), x, y-(i/2), i);
	}
}
