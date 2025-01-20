package org.example.webexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

import java.util.List;


@SpringBootApplication
public class WebExamApplication implements CommandLineRunner {

    @Autowired
    List<HandlerMapping> handlerMappingList;    // HandlerMapping Bean들을 모두 가져온다.

    @Autowired
    List<HandlerAdapter> handlerAdapterList;    // HandlerAdapter  Bean들을 모두 가져온다.

    @Autowired
    List<ViewResolver> viewResolverList;

    public static void main(String[] args) {
        SpringApplication.run(WebExamApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (ViewResolver viewResolver : viewResolverList) {
            System.out.println(viewResolver.getClass().getName());
        }
    }
}
