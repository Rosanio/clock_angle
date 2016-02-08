import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    System.out.println(findAngle("11","45"));
  }

  public static String findAngle(String hourHand, String minuteHand) {
    Double tempMinuteHand = Double.parseDouble(minuteHand);
    Double numberHourHand;
    if(hourHand == "12") {
      if(minuteHand == "60" || (tempMinuteHand >= 1.0 && tempMinuteHand <= 30.0)) {
        String zeroHourHand = "0";
        numberHourHand = Double.parseDouble(zeroHourHand);
      } else {
        numberHourHand = Double.parseDouble(hourHand);
      }
    } else {
      numberHourHand = Double.parseDouble(hourHand);
    }
    Double numberMinuteHand;
    if(minuteHand == "60") {
      String zeroMinuteHand = "0";
      numberMinuteHand = Double.parseDouble(zeroMinuteHand);
    } else {
      numberMinuteHand = Double.parseDouble(minuteHand);
    }

    Double hourFraction = numberHourHand / 12;
    Double minuteFraction = numberMinuteHand / 60;

    Double angle = 360 * (hourFraction - minuteFraction);
    if (angle < 0){
      angle = angle * -1;
    }
    if(angle > 180) {
      angle = 360.0 - angle;
    }
    Double roundedAngle = Math.floor(angle+0.5);
    String stringAngle = Double.toString(roundedAngle);
    return stringAngle;
  }
}
