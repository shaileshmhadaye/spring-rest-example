package guru.springframework.controllers;

import guru.springframework.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Controller
public class UserController {
    private AppService appService;

    public UserController(AppService appService) {
        this.appService = appService;
    }

    @RequestMapping({"","/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/users")
    public String user(Model model, ServerWebExchange serverWebExchange){
        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();

        Integer limit = new Integer(map.get("limit").get(0));

        log.debug("Received Limit value: " + limit);
        //default if null or zero
        if(limit == null || limit == 0){
            log.debug("Setting limit to default of 10");
            limit = 10;
        }

        model.addAttribute("users", appService.getUsers(limit));
        return "userlist";
    }
}
