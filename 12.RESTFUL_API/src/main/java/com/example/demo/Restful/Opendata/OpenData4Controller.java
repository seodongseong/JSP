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

//대구광역시_돌발 교통정보 조회 서비스(신)

@RestController
@Slf4j
@RequestMapping("/APARTMENT")
public class OpenData4Controller {
    private String server = "https://apis.data.go.kr/1613000/RTMSDataSvcAptTrade/getRTMSDataSvcAptTrade";
    private String serviceKey="056d4f1bf1902fc2e5893f959e99cbac8926d3803100a591f8755fbaf30c941e";
    private String LAWD_CD;
    private String DEAL_YMD;

    @GetMapping("/{LAWD_CD}/{DEAL_YMD}")
    public String get(
            @PathVariable(value = "LAWD_CD" , required = true) String LAWD_CD,
            @PathVariable(value = "DEAL_YMD", required = true) String DEAL_YMD,
            Model model
    )
    {
        log.info("GET /APARTMENT...LAWD_CD : "+LAWD_CD+" amount : "+ DEAL_YMD);
        this.LAWD_CD = LAWD_CD;
        this.DEAL_YMD = DEAL_YMD;

        // 파라미터 설정( service key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("serviceKey", serviceKey)
                .queryParam("LAWD_CD", LAWD_CD)
                .queryParam("DEAL_YMD", DEAL_YMD)
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

//        //확인
        Root root = response.getBody();
        Body body = root.getBody();
        Items items = body.getItems();
        List<Item> list = items.getItem();
        list.forEach(System.out::println);

        list.forEach(System.out::println);

        model.addAttribute("List",list);
        return "Opendata/index1";

    }

    // RESPONSE CLASS
    @Data
    private static class header {
        public int resultCode;
        public String resultMsg;
    }

    @Data
    private static class Item {
        public Object aptDong;
        public String aptNm;
        public int buildYear;
        public Object buyerGbn;
        public Object cdealDay;
        public Object cdealType;
        public double dealAmount;
        public int dealDay;
        public int dealMonth;
        public int dealYear;
        public Object dealingGbn;
        public Object estateAgentSggNm;
        public double excluUseAr;
        public int floor;
        public int jibun;
        public String landLeaseholdGbn;
        public Object rgstDate;
        public int sggCd;
        public Object slerGbn;
        public String umdNm;
    }

    @Data
    private static class Items {
        public List<item> item;
    }

    @Data
    private static class Body {
        public items items;
        public int numOfRows;
        public int pageNo;
        public int totalCount;
    }

    @Data
    private static class Root {
        public header header;
        public body body;
    }






}
