package iitd.assistech.mavi.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson
public class SignBoard {

    /**
     */
    private double latitude;

    /**
     */
    private double longitude;
    
    /**
     */
    private boolean bilingual;

    /**
     */
    private String engContent;
    
    
    /**
     */
    public SignBoard() {
    	
    }
    
    /**
     */
    public SignBoard(double latitude, double longitude) {
    	this.latitude = latitude;
    	this.longitude = longitude;
    }

}
