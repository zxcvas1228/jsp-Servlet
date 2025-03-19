package com.nonage.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderVO {
    private int odseq;
    private int oseq;
    private String id;
    private Timestamp indate;
    private String mname;
    private String zipNum;
    private String address;
    private String phone;
    private int pseq;
    private String pname;
    private int quantity;
    private int price2;
    private String result;
}
