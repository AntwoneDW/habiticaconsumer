package com.twon.habiticaconsumer.api;

import com.google.gson.Gson;
import com.twon.habiticaconsumer.HabiticaconsumerApplication;
import com.twon.habiticaconsumer.entities.HabiticaTaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class TheHandler {

	@Autowired
	private HabiticaTaskResponse habitResponse;

	private static final Logger logger = LoggerFactory.getLogger(TheHandler.class);

	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromValue("Hello, Spring!"));
	}

	public Mono<ServerResponse> taskdata(ServerRequest request) {
		Gson gson = new Gson();

		logger.info("TheHandler * habitResponse getAppVersion: " +  habitResponse.getAppVersion() );
		logger.info("TheHandler * habitResponse getData: " +  habitResponse.getData().size() );
		logger.info("TheHandler * habitResponse getSuccess: " +  habitResponse.getSuccess() );
		logger.info("TheHandler * habitResponse getNotifications: " +  habitResponse.getNotifications().size() );
		String theData = gson.toJson(habitResponse.getData());

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(theData));
	}
}
