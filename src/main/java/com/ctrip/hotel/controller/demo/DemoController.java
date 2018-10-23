package com.ctrip.hotel.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhao.yong
 * @datetime 2018/10/22
 **/
@Controller
public class DemoController {

    @RequestMapping("/vuedemo")
    public String demoView(){

        return "components/vue_demo";
    }
}
