package com.twon.habiticaconsumer;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.twon.habiticaconsumer.entities.HabiticaTaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HabiticaAccess habiticaAccess;
    @Autowired
    private HabiticaTaskResponse habitResponse;

    private static final Logger logger = LoggerFactory.getLogger(HabiticaconsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HabiticaconsumerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("habiticaAccess: " + habiticaAccess.userkey + " / " + habiticaAccess.secretkey );
        WebClient webClient = WebClient.create("https://habitica.com/api/v3/tasks/user");
        Mono<String> result = webClient.get()
                .uri("")
                .header("x-api-user", habiticaAccess.userkey)
                .header("x-api-key",habiticaAccess.secretkey)
                .header("x-client",habiticaAccess.secretkey)
                .retrieve().bodyToMono(String.class);
        result.subscribe(this::handleResponse);
        logger.info("After subscribe");
        //System.out.println();
        //wait for a while for the response
        Thread.sleep(1000);

        //String response = result.block();
        //System.out.println(response);
    }

    private void handleResponse(String s) {
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
        logger.info("* dataList.toString()" + dataList.toString() );
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
        habitResponse = gson.fromJson(s, HabiticaTaskResponse.class);
        logger.info("* habitResponse getAppVersion: " +  habitResponse.getAppVersion() );
        logger.info("* habitResponse getData: " +  habitResponse.getData().size() );
        logger.info("* habitResponse getSuccess: " +  habitResponse.getSuccess() );
        logger.info("* habitResponse getNotifications: " +  habitResponse.getNotifications().size() );
        habitResponse.getData().forEach( habitDataObj ->
        {
            logger.info("-----------------------------" );
            logger.info("* habitDataObj.getText(): " +  habitDataObj.getText() );
            logger.info("* habitDataObj.get_id(): " + habitDataObj.get_id() + " = " + habitDataObj.getId() );
            logger.info("* habitDataObj() History Size: " +  habitDataObj.getHistory().size() );
            if(!habitDataObj.getNotes().isEmpty())
            {
                logger.info("* habitDataObj.getNotes(): " +  habitDataObj.getNotes() );
            }
            logger.info("* habitDataObj.getCompleted(): " +  habitDataObj.getCompleted() );
        }
        );
    }
    // ./mvnw spring-boot:run
    // ./mvnw compile
}
