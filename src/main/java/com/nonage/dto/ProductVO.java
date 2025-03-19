package com.nonage.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ProductVO {
    private int pseq;
    private String name;
    private String kind;
    private int price1;
    private int price2;
    private int price3;
    private String content;
    private String image;
    private String useyn;
    private String bestyn;
    private Timestamp indate;
}
