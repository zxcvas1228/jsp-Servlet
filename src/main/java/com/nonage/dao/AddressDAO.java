package com.nonage.dao;

import com.nonage.dto.AddressVO;
import com.nonage.dto.ProductVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressDAO {
    private AddressDAO() {
    }

    private static AddressDAO instance = new AddressDAO();

    public static AddressDAO getInstance() {
        return instance;
    }



    public ArrayList<AddressVO> selectAddressByDong(String dong){
        ArrayList<AddressVO> list=new ArrayList<>();
        String sql="select * from address where dong like '%'||?||'%'";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,dong);
            rs=pstmt.executeQuery();

            while (rs.next()){
                AddressVO addressVO=new AddressVO();

                addressVO.setDong(rs.getString("dong"));
                addressVO.setSido(rs.getString("sido"));
                addressVO.setBunji(rs.getString("bunji"));
                addressVO.setGugun(rs.getString("gugun"));
                addressVO.setZipNum(rs.getString("zip_num"));
                addressVO.setZipCode(rs.getString("zip_code"));
                list.add(addressVO);

            }



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }
        return list;
    }
}
