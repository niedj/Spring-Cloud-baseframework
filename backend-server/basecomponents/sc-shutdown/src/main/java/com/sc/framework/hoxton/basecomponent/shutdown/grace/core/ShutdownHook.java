package com.sc.framework.hoxton.basecomponent.shutdown.grace.core;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.actuate.health.HealthIndicator;

public interface ShutdownHook extends HealthIndicator {

	/**
	 * 开始优雅关闭
	 * @return
	 */
	void startGraceShutdown();

	/**
	 * 是否开始关闭
	 * @return
	 */
	boolean isStartGraceShutdown();

	/**
	 * 暂停请求处理
	 * @throws InterruptedException
	 */
	void pauseRequest() throws InterruptedException;

	/**
	 * 停止容器
	 * @param delayTime 强制关闭时间
	 * @param delayTimeUnit 强制关闭时间单位
	 * @throws InterruptedException
	 */
	void shutdown(long delayTime, TimeUnit delayTimeUnit) throws InterruptedException;
}
