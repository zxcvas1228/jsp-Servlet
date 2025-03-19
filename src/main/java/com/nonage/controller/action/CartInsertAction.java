package com.nonage.controller.action;

import com.nonage.dao.CartDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartInsertAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="NonageServlet?command=cart_list";
        HttpSession session= req.getSession();

        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
        String quantity=req.getParameter("quantity");
        String pseq=req.getParameter("pseq");

        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            CartVO cartVO=new CartVO();
            cartVO.setId(loginUser.getId());
            cartVO.setPseq(Integer.parseInt(pseq));
            cartVO.setQuantity(Integer.parseInt(quantity));

            //DB 연결처리
            CartDAO cartDAO=CartDAO.getInstance();
            cartDAO.insertCart(cartVO);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
