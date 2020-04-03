package com.twon.habiticaconsumer;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.twon.habiticaconsumer.entities.HabiticaTaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
public class HabiticaconsumerApplication  implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(HabiticaconsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HabiticaconsumerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = WebClient.create("https://habitica.com/api/v3/tasks/user");
        Mono<String> result = webClient.get()
                .uri("")
                .header("x-api-user", "7c649a5e-ec9a-4727-bc07-e48666a413cd")
                .header("x-api-key","5a5be917-5755-45e8-ae12-d2d9c146cdcc")
                .header("x-client","5a5be917-5755-45e8-ae12-d2d9c146cdcc")
                .retrieve().bodyToMono(String.class);

        result.subscribe(HabiticaconsumerApplication::handleResponse);
        logger.info("After subscribe");
        //System.out.println();
        //wait for a while for the response
        Thread.sleep(1000);

        //String response = result.block();
        //System.out.println(response);
    }

    private static void handleResponse(String s) {
        logger.info("handle response");
        logger.info(s);
        Gson gson = new Gson();
        Map habiticaTaskResponseMap = gson.fromJson(s, Map.class);
        habiticaTaskResponseMap.keySet().forEach(
            (key) ->
            {
                logger.info("* key: " + key+ " is a " + habiticaTaskResponseMap.get(key).getClass().getSimpleName() );
            }
        );
        ArrayList<Object> dataList = (ArrayList)habiticaTaskResponseMap.get("data");
        logger.info("* data[0]: is a " + dataList.get(0).getClass().getSimpleName() );
        dataList.forEach( dataObj -> {
            LinkedTreeMap dataObjTreeMap = (LinkedTreeMap)dataObj;
            dataObjTreeMap.keySet().forEach(
                (key) ->
                {
                    //logger.info("* dataObjTreeMap key type: " +  key.getClass().getSimpleName() );
                    //logger.info("** dataObjTreeMap key: " + key+ " is a " + dataObjTreeMap.get(key).getClass().getSimpleName() );
                }
            );
        });
        HabiticaTaskResponse habitResponse = gson.fromJson(s, HabiticaTaskResponse.class);
        logger.info("* habitResponse getAppVersion: " +  habitResponse.getAppVersion() );
        logger.info("* habitResponse getData: " +  habitResponse.getData().size() );
        logger.info("* habitResponse getSuccess: " +  habitResponse.getSuccess() );
        logger.info("* habitResponse getNotifications: " +  habitResponse.getNotifications().size() );
        habitResponse.getData().forEach( habitDataObj ->
        {
            logger.info("-----------------------------" );
            logger.info("* habitDataObj.getText(): " +  habitDataObj.getText() );
            if(!habitDataObj.getNotes().isEmpty())
            {
                logger.info("* habitDataObj.getNotes(): " +  habitDataObj.getNotes() );
            }
            logger.info("* habitDataObj.getCompleted(): " +  habitDataObj.getCompleted() );
        }
        );
    }

}
