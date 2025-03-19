package com.nonage.dao;

import com.nonage.dto.ProductVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
    private ProductDAO() {
    }

    private static ProductDAO instance = new ProductDAO();

    public static ProductDAO getInstance() {
        return instance;
    }

    //신상품
    public ArrayList<ProductVO> listNewProduct(){
        ArrayList<ProductVO> productList=new ArrayList<>();
        String sql="select * from new_pro_view";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while (rs.next()){
                ProductVO productVO=new ProductVO();
                productVO.setPseq(rs.getInt("pseq"));
                productVO.setName(rs.getString("name"));
                productVO.setPrice2(rs.getInt("price2"));
                productVO.setImage(rs.getString("image"));
                productList.add(productVO);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return productList;
    }
    //베스트상품
    public ArrayList<ProductVO> listBestProduct(){
        ArrayList<ProductVO> productList=new ArrayList<>();

        String sql="select * from best_pro_view";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while (rs.next()){
                ProductVO productVO=new ProductVO();
                productVO.setPseq(rs.getInt("pseq"));
                productVO.setName(rs.getString("name"));
                productVO.setPrice2(rs.getInt("price2"));
                productVO.setImage(rs.getString("image"));
                productList.add(productVO);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return productList;
    }

    //제품상세정보
    public ProductVO getProduct(String pseq){
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
                productVO.setUseyn(rs.getString("useyn"));
                productVO.setBestyn(rs.getString("bestyn"));
                productVO.setIndate(rs.getTimestamp("indate"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return productVO;
    }

    public ArrayList<ProductVO> listKindProduct(String kind){
        ArrayList<ProductVO> productList = new ArrayList<>();

        String sql="select * from product where kind=?";

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,kind);
            rs=pstmt.executeQuery();

            while (rs.next()){
                ProductVO productVO=new ProductVO();
                productVO.setPseq(rs.getInt("pseq"));
                productVO.setName(rs.getString("name"));
                productVO.setPrice2(rs.getInt("price2"));
                productVO.setImage(rs.getString("image"));
                productList.add(productVO);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }

        return productList;
    }
}
