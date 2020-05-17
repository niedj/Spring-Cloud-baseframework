package com.sc.eurekaserver;

/**
 * security认证导致的Cannot execute request on any known server:
 *
 * 默认情况下，当Spring Security处于类路径时，它将要求向应用程序发送每个请求的有效CSRF令牌。
 * EURKA客户端一般不会拥有有效的跨站点请求伪造（CSRF）令牌，
 * 您将需要禁用对/EURKA/**端点的此要求
 *//*
    * @Override
    * protected void configure(HttpSecurity http) throws Exception {
    * http.csrf().ignoringAntMatchers("/**");
    * super.configure(http);
    * }
    * }
    */
