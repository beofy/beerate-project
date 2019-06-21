package cn.beerate.exception;

import cn.beerate.common.Message;
import cn.beerate.common.StatusCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandle {

    private static final Log logger = LogFactory.getLog(ExceptionHandle.class);

    private HttpServletRequest request;

    public ExceptionHandle(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handleException(Exception ex) {
        logger.error(String.format("系统异常：[%s],原因：[%s]", ex.getMessage(), ex.getCause()), ex);
        //匹配后台请求
        if (request.getRequestURI().contains(".html")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("ex", ex);
            modelAndView.setViewName("500");
            return modelAndView;
        } else {
            return new HttpEntity<>(Message.error(ex.getMessage()));
        }
    }

    /**
     * 后台未登录异常(跳转登录页面)
     */
    @ExceptionHandler(AdminLoginException.class)
    public ModelAndView adminNoLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/login.html");
        return modelAndView;
    }

    /**
     * 用户未登录异常(返回数据)
     */
    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public Message<String> userNoLogin(UserLoginException e) {
        return new Message<>(StatusCode.NOT_LOGIN, e.getMessage());
    }

    /**
     * 用户未认证异常
     */
    @ExceptionHandler(NoApproveException.class)
    @ResponseBody
    public Message<String> userNoApprove(NoApproveException e) {
        return new Message<>(StatusCode.NOT_APPROVE, e.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public Message<String> tokenException(TokenException e) {
        return new Message<>(StatusCode.TOKEN_EXCEPTION, e.getMessage());
    }




}