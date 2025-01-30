JSP 사용하려면 
tomcat-embed-jasper
jslt 라이브러리를 꼭 추가 해줘야한다
jsp 인식못한다 

-------------------------------------------------------------------


Dispacher Servlet 

Handler
 - 핸들러 메서드
    Controller Class의 Mapping된 메서드를 핸들러라고 생각하면 된다.

View Template


-------------------------------------------------------------------

요청 
    -- 요청 선처리 작업
        1. Locale 결정, 지역화, I18N
            1) 브러우저 설정에 따라서 Accept-language 등을 보내고 있음
            2) 이런 정보들을 서버로 전송한다
        2. RequestContextHandler에 요청 저장
        3. FlashMap 복원 - redirect 관련 
        4. 멀티파트 요청 (폼을 통해 파일 업로드를 할경우 - POST 방식)
            Yes)
                MultipartResolver가 멀티파트 결정
            NO)
                핸들러 결정과 실행
    -- HandlerExcutionChain 결정
        ** DispatcherServlet은 어떤 URL 요청이 올때 어떤 Handler 실행될지 알고있다.
        1. HandlerMapping으로 HandlerExcutionChain 결정됨
         - 프로젝트가 돌아가면서 해당 Bean들을 만들어 사용한다.
            org.springframework.web.servlet.function.support.RouterFunctionMapping
            org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
            org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping
            org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
            org.springframework.boot.autoconfigure.web.servlet.WelcomePageNotAcceptableHandlerMapping
            org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
    -- HandlerExcutionChain 발견
        No
            - HTTP404 전달 -> 요청이 처리됨
        Yes
            - HandlerAdapter 결정
                org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
                org.springframework.web.servlet.function.support.HandlerFunctionAdapter
                org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
                org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
                   - No
                        ServletException 발생 -> 요청이 처리됨
                   - Yes
                        요청처리
    -- HandlerExecutionChain 실행
        1. 결정된 HandlerExecutionChain
        2. 사용가능한 Interceptor 존재 하는가 (Interceptor 여러개 일수도 있다.)
            ** DispatcherServlet과 Handler 사이에 위치하여 어떤 패턴이 올때 마다 가로채어 처리한다 (preHandler/postHandler)
            Yes
                - 인터셉터의 preHandler 호출해 요청 처리 -> 계속 요청처리 
                    - 예(핸들러실행) 
                    - 아니요(요청 처리 종료)
            No
                - 핸들러 실행(함수가 실행됨)
        3. ModelAndView 리턴 
            Yes
                - ModelAndView 가 View 를 갖느냐 
                    > 예 -> 인터셉터의 PostHandler를 호출해 요청처리 -> 뷰 렌더링
                    > 아니요 -> RequestToViewNameTranslator -> 인터셉터의 PostHandler를 호출해 요청처리 -> 뷰 렌더링
            No
                - 인터셉터의 PostHandler를 호출해 요청처리 -> 뷰 렌더링
    -- 예외가 발생했을경우
        1. HandlerExecutionResolver에 문의
        2. ModelAndView 리턴
            Yes
                - 요청 처리 재개
            NO
                - 예외를 다시 던짐
    -- View 랜더링
        1. 뷰가 String을 참조
            Yes
                - ViewResolver로 View 구현체를 찾음
                    org.springframework.web.servlet.view.ContentNegotiatingViewResolver
                    org.springframework.web.servlet.view.BeanNameViewResolver
                    org.springframework.web.servlet.view.ViewResolverComposite
                    org.springframework.web.servlet.view.InternalResourceViewResolver
            NO 
                - View 구현체가 존재? (jsp, thymeleaf 등...)
                    No - ServletException을 던짐
        2. View 구현체가 존재하면 View 구현체로 렌더링
        3. 요청처리 재개
    -- 요청처리 종료
        1. HandlerExcutionChain 존재
            YES
                - 인터셉터의 afterCompletion 메소드 실행 -> RequestHandlerEvent 발생
                - ApplicationListener<RequestHandledEvent> 구현하여 따로 처리할 수 있다.
            NO
                RequestHandlerEvent 발생
        2. 요청이 처리됨



-------------------------------------------------------------------
if문과 다형성 관련 - 중요한 개념으로 공부해야된다.
-------------------------------------------------------------------
등록된 빈이 없다면 DispatcherServlet.properties 에 등록된 기본 빈을 사용한다.
special bean
    HandlerMapping 
     - RequestMappingHandlerMapping (which supports @RequestMapping)
       - 요청을 핸들러에 매핑하고, 사전 및 사후 처리를 위한 인터셉터 목록과 함께 매핑합니다.
       - SimpleUrlHandlerMapping (URI path patterns to handlers) 
    HandlerAdapter
    HandlerExceptionResolver
        - if an exception occurs, it delegate to a chain of HanderlExceptionResolver
        - SimpleMappingExceptionResolver
        - DefaultHandlerExceptionResolver
        - ResponseStatusExceptionResolver
        - ExceptionHandlerExceptionResolver
    ViewResolver
    LocaleResolver, LocaleContextResolver
        - 프로세스의 요소들이 요청을 처리할때 사용할 로케일을 결정할 수 있게 합니다.
    ThemeResolver
        - 태마 리졸버가 요청에 바인딩 되어 뷰와 같은 요소들이 사용할 테마를 결정할 수 있게합니다.
    MultipartResolver
        - 멀티파트리졸버를 지정하면 요청이 멀티파트를 검사합니다.
    FlashMapManager

    HTTP caching 또한 지원해줍니다.
    WebRequest의 checkNotModified 을 사용해서 처리
    
    HandlerInterceptor 
        - preHandler
            > true - execution continues
            > false - handler is not called.

        @ResponseBody, ResponseEntity controller 는 HandlerAdapter에서 처리되기 떄문에 
        ResponseBodyAdvice를 구현하고 Controller Advice빈으로 선언하거나, RequestMappingHandlerAdapter 설정을해라
        - postHander
        - afterCompletion
    

    
