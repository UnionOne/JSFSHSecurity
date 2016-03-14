package com.itibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringSecurityHelloController {
    @RequestMapping(value = "/login.xhtml", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/admin.xhtml", method = RequestMethod.GET)
    public ModelAndView adminPage(){
        return new ModelAndView("admin");
    }
}