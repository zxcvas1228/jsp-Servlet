package com.nonage.dao;

import com.nonage.dto.MemberVO;
import com.nonage.dto.ProductVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    private MemberDAO() {
    }

    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance() {
        return instance;
    }


    public int confirmID(String id){
        int result=-1;

        String sql="select * from member where id=?";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs=pstmt.executeQuery();

            if(rs.next()){
                result=1;
            }else {
                result = -1;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }


        return result;
    }


    public int insertMember(MemberVO memberVO){
        int result=0;

        String sql="insert into member (id,pwd,name,email,zip_num,address,phone) values(?,?,?,?,?,?,?)";

        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,memberVO.getId());
            pstmt.setString(2,memberVO.getPwd());
            pstmt.setString(3,memberVO.getName());
            pstmt.setString(4,memberVO.getEmail());
            pstmt.setString(5,memberVO.getZipNum());
            pstmt.setString(6,memberVO.getAddress());
            pstmt.setString(7,memberVO.getPhone());
            result=pstmt.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }

        return result;
    }

    public static MemberVO getMember(String id,String pwd){
        MemberVO memberVO=new MemberVO();

        String sql="select * from member where id=? and pwd=?";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);
            rs=pstmt.executeQuery();

           if(rs.next()){
               memberVO=new MemberVO();
               memberVO.setId(rs.getString("id"));
               memberVO.setPwd(rs.getString("pwd"));
               memberVO.setName(rs.getString("name"));
               memberVO.setEmail(rs.getString("email"));
               memberVO.setZipNum(rs.getString("zip_num"));
               memberVO.setAddress(rs.getString("address"));
               memberVO.setPhone(rs.getString("phone"));
               memberVO.setUseyn(rs.getString("useyn"));
               memberVO.setIndate(rs.getTimestamp("indate"));
           }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return memberVO;
    }

}
