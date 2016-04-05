package iitd.assistech.mavi;
import iitd.assistech.mavi.domain.Location;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Location.class, transactional = false)
public class LocationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
