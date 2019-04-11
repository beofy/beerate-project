package cn.beerate.exception;

import cn.beerate.common.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


}