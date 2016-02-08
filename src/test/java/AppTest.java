import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AppTest {

  @Test
  public void findAngle_is0IfBothHandsFace12_0() {
    App clockAngle = new App();
    assertEquals("0.0", App.findAngle("12","60"));
  }

  @Test
  public void findAngle_is90IfHandsFace12And3_90() {
    App clockAngle = new App();
    assertEquals("90.0", App.findAngle("12","15"));
  }
}
