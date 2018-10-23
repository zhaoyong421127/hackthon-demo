package com.ctrip.hotel.common.exception;

/**
 * @author zhao.yong
 * @datetime 2018/10/22
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/error")
public class GlobalBusinessException extends AbstractErrorController{
    private  ErrorProperties errorProperties;


    @Autowired
    public GlobalBusinessException(ErrorAttributes errorAttributes,ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties = serverProperties.getError();
    }

    public GlobalBusinessException(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response){
        ErrorProperties.IncludeStacktrace include = errorProperties.getIncludeStacktrace();
        boolean isIncludeStack = false;
        if(include == ErrorProperties.IncludeStacktrace.ALWAYS){
            isIncludeStack = true;
        }
        Map<String, Object> errorMap = getErrorAttributes(request, isIncludeStack);

        return new ModelAndView("error",errorMap);
    }
}
