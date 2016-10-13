package music_00_Reference;

import java.util.List;

import org.junit.Assert;

import com.nhncorp.api.DeviceOption;
import com.nhncorp.api.MobileDevice;
import com.nhncorp.api.MobileLauncher;
import com.nhncorp.api.enumeration.ScreenOnOff;
import com.nhncorp.api.enumeration.ScreenQuality;
import com.nhncorp.api.enumeration.ScreenScale;

public class nMobile {
	private MobileLauncher sut; // system under test

	private String userId = "아이디";
	private String password = "비밀번호";
	
	public String brandName = null;
	public String modelName = "SM-N910K";
	public String build = "5.1.1";
	public String serviceName = "뮤직 테스트";
	public String country = "Korea";
	public int useTime = 2;

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

		if (sut != null) {
			sut.closeDevice();
		}
		Thread.sleep(3 * 1000);
	}

	public void findAvailableDevice_test() throws Exception {

		deviceOption.setUseTime(useTime);
		deviceOption.setServiceName(serviceName);
		deviceOption.setCountry(country);
		deviceOption.setModel(modelName);
		deviceOption.setBuild(build);

		List<MobileDevice> availableDevices = sut.findAvailableDevice(deviceOption);

		System.out.println("Available device count: " + availableDevices.size());

		for (int i = 0; i < availableDevices.size(); i++) {
			brandName = availableDevices.get(i).getBrandFullName();
			build = availableDevices.get(i).getBuild();
			System.out.println(i + " : " + brandName + " , " + build);
		}
		brandName = availableDevices.get(0).getBrandFullName();
	}

	public void openDevice() throws InterruptedException {
		// Given
		deviceOption.setUseTime(useTime);
		deviceOption.setServiceName(serviceName);
		deviceOption.setBrand(brandName);

		deviceOption.setAdbFile("C:/Android/android-sdk_r24.3.4-windows/android-sdk-windows/platform-tools/adb.exe");

		deviceOption.setQuality(ScreenQuality.FIVE);
		deviceOption.setScale(ScreenScale.FIVE);
		deviceOption.setScreenOnOff(ScreenOnOff.ON);

		System.out.println(deviceOption);
		MobileDevice openDevice = sut.openDevice(deviceOption);

		if (openDevice != null || openDevice.isOpen()) {

			Thread.sleep(3 * 1000);

		}
		Assert.assertTrue(openDevice.isOpen());
	}
}
