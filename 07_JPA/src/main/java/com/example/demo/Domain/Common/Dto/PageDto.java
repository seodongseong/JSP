package com.example.demo.Domain.Common.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
//    int말고 Integer 쓰는게 추천
    private Integer pageNo;
    private Integer amount;
    private String keyword;
    private String type;
}
