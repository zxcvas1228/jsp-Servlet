package com.nonage.admin.dao;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.QnaVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AQnaDAO {

    private AQnaDAO() {
    }

    private static AQnaDAO instance = new AQnaDAO();

    public static AQnaDAO getInstance() {
        return instance;
    }


    public ArrayList<QnaVO> listQna() {
        ArrayList<QnaVO> qnaList = new ArrayList<>();

        String insertOrser = "select * from qna  ORDER BY rep ASC, qseq DESC";


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(insertOrser);
            rs = pstmt.executeQuery();


            while (rs.next()) {
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


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt,rs);
        }

        return qnaList;
    }

    public QnaVO getQna(int qseq) {

        QnaVO qnaVO = null;

        String sql = "select * from qna where qseq = ? ";


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,qseq);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                qnaVO= new QnaVO();
                qnaVO.setQseq(rs.getInt("qseq"));
                qnaVO.setSubject(rs.getString("subject"));
                qnaVO.setContent(rs.getString("content"));
                qnaVO.setId(rs.getString("id"));
                qnaVO.setIndate(rs.getTimestamp("indate"));
                qnaVO.setReply(rs.getString("reply"));
                qnaVO.setRep(rs.getString("rep"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt,rs);
        }

        return qnaVO;

    }

    public void insertReply(QnaVO qnaVO,int qseq) {

        String sql = "UPDATE qna set reply=? ,rep=2  where qseq = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,qnaVO.getReply());
            pstmt.setInt(2,qseq);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

    }



}
