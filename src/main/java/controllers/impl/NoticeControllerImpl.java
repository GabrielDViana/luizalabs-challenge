package controllers.impl;

import controllers.NoticeController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Notice", tags = {"Notice"})
public class NoticeControllerImpl implements NoticeController {
}
