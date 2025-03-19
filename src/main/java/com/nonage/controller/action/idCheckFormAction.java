package com.nonage.controller.action;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.ProductDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class idCheckFormAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="member/idcheck.jsp";

        //파라메터정보 받기
        String id=req.getParameter("id");


        //DAO 요청
        MemberDAO memberDAO = MemberDAO.getInstance();
        int message=memberDAO.confirmID(id);

        req.setAttribute("message",message);
        req.setAttribute("id",id);


        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
