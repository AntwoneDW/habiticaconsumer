package com.twon.habiticaconsumer.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class TheRouter {

    @Bean
    public RouterFunction<ServerResponse> route(TheHandler theHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), theHandler::hello)
                .andRoute(RequestPredicates.GET("/data").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), theHandler::taskdata);
    }

    /*

public class MyRouter {
    // works for a single bean
    @Bean
    public RouterFunction<ServerResponse> routes(PersonHandler personHandler) {
        return RouterFunctions.route(GET("/people/{id}").and(accept(APPLICATION_JSON)), personHandler::get)
            .andRoute(GET("/people").and(accept(APPLICATION_JSON)), personHandler::all)
            .andRoute(POST("/people").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::post)
            .andRoute(PUT("/people/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::put)
            .andRoute(DELETE("/people/{id}"), personHandler::delete)
            .andRoute(GET("/people/country/{country}").and(accept(APPLICATION_JSON)), personHandler::getByCountry);
    }
}
     */
}
