package com.xyz.wizard.faraday.gateway.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesResultEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/29 15:14:09
 */
@Component
@Log4j2
public class DynamicGatewayRouteConfig implements ApplicationEventPublisherAware {

    private static final String DATA_ID = "gateway-router.json";

    private static final String GROUP = "DEFAULT_GROUP";

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final List<String> ROUTE_LIST = new ArrayList<>();

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        log.info("gateway route init...");
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            configService.getConfig(DATA_ID, GROUP, 5000);
            configService.addListener(DATA_ID, GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String config) {
                    log.info("获取网关当前配置:\r\n{}", config);
                    clearRoute();

                    try {
                        List<RouteDefinition> gatewayRouteDefinition = objectMapper.readValue(config, new TypeReference<List<RouteDefinition>>() {
                        });
                        gatewayRouteDefinition.forEach(this::addRoute);
                        publish();

                    } catch (JsonProcessingException e) {
                        log.error("初始化网关路由时发生错误", e);
                    }

                }

                private void addRoute(RouteDefinition routeDefinition) {
                    routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
                    ROUTE_LIST.add(routeDefinition.getId());
                }
            });

        } catch (NacosException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;

    }

    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesResultEvent(this.routeDefinitionWriter));
    }

    private void clearRoute() {
        ROUTE_LIST.forEach(id -> this.routeDefinitionWriter.delete(Mono.just(id)).subscribe());
        ROUTE_LIST.clear();
    }
}
