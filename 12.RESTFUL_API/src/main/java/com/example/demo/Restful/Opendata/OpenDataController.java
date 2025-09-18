package com.example.demo.Restful.Opendata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.net.httpserver.HttpHandlers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//대구광역시_돌발 교통정보 조회 서비스(신)

@Controller
@Slf4j
@RequestMapping("/INCIDENT")
public class OpenDataController {
    private String server = "https://apis.data.go.kr/6270000/service/rest/dgincident";
    private String serviceKey="056d4f1bf1902fc2e5893f959e99cbac8926d3803100a591f8755fbaf30c941e";
    private String pageNo;
    private String numOfRows;

    @GetMapping("/{pageNo}/{numOfRows}")
    public String get(
            @PathVariable(value = "pageNo" , required = true) String pageNo,
            @PathVariable(value = "numOfRows", required = true) String numOfRows,
            Model model
    )
    {
        log.info("GET /INCIDENT...pageNo : "+pageNo+" amount : "+ numOfRows);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;

        // 파라미터 설정( service key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .toUriString();
         RestTemplate restTemplate = new RestTemplate();

//         요청 헤더 설정(x)
//        HttpHeaders header = new HttpHeaders();
//        header.add("key", "value");
//        // 요청 바디 설정(x)
//       MultiValueMap<String ,String> param = new LinkedMultiValueMap<>();
//        params.put("","");
//
//        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header);
        // 요청 후 응답 확인
        ResponseEntity<Root> response =
//        restTemplate.exchange("URL","요청Method","entity","반환값 받아낼 자료형")
        restTemplate.exchange(url, HttpMethod.GET,null, Root.class);

        // REST TYPE -> Class Type 변환
        System.out.println(response.getBody());

        //확인
        Root root = response.getBody();
        Body body = root.getBody();
        Items items = body.getItems();
        List<Item> list = items.getItem();
        list.forEach(System.out::println);

        model.addAttribute("List",list);
        return "Opendata/index1";

    }

    // RESPONSE CLASS
    @Data
    private static class Body{
        public Items items;
        public String numOfRows;
        public String pageNo;
        public String totalCount;
    }
    @Data
    private static class Header{
        public String resultCode;
        public String resultMsg;
    }
    @Data
    private static class Item{
        @JsonProperty("LOCATION")
        public String lOCATION;
        @JsonProperty("INCIDENTTITLE")
        public String iNCIDENTTITLE;
        @JsonProperty("LOGDATE")
        public String lOGDATE;
        @JsonProperty("TROUBLEGRADE")
        public String tROUBLEGRADE;
        @JsonProperty("STARTDATE")
        public String sTARTDATE;
        @JsonProperty("INCIDENTSUBCODE")
        public String iNCIDENTSUBCODE;
        @JsonProperty("LINKID")
        public String lINKID;
        @JsonProperty("REPORTDATE")
        public String rEPORTDATE;
        @JsonProperty("ENDDATE")
        public String eNDDATE;
        @JsonProperty("COORDX")
        public double cOORDX;
        @JsonProperty("INCIDENTCODE")
        public String iNCIDENTCODE;
        @JsonProperty("INCIDENTID")
        public String iNCIDENTID;
        @JsonProperty("COORDY")
        public double cOORDY;
        @JsonProperty("TRAFFICGRADE")
        public String tRAFFICGRADE;
    }
    @Data
    private static class Items{
        public ArrayList<Item> item;
    }
    @Data
    private static class Root{
        public Body body;
        public Header header;
    }




}
