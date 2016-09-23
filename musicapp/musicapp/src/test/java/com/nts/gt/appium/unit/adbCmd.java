package com.nts.gt.appium.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adbCmd { 

	Map<String, String> devices = new HashMap<String, String>();
	ArrayList<String> deviceSerail = new ArrayList<String>();
	ArrayList<String> deviceModel = new ArrayList<String>();
	Process p;
 
	public String runCommand(String command) throws InterruptedException, IOException {
		 
		p = Runtime.getRuntime().exec(command);
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String line = "";
		String allLine = "";
				
		while ((line = r.readLine()) != null) {
			/* if (line.isEmpty()) {
				break;
			}*/
			allLine = allLine + "" + line + "\n";
            if (line.contains("Console LogLevel: debug") && line.contains("Complete"))
				break;	
		}
		return allLine;
	}

	public void startADB() throws Exception {
		
		String output = runCommand("adb start-server");
		String[] lines = output.split("\n");
		
		if (lines[0].contains("internal or external command")) {
			System.out.println("Please set ANDROID_HOME in your system variables");
		}
	}

	public void stopADB() throws Exception {
		
		runCommand("adb kill-server");
	}

	public Map<String, String> getDevicesInfo() throws Exception {

		startADB(); // start adb service
		String output = runCommand("adb devices");
		String[] lines = output.split("\n");

		if (lines.length <= 1) {
			System.out.println("\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A"
					+ "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A"
					+ "\uD83C\uDF7A" + "\uD83C\uDF7A");
			System.out.println("No Device Connected");
			System.out.println("\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A"
					+ "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A" + "\uD83C\uDF7A"
					+ "\uD83C\uDF7A" + "\uD83C\uDF7A");
			
			stopADB();
			return null;
		}
		
		else {
			for (int i = 1; i < lines.length; i++) {
				lines[i] = lines[i].replaceAll("\\s+", "");

				if (lines[i].contains("device")) {
					lines[i] = lines[i].replaceAll("device", "");
					String deviceID = lines[i];
					String model = runCommand("adb -s " + deviceID + " shell getprop ro.product.model").replaceAll("\\s+", "");
					String brand = runCommand("adb -s " + deviceID + " shell getprop ro.product.brand").replaceAll("\\s+", "");
					String osVersion = runCommand("adb -s " + deviceID + " shell getprop ro.build.version.release")	.replaceAll("\\s+", "");
					String deviceName = brand + " " + model;

					devices.put("deviceID" + i, deviceID);
					devices.put("deviceName" + i, deviceName);
					devices.put("osVersion" + i, osVersion);
					
				} else if (lines[i].contains("unauthorized")) {
					lines[i] = lines[i].replaceAll("unauthorized", "");
					String deviceID = lines[i];
					
				} else if (lines[i].contains("offline")) {
					lines[i] = lines[i].replaceAll("offline", "");
					String deviceID = lines[i];
				}
			}
			
			return devices;
		}
	}

	public ArrayList<String> getDeviceSerial() throws Exception {

		startADB(); // start adb service
		String output = runCommand("adb devices");
		String[] lines = output.split("\n");

		if (lines.length <= 1) {
			System.out.println("No Device Connected");
			//	stopADB();
			return null;
		}
		
		else {
			for (int i = 1; i < lines.length; i++) {
				
				lines[i] = lines[i].replaceAll("\\s+", "");

				if (lines[i].contains("device")) {
					lines[i] = lines[i].replaceAll("device", "");
					String deviceID = lines[i];
					deviceSerail.add(deviceID);
				} 
				
				else if (lines[i].contains("unauthorized")) {
					lines[i] = lines[i].replaceAll("unauthorized", "");
					String deviceID = lines[i];
				} 
				
				else if (lines[i].contains("offline")) {
					lines[i] = lines[i].replaceAll("offline", "");
					String deviceID = lines[i];
				}
			}
			return deviceSerail;
		}
	}

	public String getDeviceModel(String deviceID) {
		
		String deviceModelName = null;
		String brand = null;
		 
		try {
			deviceModelName = runCommand("adb -s " + deviceID + " shell getprop ro.product.model").replaceAll("\\W", "");

			brand = runCommand("adb -s " + deviceID + " shell getprop ro.product.brand");
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		//deviceModel=deviceModelName.concat("_"+brand);

		return deviceModelName;
		//return deviceModel.trim(); 
	} 

	public String getDeviceOS(String deviceID) {
		
		String deviceOS = null;
		//String cmd = "adb -s " + deviceID + " shell getprop ro.build.version.sdk";
		String cmd = "adb -s " + deviceID + " shell getprop ro.build.version.release";
 
		try {
			System.out.println (cmd);
			deviceOS = runCommand(cmd).replaceAll("\n", "");
			//deviceOS = runCommand(cmd);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		return deviceOS;
	}

	public void closeRunningApp(String deviceID, String appPackage) throws InterruptedException, IOException {

		String cmd = "adb -s " + deviceID + " shell am force-stop " + appPackage;
		System.out.println (cmd);
		
		runCommand(cmd);
	}

	public void clearAppData(String deviceID, String appPackage) throws InterruptedException, IOException {

		String cmd = "adb -s " + deviceID + " shell pm clear " + appPackage;
		System.out.println (cmd);
		
		runCommand(cmd);
	}
}
