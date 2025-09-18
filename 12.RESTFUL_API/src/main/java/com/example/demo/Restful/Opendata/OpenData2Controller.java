package com.example.demo.Restful.Opendata;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//대구광역시_돌발 교통정보 조회 서비스(신)

@RestController
@Slf4j
@RequestMapping("/WEATHER")
public class OpenData2Controller {
    private String server ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    private String serviceKey="xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";
    private String pageNo;
    private String numOfRows;
    private String dataType;
    private String base_date;
    private String base_time;
    private String nx;
    private String ny;
    @GetMapping(value = "/{pageNo}/{numOfRows}/{dataType}/{base_date}/{base_time}/{nx}/{ny}" )
    public void get(
            @PathVariable(value = "pageNo",required = true) String pageNo,
            @PathVariable(value = "numOfRows",required = true) String numOfRows,
            @PathVariable(value = "dataType",required = true) String dataType,
            @PathVariable(value = "base_date",required = true) String base_date,
            @PathVariable(value = "base_time",required = true) String base_time,
            @PathVariable(value = "nx",required = true) String nx,
            @PathVariable(value = "ny",required = true) String ny,
            Model model
    )
    {
        log.info("GET /WEATHER....pageNo:{} numOfRows:{} dataType:{} base_date:{} base_time:{} nx={} ny={}"
                ,pageNo,numOfRows,dataType,base_date,base_time,nx,ny);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.dataType = dataType;
        this.base_date = base_date;
        this.base_time = base_time;
        this.nx = nx;
        this.ny = ny;

        // 파라미터 설정( service Key 포함)
//        String url = UriComponentsBuilder.fromHttpUrl(server)
//                .queryParam("serviceKey",serviceKey)
//                .queryParam("pageNo",pageNo)
//                .queryParam("numOfRows",numOfRows)
//                .toUriString();
//        System.out.println(url);

        String url = server;
        url+="?serviceKey=" + serviceKey;
        url+="&pageNo=" + pageNo;
        url+="&numOfRows="+numOfRows;
        url+="&dataType="+dataType;
        url+="&base_date="+base_date;
        url+="&base_time="+base_time;
        url+="&nx="+nx;
        url+="&ny="+ny;
//
//
        RestTemplate restTemplate = new RestTemplate();
//
////        // 요청 헤더 설정(x)
////        HttpHeaders header = new HttpHeaders();
////        header.add("key","value");
////        // 요청 바디 설정(x)
////        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
////        params.add("","");
////
////        HttpEntity< MultiValueMap<String,String>  > entity = new HttpEntity<>(params,header);
//
//        // 요청 후 응답 확인
        ResponseEntity<Root> response =
                restTemplate.exchange(url, HttpMethod.GET,null,Root.class);
//
        //REST TYPE -> Class Type 변환
        System.out.println(response.getBody());

        //확인
        Root root = response.getBody();
        Response resp = root.getResponse();
        Body body = resp.getBody();
        Items items = body.getItems();
        List<Item> list = items.getItem();
        list.forEach(System.out::println);

//
//        model.addAttribute("list",list);
//        return "Opendata/index1";
    }


    // RESPONSE CLASS
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    @Data
    private static class Body{
        public String dataType;
        public Items items;
        public int pageNo;
        public int numOfRows;
        public int totalCount;
    }
    @Data
    private static class Header{
        public String resultCode;
        public String resultMsg;
    }
    @Data
    private static class Item{
        public String baseDate;
        public String baseTime;
        public String category;
        public int nx;
        public int ny;
        public String obsrValue;
    }
    @Data
    private static class Items{
        public ArrayList<Item> item;
    }
    @Data
    private static class Response{
        public Header header;
        public Body body;
    }
    @Data
    private static class Root{
        public Response response;
    }




}