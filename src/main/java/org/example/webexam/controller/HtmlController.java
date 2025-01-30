package org.example.webexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HtmlController {

    @GetMapping("/happys")
    public String happys(){
        System.out.println("happys");
        return "happys";
    }

    @GetMapping("/hello")
    @ResponseBody // 잘 사용하지 않음
    public String hello() {
        // 사용자가 요청을 보낼때 json, html, header 정보등을 요청할 수 있음
        return "index"; // 이게 뷰이름임 // 보통의 경우 템플릿 이름을 리턴함
    }

    @GetMapping("/makeError")
    public String makeError() throws Exception {
        String rtnVal = "index";
        if (rtnVal.equals("index")) {
            throw new Exception();
        }
        return rtnVal;
    }


}
