package cn.beerate.aspect;

import cn.beerate.constant.SessionKey;
import cn.beerate.model.bean.User;
import cn.beerate.model.entity.t_admin;
import cn.beerate.service.PlatformLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Aspect
@Component
public class LogAspect {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PlatformLogService platformLogService;

    @Around("execution(* cn.beerate.controller.*.*(..)) " +
            "&& !execution(* cn.beerate.controller.BaseController.*(..)) " +
            "&& !execution(* cn.beerate.controller.AdminBaseController.*(..)) " +
            "&& !execution(* cn.beerate.controller.UserBaseController.*(..)) " +
            "&& !execution(* cn.beerate.controller.SettingController.*(..)) ")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //操作者
        HttpSession session = request.getSession();
        t_admin admin =(t_admin) session.getAttribute(SessionKey.ADMIN_SESSION_KEY);
        User user = (User)session.getAttribute(SessionKey.USER_SESSION_KEY);
        String operator = "未登录";
        String  operatorType = "unkown";
        if (admin!=null&&user!=null){
            operator = String.format("异常提醒:管理员和用户在会话状态下登录,管理员：[%s],用户：[%s]",admin.getUsername(),user.getName());
            operatorType="error";
        }else if (admin!=null){
            operator = admin.getUsername();
            operatorType="admin";
        }else if (user!=null){
            operator = user.getName();
            operatorType="user";
        }

        //获取请求地址
        String action = request.getRequestURI();

        //获取控制器参数
        Map<String, String[]> params = request.getParameterMap();

        //获取返回结果
        long start = System.currentTimeMillis();
        Object returnValue = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        //执行时间
        long executionTime = end - start;

        logger.info(String.format("操作者：%s,请求地址：%s,方法:%s,请求参数：%s,返回结果：%s,执行时间：%s(ms)",
                operator,
                action,
                proceedingJoinPoint.getTarget(),
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(returnValue),
                executionTime));

        //保存系统日志
        try {
            platformLogService.addPlatformLog(
                    operator,
                    action,
                    objectMapper.writeValueAsString(params),
                    objectMapper.writeValueAsString(returnValue),
                    String.valueOf(executionTime),
                    request.getRemoteHost(),
                    operatorType
            );
        }catch (Exception e){
            logger.info("添加系统日志异常,原因：["+e.getMessage()+"]");
            logger.debug(e);
        }

        return returnValue;
    }
}
