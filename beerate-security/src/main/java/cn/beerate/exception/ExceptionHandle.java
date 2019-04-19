package cn.beerate.exception;

import cn.beerate.common.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RestController
public class ExceptionHandle {

    private static final Log logger = LogFactory.getLog(ExceptionHandle.class);

    /**
     * 异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Message handleException(Exception ex) {
        logger.error("系统异常",ex);
        return Message.error(ex.getMessage());
    }

    /**
     * 后台未登录异常(跳转登录页面)
     */
    @ExceptionHandler(AdminNoLoginException.class)
    public ModelAndView adminNoLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/login.html");
        return modelAndView;
    }

}