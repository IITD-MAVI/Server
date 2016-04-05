package iitd.assistech.mavi;

import java.util.Random;

import iitd.assistech.mavi.Controller;
import iitd.assistech.mavi.domain.Location;
import iitd.assistech.mavi.utility.JsonUtil;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	
	private static final Logger LOG = Logger.getLogger(Application.class);

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");		

		Random random = new Random();
		Location location = new Location(random.nextDouble(), random.nextDouble());
		System.out.println(JsonUtil.toJson(location));
		new Controller().post(JsonUtil.toJson(location));
		
		LOG.debug( JsonUtil.toJson(location) );
		
		ctx.close();

	}

}
