package com.nonage.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CartVO {
    private int cseq;
    private String id;
    private int pseq;
    private String mname;
    private String pname;
    private int quantity;
    private int price2;
    private Timestamp indate;
}
