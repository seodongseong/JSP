package com.example.demo.Restful.Opendata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

// 대구광역시_돌발 교통정보 조회 서비스(신)
@RestController
@Slf4j
@RequestMapping("/BUS")
public class OpenData3Controller {
    private String server = "https://apis.data.go.kr/6270000/dbmsapi02/getRealtime02";
    private String serviceKey = "c1ba8d17daa813982e5364c2b460f4d36b880a3a4f512443aee17b20766a3049";
    private String bsId;
    private String routeNo;

    @GetMapping(value = "/{bsId}/{routeNo}")
    public void get(
            @PathVariable(value = "bsId", required = true) String bsId,
            @PathVariable(value = "routeNo", required = true) String routeNo,
            Model model
    )
    {
        log.info("Get /BUS...pageNo : " +bsId+ " amount : " + routeNo);
        this.bsId = bsId;
        this.routeNo = routeNo;

        // 파라미터 설정(service Key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("serviceKey",serviceKey)
                .queryParam("bsId",bsId)
                .queryParam("routeNo", routeNo)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        // 요청 헤더 설정(x)
//        HttpHeaders header = new HttpHeaders();
//        header.add("key","value");
//        // 요청 바디 설정(x)
//        Map<String, String> params = new LinkedMultiValueMap<>();
//        params.add("","");
//
//        HttpEntity<Map<String,String>> entity = new HttpEntity<>(params, header);

        // 요청 후 응답 확인
//        restTemplate.exchange("URL","요청METHOD","entity","반환값 받아낼 자료형");
        ResponseEntity<Root> response =
                restTemplate.exchange(url, HttpMethod.GET, null, Root.class);

        // REST TYPE -> Class Type 변환
        System.out.println(response.getBody());

        // 확인
        Root root = response.getBody();
        Body body = root.getBody();
        ArrayList<Item> items =body.getItems();
        items.forEach((item)->{
            System.out.println(item);
        });

    }
    // RESPONSE CLASS
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    @Data
    public static class ArrList{
        public String routeId;
        public String routeNo;
        public String moveDir;
        public int bsGap;
        public String bsNm;
        public String vhcNo2;
        public String busTCd2;
        public String busTCd3;
        public String busAreaCd;
        public String arrState;
        public int prevBsGap;
        public int arrTime;
    }
    @Data
    public static class Body{
        public ArrayList<Item> items;
        public int totalCount;
    }
    @Data
    public static class Header{
        public boolean success;
        public String resultCode;
        public String resultMsg;
    }
    @Data
    public static class Item{
        public String routeNo;
        public ArrayList<ArrList> arrList;
    }
    @Data
    public static class Root{
        public Header header;
        public Body body;
    }




}
