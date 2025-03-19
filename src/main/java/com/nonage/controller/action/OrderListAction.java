package com.nonage.controller.action;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class OrderListAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="mypage/orderList.jsp";
        HttpSession session=req.getSession();

        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");

        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            OrderDAO orderDAO=OrderDAO.getInstance();
            int oseq = Integer.parseInt(req.getParameter("oseq"));
            ArrayList<OrderVO> orderList= orderDAO.listOrderById(loginUser.getId(),"1",oseq);

            int totalPrice=0;
            for(OrderVO orderVO : orderList){
                totalPrice+=orderVO.getPrice2()*orderVO.getQuantity();
            }

            req.setAttribute("orderList",orderList);
            req.setAttribute("totalPrice",totalPrice);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
