package com.nonage.controller.action;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class OrderInsertAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="NonageServlet?command=order_list";

        HttpSession session=req.getSession();
        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");


        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            CartDAO cartDAO=CartDAO.getInstance();
            ArrayList<CartVO> cartList= cartDAO.listcart(loginUser.getId());

            OrderDAO orderDAO=OrderDAO.getInstance();
            int maxOseq=orderDAO.insertOrder(cartList,loginUser.getId());
            url="NonageServlet?command=order_list&oseq="+maxOseq;//
        }

        resp.sendRedirect(url);
    }
}
