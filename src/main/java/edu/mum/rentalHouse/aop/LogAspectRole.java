package edu.mum.rentalHouse.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspectRole {

    private static Logger logger= LogManager.getLogger(LogManager.class.getName());

    @Before("execution(* edu.mum.rentalHouse.serviceImpl.RoleServiceImpl.*(..))"+" && @target(org.springframework.stereotype.Service)")
    public void LogBefore(JoinPoint joinPoint){
        logger.warn("About the Role Management using Before annotation :"+ joinPoint.getSignature().getName());
    }
    @After("execution(* edu.mum.rentalHouse.serviceImpl.RoleServiceImpl.*(..))"+" && @target(org.springframework.stereotype.Service)")
    public void LogAfter(JoinPoint joinPoint){
        logger.warn("About the Role management using After annotation : "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* edu.mum.rentalHouse.serviceImpl.RoleServiceImpl.get(..))", returning = "service")
    public void afterReturn(JoinPoint joinPoint, String service){
        System.out.println("About the Role Management AfterReturn annotation  : "+ joinPoint.getSignature().getName()+ " Returned: "+ service);
    }

    @AfterThrowing(pointcut = "execution(* edu.mum.rentalHouse.serviceImpl.RoleServiceImpl.get(..))", throwing = "excep")
    public void afterThrow(JoinPoint joinPoint, MyException excep){
        System.out.println("About the Role management AfterThrowing annotation  : "+ joinPoint.getSignature().getName()+ " threw a: "+ excep.getClass().getName());
    }
    @Around("execution(* edu.mum.rentalHouse.serviceImpl.RoleServiceImpl.get(..))")
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
