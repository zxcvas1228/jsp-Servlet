package com.nonage.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QnaVO {
    private int   qseq;
    private String subject;
    private String content;
    private String reply;
    private String id;
    private String rep;
    private Timestamp indate;
}
