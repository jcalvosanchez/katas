package com.htic.idi.codekata.romannumbers;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={	"classpath:spring/applicationContext_root.xml", 
									"classpath:spring/applicationContext_test.xml"})
public class BaseTest {

	@Autowired private ApplicationContext applicationContext;
	protected static Logger logger	= Logger.getLogger(BaseTest.class.getName());


	@Test
	public void applicationContextNotNullTest () {
		Assert.assertNotNull(applicationContext);
	}


	//Getters && Setters
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}