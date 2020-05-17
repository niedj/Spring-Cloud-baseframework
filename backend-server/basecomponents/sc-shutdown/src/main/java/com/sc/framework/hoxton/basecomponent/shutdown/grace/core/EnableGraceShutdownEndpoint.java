package com.sc.framework.hoxton.basecomponent.shutdown.grace.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.config.GraceShutdownConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GraceShutdownConfig.class)
public @interface EnableGraceShutdownEndpoint {}
