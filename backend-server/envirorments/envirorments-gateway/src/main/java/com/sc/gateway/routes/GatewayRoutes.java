/**
 * Project Name:envirorments-gateway
 * File Name:GateRoutes.java
 * Package Name:com.sc.gateway.routes
 * Date:2020-05-19 15:46:46
 * Copyright (c) 2020, CCS DIG All Rights Reserved.
 *
 */

package com.sc.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/java/**").filters(f -> f.stripPrefix(1)).uri("https://blog.csdn.net/")).build();
    }

}