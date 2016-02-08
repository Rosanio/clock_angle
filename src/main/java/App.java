import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();

      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/angle", (request, response) -> {
      HashMap model = new HashMap();
      String hour = request.queryParams("hour");
      String minute = request.queryParams("minute");
      String angleAnswer = findAngle(hour, minute);

      model.put("angleAnswer", angleAnswer);
      model.put("template", "templates/angle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
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
    if(numberHourHand > 12 || numberHourHand < 0 || numberMinuteHand > 60 || numberMinuteHand < 0) {
      stringAngle = "nonexistent";
    }
    return stringAngle;
  }
}
