package com.nonage.dao;

import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDAO {
    private OrderDAO() {
    }

    private static OrderDAO instance = new OrderDAO();

    public static OrderDAO getInstance() {
        return instance;
    }

    public int insertOrder(ArrayList<CartVO> cartList, String id) {
        int maxOseq = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            conn = DBManager.getConnection();

            String insertOrder = "insert into orders(oseq, id) values("
                    + "orders_seq.nextval, ?)";
            pstmt = conn.prepareStatement(insertOrder);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

            pstmt.close();

            String selectMaxOseq = "select max(oseq) from orders";
            pstmt = conn.prepareStatement(selectMaxOseq);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxOseq = rs.getInt(1);
            }

            for (CartVO cartVO : cartList) {
                insertOrderDetail(cartVO, maxOseq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return maxOseq;
    }

    public void insertOrderDetail(CartVO cartVO, int maxOseq) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();

            String insertOrderDetail = "insert into order_detail(odseq, oseq, "
                    + "pseq, quantity) values(order_detail_seq.nextval, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertOrderDetail);
            pstmt.setInt(1, maxOseq);
            pstmt.setInt(2, cartVO.getPseq());
            pstmt.setInt(3, cartVO.getQuantity());
            pstmt.executeUpdate();
            pstmt.close();

            String updateCartResult = "update cart set result=2 where cseq=?";
            pstmt = conn.prepareStatement(updateCartResult);
            pstmt.setInt(1, cartVO.getCseq());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }


    public ArrayList<OrderVO> listOrderById(String id,String result,int oseq){
        ArrayList<OrderVO> orderList=new ArrayList<>();

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= DBManager.getConnection();

            String sql="select * from order_view where id=? and result like '%'||?||'%' and oseq=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,result);
            pstmt.setInt(3,oseq);

            rs= pstmt.executeQuery();

            while (rs.next()) {
                OrderVO orderVO = new OrderVO();
                orderVO.setOdseq(rs.getInt("ODSEQ"));
                orderVO.setOseq(rs.getInt("OSEQ"));
                orderVO.setId(rs.getString("ID"));
                orderVO.setIndate(rs.getTimestamp("INDATE"));
                orderVO.setMname(rs.getString("MNAME"));
                orderVO.setZipNum(rs.getString("ZIP_NUM"));
                orderVO.setAddress(rs.getString("ADDRESS"));
                orderVO.setPhone(rs.getString("PHONE"));
                orderVO.setPseq(rs.getInt("PSEQ"));
                orderVO.setQuantity(rs.getInt("QUANTITY"));
                orderVO.setPname(rs.getString("PNAME"));
                orderVO.setPrice2(rs.getInt("PRICE2"));
                orderVO.setResult(rs.getString("RESULT"));
                orderList.add(orderVO);
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }


        return orderList;
    }

}
