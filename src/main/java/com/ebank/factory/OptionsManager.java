package com.ebank.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions chromeOptions;
	private FirefoxOptions fire;
	private EdgeOptions edge;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions() {
		chromeOptions = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			chromeOptions.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			chromeOptions.addArguments("--incognito");
		}
		return chromeOptions;
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fire = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fire.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fire.addArguments("--incognito");
		}
		return fire;
	}
	
	public EdgeOptions getEdgeOptions() {
		edge = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			edge.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			edge.addArguments("--incognito");
		}
		return edge;
	}

}
