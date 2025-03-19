package com.nonage.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberVO {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String zipNum;
    private String address;
    private String phone;
    private String useyn;
    private Timestamp indate;
}
