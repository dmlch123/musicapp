package music_00_Reference;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nhncorp.api.DeviceOption;
import com.nhncorp.api.MobileDevice;
import com.nhncorp.api.MobileLauncher;
import com.nhncorp.api.UploadTask;
import com.nhncorp.api.enumeration.DeviceOsType;
import com.nhncorp.api.enumeration.ScreenOnOff;
import com.nhncorp.api.enumeration.ScreenQuality;
import com.nhncorp.api.enumeration.ScreenScale;

public class nMobile {
	private MobileLauncher sut; // system under test

	private String userId = "nt11165";
	private String password = "a1354687!";
	public String buildName = null;
	private DeviceOption deviceOption;

	public void setUp() throws Exception {
		sut = new MobileLauncher(userId, password);
		
		sut.login();
		if (sut.isValidLogin() == false) {
			throw new Exception("LOGIN FAIL.");
		}

		deviceOption = new DeviceOption();
		deviceOption.setInstance("Devices");
	}

	public void tearDown() throws Exception {
		
		Thread.sleep(3*1000);
		if (sut != null) {
			sut.closeDevice();
		}
	}

	public void findAvailableDevice_test() throws Exception {
		// Given
		deviceOption.setUseTime(2);
		deviceOption.setServiceName("API 테스트");
		deviceOption.setCountry("Korea");
		
//		deviceOption.setModel("SM-G920S");
		deviceOption.setBuild("6.0.1");
		// openDevice, openDevices API 가 있습니다. 브랜드는 서비스상 유니크한 명칭으로 단일 단말을 오픈할때 사용합니다.
		
		List<MobileDevice> availableDevices = sut.findAvailableDevice(deviceOption);

		System.out.println("Available device count: " + availableDevices.size());
		System.out.println("### \n" + availableDevices.toString());
		
		
		deviceOption.setAdbFile("C:/Android/android-sdk_r24.3.4-windows/android-sdk-windows/platform-tools/adb.exe");

		deviceOption.setQuality(ScreenQuality.FIVE);
		deviceOption.setScale(ScreenScale.FIVE);
		deviceOption.setScreenOnOff(ScreenOnOff.ON);

		// When
		for(int i = 0; i<availableDevices.size(); i++){
			String brandName = availableDevices.get(i).getBrandFullName();
			String buildName = availableDevices.get(i).getBuild();
			System.out.println(i + " : " + brandName + " , " + buildName);	
		}
		
		String brandName = availableDevices.get(0).getBrandFullName();
		deviceOption.setBrand(brandName);
		
		String buildName = availableDevices.get(0).getBuild();
		System.out.println(buildName);
		// deviceOption.setBrand(brandName);
		// 브랜드명을 입력하는 경의 country, model, build 는 입력하실필요가 없습니다.
		
		System.out.println(deviceOption);
		MobileDevice openDevice = sut.openDevice(deviceOption);

		if (openDevice != null || openDevice.isOpen()) {
			System.out.println(openDevice.getBrand());
			Thread.sleep(3 * 1000);

		//	UploadTask installTask = openDevice.installApp("C:\\Music_3.3.4-release.apk");
			 
		}
		
		
	
		// Then
	}
/*
	public void openDevice_test() throws InterruptedException {
		// Given
		deviceOption.setUseTime(2);
		deviceOption.setServiceName("API 테스트");
		deviceOption.setAdbFile("C:/Android/android-sdk_r24.3.4-windows/android-sdk-windows/platform-tools/adb.exe");

		deviceOption.setQuality(ScreenQuality.FIVE);
		deviceOption.setScale(ScreenScale.FIVE);
		deviceOption.setScreenOnOff(ScreenOnOff.ON);

		// deviceOption.setCountry("Korea");
		// deviceOption.setModel("SM-G900S");
		// deviceOption.setBuild("5.0");
		deviceOption.setBrand("Galaxy S5 KR#04"); // 브랜드명을 입력하는 경의 country, model, build 는 입력하실필요가 없습니다.
		// openDevice, openDevices API 가 있습니다. 브랜드는 서비스상 유니크한 명칭으로 단일 단말을 오픈할때 사용합니다.

		MobileDevice openDevice = sut.openDevice(deviceOption);

		if (openDevice != null || openDevice.isOpen()) {

			Thread.sleep(3 * 1000);

		}
		// openDevice.recordScreen(OnOffType.ON);
		openDevice.captureScreen();
		Assert.assertTrue(openDevice.isOpen());
	}

	public void openAvailableDevices_test() throws Exception {

		// Given
		deviceOption.setUseTime(2); // 필수
		deviceOption.setServiceName("옵션 단말 열기 테스트"); // 필수

		deviceOption.setCountry("Korea");
		// deviceOption.setModel("SM-G900S");
		deviceOption.setBuild("4.4.2");

		// When
		List<MobileDevice> openDevices = sut.openDevices(deviceOption);
		Thread.sleep(5 * 1000);

		// Then
		System.out.println("### size: " + openDevices.size());
		System.out.println("### \n" + openDevices.toString());

		if (openDevices != null) {

			Thread.sleep(3 * 1000);

		}
	}
	public void openDeviceAppInstall() throws Exception{
		// Given
	 	deviceOption.setUseTime(2); // 필수
	 	deviceOption.setServiceName("API 앱설치 테스트"); // 필수
	    deviceOption.setAdbFile("C:/Android/android-sdk_r24.3.4-windows/android-sdk-windows/platform-tools/adb.exe");

		deviceOption.setQuality(ScreenQuality.FIVE);
		deviceOption.setScale(ScreenScale.FIVE);
		deviceOption.setScreenOnOff(ScreenOnOff.ON);

		deviceOption.setBrand("Galaxy S5 KR#04"); 

	 	String apkFilePath = "C://Music_3.3.3-release.apk";

	 	// When
	 	MobileDevice devices = sut.openDevice(deviceOption);
	 	sut.installApp(apkFilePath);
	 	
	 	if (devices != null || devices.isOpen()) {

			Thread.sleep(3 * 1000);

		}
		
	}
*/

}
