package org.example.webexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/main")
public class HtmlController {

    @GetMapping("/hello")
    @ResponseBody // 잘 사용하지 않음
    public String hello() {
        return "index"; // 보통의 경우 템플릿 이름을 리턴함
    }
}
