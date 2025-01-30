package org.example.webexam.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


public class ErrorController implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("1111111111111111");
        System.out.println(request);
        System.out.println(response);
        System.out.println(handler);
        System.out.println(ex);
        System.out.println("1111111111111111");
        return null;
    }

    //BasicErrorController 가 기본으로 사용되고 있어 ErrorController을 상속함으로써 에러 컨트롤러를 작성한다.
//    @RequestMapping(path = "/error")
//    public Map<String, Object> handle(HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", request.getAttribute("jakarta.servlet.error.status_code"));
//        map.put("reason", request.getAttribute("jakarta.servlet.error.message"));
//        return map;
//    }


}
