package iitd.assistech.mavi;

import iitd.assistech.mavi.domain.SignBoard;
import iitd.assistech.mavi.event.CreateEvent;
import iitd.assistech.mavi.utility.JsonUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	
//	private static final Logger LOG = Logger.getLogger(Application.class);

	public static void main(String[] args) throws IOException {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");		

//		Random random = new Random();
//		Location location = new Location(random.nextDouble(), random.nextDouble());
//		System.out.println(JsonUtil.toJson(location));
//		new Controller().post(JsonUtil.toJson(location));
//		
//		LOG.debug( JsonUtil.toJson(location) );
		
		Controller controller = new Controller();
		String line="";
		String[] file = new String[10];
		int i=0;
		SignBoard signBoard = new SignBoard();
		BufferedReader br = new BufferedReader(new FileReader("final data.csv"));
		while((line = br.readLine()) != null) {
			file = line.split(",");
			if(Integer.parseInt(file[0]) != i) {
				if(i>0) {
					controller.post(JsonUtil.toJson(new CreateEvent<SignBoard>(signBoard)));
				}
				
				i = Integer.parseInt(file[0]);
				if(!file[1].isEmpty()) {
					String[] coordinate = file[1].split(";");
					signBoard = new SignBoard(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1]));
				}
				else {
					signBoard = new SignBoard();
				}
				
				signBoard.setBilingual(file[3].startsWith("BI"));
				signBoard.setEngContent(file[2]);
			}
			else if(file.length>1) {
				signBoard.setEngContent(signBoard.getEngContent()+"\n"+file[2]);
			}
//			System.out.println(file.length);
		}
		controller.post(JsonUtil.toJson(new CreateEvent<SignBoard>(signBoard)));
//		System.out.println(signBoard.getEngContent());
		br.close();
		
//		for(i=0;i<320;i++) {
//			System.out.println(file[i][0]+", "+file[i][1]+", "+file[i][2]+", "+file[i][3]+", "+file[i][4]);
//		}
		
		ctx.close();

	}

}
