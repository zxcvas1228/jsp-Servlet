package com.nonage.controller.action;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.ProductDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class JoinAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="member/login.jsp";

        HttpSession session= req.getSession();

        MemberVO memberVO=new MemberVO();
        //파라매터정보 받기
        memberVO.setId(req.getParameter("id"));
        memberVO.setPwd(req.getParameter("pwd"));
        memberVO.setName(req.getParameter("name"));
        memberVO.setEmail(req.getParameter("email"));
        memberVO.setZipNum(req.getParameter("zipnum"));
        memberVO.setAddress(req.getParameter("addr1")+req.getParameter("addr2"));
        memberVO.setPhone(req.getParameter("phone"));

        session.setAttribute("id",req.getParameter("id"));
        //DAO 요청
        MemberDAO memberDAO=MemberDAO.getInstance();
        memberDAO.insertMember(memberVO);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
