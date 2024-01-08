package com.scaler.firstapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// This class will have multiple Methods
// Each method will be mapped to a specific URL
// This class will be service rest API (HTTPS)
// localhost:8080/hello/say/Kartik/3
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/say/{name}/{times}")
    public String Hello(@PathVariable("name") String name,@PathVariable("times") int times) {
        String ans="";
        for(int i=0;i<times;i++){
            ans+="Hello "+ name;
            ans+="<BR>";
        }
        return ans;
    }


}
