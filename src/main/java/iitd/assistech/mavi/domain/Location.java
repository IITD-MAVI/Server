package iitd.assistech.mavi.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson
public class Location {

    /**
     * Latitude of the location
     */
    @NotNull
    private double latitude;

    /**
     * Longitude of the location
     */
    @NotNull
    private double longitude;
    
    public Location(double latitude, double longitude) {
    	this.latitude = latitude;
    	this.longitude = longitude;
    }
}
