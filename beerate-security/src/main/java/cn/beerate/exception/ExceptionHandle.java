package cn.beerate.exception;

import cn.beerate.common.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
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
        logger.error(String.format("系统异常：[%s],原因：[%s]",ex.getMessage(),ex.getCause()),ex);
        /* AntPathMatcher
         * 匹配大小写敏感，可用通配符为:?,*,**
         * ?表示单个字符
         * *表示一层路径内的任意字符串，不可跨层级
         * **表示任意层路径
         */

        //匹配后台请求
        if(new AntPathMatcher().match("/admin/**",request.getRequestURI())){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        }else {
            return Message.error(ex.getMessage());
        }
    }

    /**
     * 后台未登录异常(跳转登录页面)
     */
    @ExceptionHandler(AdminLoginException.class)
    public ModelAndView adminNoLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/login.html");
        return modelAndView;
    }

}