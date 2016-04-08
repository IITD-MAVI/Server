package iitd.assistech.mavi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	
	private static final Logger LOG = Logger.getLogger(Application.class);

	public static void main(String[] args) throws IOException {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");		

//		Random random = new Random();
//		Location location = new Location(random.nextDouble(), random.nextDouble());
//		System.out.println(JsonUtil.toJson(location));
//		new Controller().post(JsonUtil.toJson(location));
//		
//		LOG.debug( JsonUtil.toJson(location) );
		
		String line="";
		String[][] file = new String[320][5];
		int i=0;
		BufferedReader br = new BufferedReader(new FileReader("final data.csv"));
		while((line = br.readLine()) != null) {
			file[i] = line.split(",");
			System.out.println(file[i].length);
			i++;
		}
		br.close();
		
//		for(i=0;i<320;i++) {
//			System.out.println(file[i][0]+", "+file[i][1]+", "+file[i][2]+", "+file[i][3]+", "+file[i][4]);
//		}
		
		ctx.close();

	}

}
