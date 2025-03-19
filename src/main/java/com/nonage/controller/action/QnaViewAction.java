package com.nonage.controller.action;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class QnaViewAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="qna/qnaView.jsp";

        HttpSession session=req.getSession();

        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");

        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            int qseq=Integer.parseInt(req.getParameter("qseq"));
            QnaDAO qnaDAO=QnaDAO.getInstance();
            QnaVO qnaVO=qnaDAO.getQna(qseq);
            req.setAttribute("qnaVO",qnaVO);
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
