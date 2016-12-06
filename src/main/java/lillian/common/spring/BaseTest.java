package lillian.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BaseTest {
	
	private static ApplicationContext ac = (ApplicationContext) new FileSystemXmlApplicationContext("spring.xml").getBean("ear.context");
	
	public static ApplicationContext getAc() {
		return ac;
	}

}
