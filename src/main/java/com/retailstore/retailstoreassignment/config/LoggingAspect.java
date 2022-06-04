package com.retailstore.retailstoreassignment.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

   private final Logger log = LoggerFactory.getLogger(this.getClass());

   /**
    * Advice that logs when a method is entered and exited.
    *
    * @param joinPoint join point for advice
    * @return result
    * @throws Throwable throws IllegalArgumentException
    */
   @Around("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
   public Object logAroundRestControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
      if (log.isInfoEnabled()) {
         log.info("REST-ENTER: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
      }
      try {
         Object result = joinPoint.proceed();
         if (log.isInfoEnabled()) {
            log.info("REST-END: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
         }
         return result;
      } catch (IllegalArgumentException e) {
         log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                 joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
         throw e;
      }
   }

}