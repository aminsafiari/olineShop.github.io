package amin.shop.app.helper.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtils<T> {

    final Class<T> type;

    //inject Class<T> with constructor and final type = signature type.
    //ex : in responseEntity -> getType(), To detect the object type in the HttpUtils class, in fact, what class type is the output of this class HttpUtils;
    public HttpUtils(Class<T> type) {
        this.type = type;
    }

    public T callPost(String address, Object data) {
        Gson gson = getGson();
        //RestTemplate -> help to call rest api's.
        RestTemplate restTemplate = new RestTemplate();
        //HttpHeader: includes body and header in this protocol.
        HttpHeaders headers = new HttpHeaders();
        //set header.
        headers.setContentType(MediaType.APPLICATION_JSON);
        //set body; conversion object to gson, serialize data to gson.
        String bodyData = gson.toJson(data);
        //HttpEntity: Its job is to connect the body and header and create an Http packet for us
        HttpEntity<String> httpEntity = new HttpEntity<>(bodyData, headers);
        //getType(): mean get return type.
        ResponseEntity<String> responseEntity = restTemplate.exchange(address, HttpMethod.POST, httpEntity, String.class);
        String responseBody = responseEntity.getBody();
        T responseData = gson.fromJson(responseBody, getType());
        return responseData;
    }

    public ResponseEntity<T> callGet(String address) {
        //RestTemplate -> help to call rest api's.
        RestTemplate restTemplate = new RestTemplate();
        //getType(): mean get return type.
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(address, getType());
        return responseEntity;
    }

    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson;
    }

    public Class<T>  getType() {
        return type;
    }

}
