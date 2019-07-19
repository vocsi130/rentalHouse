package edu.mum.rentalHouse.aop;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogAspectUser {

    private static Logger logger=LogManager.getLogger(LogManager.class.getName());

    @Before("execution(* edu.mum.rentalHouse.serviceImpl.UserServiceImpl.*(..))"+" && @target(org.springframework.stereotype.Service)")
    public void LogBefore(JoinPoint joinPoint){
        logger.warn("About the User Management using Before annotation :"+ joinPoint.getSignature().getName());
    }
    @After("execution(* edu.mum.rentalHouse.serviceImpl.UserServiceImpl.*(..))"+" && @target(org.springframework.stereotype.Service)")
    public void LogAfter(JoinPoint joinPoint){
        logger.warn("About the user management using After annotation : "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* edu.mum.rentalHouse.serviceImpl.UserServiceImpl.findByEmail(..))", returning = "service")
    public void afterReturn(JoinPoint joinPoint, String service){
        System.out.println("About the User Management AfterReturn annotation  : "+ joinPoint.getSignature().getName()+ " Returned: "+ service);
    }

    @AfterThrowing(pointcut = "execution(* edu.mum.rentalHouse.serviceImpl.UserServiceImpl.findByEmail(..))", throwing = "excep")
    public void afterThrow(JoinPoint joinPoint, MyException excep){
        System.out.println("About the User management AfterThrowing annotation  : "+ joinPoint.getSignature().getName()+ " threw a: "+ excep.getClass().getName());
    }
    @Around("execution(* edu.mum.rentalHouse.serviceImpl.UserServiceImpl.findByEmail(..))")
    public Object around(ProceedingJoinPoint joinPoint){
        String m  = joinPoint.getSignature().getName();
        System.out.println("Inside @Around : Before "+ m);
        Object ret  =  null;
        try{

            ret = joinPoint.proceed();

        }catch (Throwable e){
            e.printStackTrace();
        }
        System.out.println("Inside @Around After "+ m+" Returned "+ ret);

        return  ret;
    }

}
