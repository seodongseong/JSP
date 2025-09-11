package com.example.demo.Domain.Common.Dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor  //디폴트
@AllArgsConstructor //모든 인자
@Data               // Getter and Setter , toString +a

public class MemoDto {
    @Min(value = 10 , message="ID는 10이상의 값부터 시작합니다.")
    @Max(value = 65535 , message="ID의 최대 숫자는 65535입니다.")
    @NotNull(message="ID는 필수 항목입니다.")
    private long id;
    @NotBlank(message = "TEXT는 필수 항목입니다.")
    private String text;
    @NotBlank(message = "작성자를 입력하세요")
    @Email(message = "example@examle.com 형식으로 입력하세요")
    private String writer;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull(message="날짜 정보를 선택하세요")
    @Future(message="오늘 날짜 기준 이후의 날짜를 입력하세요")
    private LocalDateTime createAt; //시간 받기

    private LocalDate data_test;
}
