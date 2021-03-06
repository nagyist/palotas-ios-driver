package org.uiautomation.ios.e2e;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.uiautomation.ios.UIAModels.UIARect;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIAApplication;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIADriver;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIATarget;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIAWindow;
import org.uiautomation.ios.server.IOSServer;
import org.uiautomation.ios.server.IOSServerConfiguration;

public class EndToEndTests {

	private IOSServer server;
	private static String[] args = {"-port", "4444","-host", "localhost"};
	private static IOSServerConfiguration config = IOSServerConfiguration.create(args);

	@BeforeClass
	public void startServer() throws Exception {
		server = new IOSServer(config);
		server.start();
	}

	@Test
	public void scriptStartsAndRegisterToServer() {
		RemoteUIADriver driver = null;
		try {
			Map<String, Object> caps = new HashMap<String, Object>();
			caps.put("sdkVersion", "5.1");
			caps.put("language", "fr");
            caps.put("locale", ""+Locale.CANADA_FRENCH);
            caps.put("ios.switches", Arrays.asList(new String[]{"useQA","YES"}));
			driver = new RemoteUIADriver("http://"+config.getHost()+":"+config.getPort()+"/wd/hub", caps);

			RemoteUIATarget target = driver.getLocalTarget();
			Assert.assertEquals(target.getReference(), "1");
			target = (RemoteUIATarget) driver.getLocalTarget();
			Assert.assertEquals(target.getReference(), "2");
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test
	public void targetAttributesTests() {
		RemoteUIADriver driver = null;
		try {
			Map<String, Object> caps = new HashMap<String, Object>();
			caps.put("version", "5.1");
			
			driver = new RemoteUIADriver("http://" + config.getHost() + ":"+config.getPort()+"/wd/hub", caps);

			RemoteUIATarget target = driver.getLocalTarget();
			Assert.assertEquals(target.getReference(), "1");

			String model = target.getModel();
			Assert.assertEquals(model, "iPhone Simulator");

			String name = target.getName();
			Assert.assertEquals(name, "iPhone Simulator");

			String systemName = target.getSystemName();
			Assert.assertEquals(systemName, "iPhone OS");

			String systemVersion = target.getSystemVersion();
			Assert.assertEquals(systemVersion, "5.1");

			UIARect rect = target.getRect();
			Assert.assertEquals(rect.getX(), 0);
			Assert.assertEquals(rect.getX(), 0);
			Assert.assertEquals(rect.getHeight(), 480);
			Assert.assertEquals(rect.getWidth(), 320);

		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test
	public void screenshot() throws InterruptedException {
		RemoteUIADriver driver = null;
		try {
			Map<String, Object> caps = new HashMap<String, Object>();
			caps.put("browserName", "iphone");
			caps.put("sdkVersion", "5.1");
			driver = new RemoteUIADriver("http://"+config.getHost()+":"+config.getPort()+"/wd/hub", caps);

			RemoteUIATarget target = driver.getLocalTarget();
			
			File to = new File("ss.png");
			target.takeScreenshot(to.getAbsolutePath());
			Assert.assertTrue(to.exists());
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test
	public void frontMostApp() throws InterruptedException {
		RemoteUIADriver driver = null;
		try {
			Map<String, Object> caps = new HashMap<String, Object>();
			caps.put("version", "5.0");
			driver = new RemoteUIADriver("http://"+config.getHost()+":"+config.getPort()+"/wd/hub", caps);

			RemoteUIATarget target = driver.getLocalTarget();
			RemoteUIAApplication app = target.getFrontMostApp();
			System.out.println(app);
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test
	public void mainWindow() throws InterruptedException {
		RemoteUIADriver driver = null;
		try {
			Map<String, Object> caps = new HashMap<String, Object>();
			caps.put("version", "5.0");
			driver = new RemoteUIADriver("http://"+config.getHost()+":"+config.getPort()+"/wd/hub", caps);

			RemoteUIATarget target = driver.getLocalTarget();
			RemoteUIAApplication app = target.getFrontMostApp();
			RemoteUIAWindow window = app.getMainWindow();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
	
	
	
	
	
	
	

	@AfterClass
	public void stopServer() throws Exception {
		server.stop();
	}
}
