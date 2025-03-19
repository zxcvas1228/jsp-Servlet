package com.nonage.dao;

import com.nonage.dto.ProductVO;
import com.nonage.dto.QnaVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QnaDAO {
    private QnaDAO() {
    }

    private static QnaDAO instance = new QnaDAO();

    public static QnaDAO getInstance() {
        return instance;
    }

    public ArrayList<QnaVO> listQna(String id){
        ArrayList<QnaVO> qnaList=new ArrayList<>();
        String sql="select * from qna where id=? order by qseq desc";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs=pstmt.executeQuery();

            while (rs.next()){
                QnaVO qnaVO = new QnaVO();
                qnaVO.setQseq(rs.getInt("qseq"));
                qnaVO.setSubject(rs.getString("subject"));
                qnaVO.setContent(rs.getString("content"));
                qnaVO.setId(rs.getString("id"));
                qnaVO.setIndate(rs.getTimestamp("indate"));
                qnaVO.setReply(rs.getString("reply"));
                qnaVO.setRep(rs.getString("rep"));
                qnaList.add(qnaVO);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return qnaList;
    }

    public void insertqna(QnaVO qnaVO,String session_id){
        String sql="insert into qna (qseq, subject,content,id) values(qna_seq.nextval,?,?,?)";

        Connection conn=null;
        PreparedStatement pstmt=null;


        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,qnaVO.getSubject());
            pstmt.setString(2,qnaVO.getContent());
            pstmt.setString(3,session_id);
            pstmt.executeUpdate();



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
    }

    public QnaVO getQna(int qseq){
        QnaVO qnaVO=null;
        String sql="select * from qna where qseq=?";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,qseq);
            rs=pstmt.executeQuery();

            while (rs.next()){
                qnaVO = new QnaVO();
                qnaVO.setQseq(rs.getInt("qseq"));
                qnaVO.setSubject(rs.getString("subject"));
                qnaVO.setContent(rs.getString("content"));
                qnaVO.setId(rs.getString("id"));
                qnaVO.setIndate(rs.getTimestamp("indate"));
                qnaVO.setReply(rs.getString("reply"));
                qnaVO.setRep(rs.getString("rep"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }
        return qnaVO;
    }

}
