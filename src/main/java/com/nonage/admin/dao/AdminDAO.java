package com.nonage.admin.dao;

import com.nonage.admin.dto.AdminVO;
import com.nonage.dto.ProductVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private AdminDAO() {
    }

    private static AdminDAO instance = new AdminDAO();

    public static AdminDAO getInstance() {
        return instance;
    }


    public static AdminVO getAdmin(String id, String pwd){
        AdminVO adminVO=new AdminVO();

        String sql="select * from worker where id=? and pwd=?";

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
                adminVO=new AdminVO();
                adminVO.setId(rs.getString("id"));
                adminVO.setPwd(rs.getString("pwd"));
                adminVO.setName(rs.getString("name"));
                adminVO.setPhone(rs.getString("phone"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return adminVO;
    }



    public int getListCount(){
        int count=0;

        String sql="select (*) from Product order by pseq desc";
        AdminVO adminVo=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            if(rs.next()){
                count=rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }
        return count;
    }


    public List<ProductVO> getProductList(int page, int limit){
        List<ProductVO> list=new ArrayList<ProductVO>();
        String sql="select *" +
                " from (" +
                "            select rownum rm,b1.* " +
                "            from ( " +
                "                       select * " +
                "                       from product " +
                "                       order by pseq desc " +
                "                     ) b1 " +
                "        )b2 " +
                " where rm >= ? and rm <= ? ";
        int startrow=(page-1)*limit+1;
        int endrow=startrow+limit-1;

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,startrow);
            pstmt.setInt(2,endrow);
            rs=pstmt.executeQuery();

            while(rs.next()){
                ProductVO pVo=new ProductVO();
                pVo.setPseq(rs.getInt("pseq"));
                pVo.setName(rs.getString("name"));
                pVo.setPrice1(rs.getInt("price1"));
                pVo.setPrice2(rs.getInt("price2"));
                pVo.setIndate(rs.getTimestamp("indate"));
                pVo.setUseyn(rs.getString("useyn"));

                list.add(pVo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return list;
    }


    ////////////////////////////////////////

    public ProductVO getAdminProduct(String pseq){
        ProductVO productVO=null;

        String sql="select * from product where pseq = ?";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pseq);

            rs=pstmt.executeQuery();

            while (rs.next()){
                productVO=new ProductVO();
                productVO.setPseq(rs.getInt("pseq"));
                productVO.setName(rs.getString("name"));
                productVO.setKind(rs.getString("kind"));
                productVO.setPrice1(rs.getInt("price1"));
                productVO.setPrice2(rs.getInt("price2"));
                productVO.setPrice3(rs.getInt("price3"));
                productVO.setContent(rs.getString("content"));
                productVO.setImage(rs.getString("image"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return productVO;
    }

public String kind_check(String kind){
        String kindCheck = null;
        switch (kind){
            case "1" : kindCheck="Heels";
                break;
            case "2" : kindCheck="Boots";
                break;
            case "3" : kindCheck="Sandls";
                break;
            case "4" : kindCheck="Sneakers";
                break;
            case "5" : kindCheck="Sneakers";
                break;
            case "6" : kindCheck="Sneakers";
                break;
        }
        return kindCheck;
}



    public void updateProduct(ProductVO pVo){
        String sql=" update product set kind=?,name=?,price1=?,price2=?,price3=?,useyn=?,bestyn=?,content=?,image=? where pseq=? ";
        Connection conn=null;
        PreparedStatement pstmt=null;


        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,pVo.getKind());
            pstmt.setString(2,pVo.getName());
            pstmt.setInt(3,pVo.getPrice1());
            pstmt.setInt(4,pVo.getPrice2());
            pstmt.setInt(5,pVo.getPrice3());
            pstmt.setString(6,pVo.getUseyn());
            pstmt.setString(7,pVo.getBestyn());
            pstmt.setString(8,pVo.getContent());
            pstmt.setString(9,pVo.getImage());
            pstmt.setInt(10,pVo.getPseq());

            pstmt.executeUpdate();



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
    }

    public String use_check(String use){
        String kindCheck = null;
        switch (use){
            case "y" : kindCheck="사용";
                break;
            case "n" : kindCheck="사용중이지 않음";
                break;
        }
        return kindCheck;
    }









}
