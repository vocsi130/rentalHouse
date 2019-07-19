package edu.mum.rentalHouse.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("edu.mum.rentalHouse.serviceImpl")
@EnableAspectJAutoProxy
public class Config {
}
