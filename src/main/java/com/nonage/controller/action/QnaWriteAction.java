package com.nonage.controller.action;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.QnaDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class QnaWriteAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="NonageServlet?command=qna_list";

        HttpSession session=req.getSession();
        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");


        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            QnaVO qnaVO=new QnaVO();
            qnaVO.setSubject(req.getParameter("subject"));
            qnaVO.setContent(req.getParameter("content"));
            QnaDAO qnaDAO=QnaDAO.getInstance();
            qnaDAO.insertqna(qnaVO,loginUser.getId());
        }

        resp.sendRedirect(url);
    }
}
