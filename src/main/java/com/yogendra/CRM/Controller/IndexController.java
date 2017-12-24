package com.yogendra.CRM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
    public String index(Model model)
    {

    	System.out.println("inside");
        return "resources/static/index.html";
    }
	
	

}
