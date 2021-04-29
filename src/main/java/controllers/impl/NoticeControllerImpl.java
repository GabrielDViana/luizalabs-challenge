package controllers.impl;

import controllers.NoticeController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Notice", tags = {"Notice"})
public class NoticeControllerImpl implements NoticeController {
//    @Value("${phoneNumber:** Todo: buy a phone number from Twilio **}")
//    private String phoneNumber;

//    @GetMapping("/")
//    public ModelAndView showDashboard(){
//        ModelAndView dashboard = new ModelAndView("dashboard");
//        dashboard.addObject("phoneNumber", phoneNumber);
//        return dashboard;
//    }
}
