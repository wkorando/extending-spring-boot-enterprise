package com.ibm.cloud.startup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIBMCloudStartupService {
	@Autowired
	private IBMCloudStartupService service;

	@Test
	public void test() {
		service.call();
	}
	
	@Test
	public void testNewApp() {
		service.addNewApp();
	}

}
