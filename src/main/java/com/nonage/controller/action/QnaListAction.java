package com.nonage.controller.action;

import com.nonage.dao.OrderDAO;
import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;
import com.nonage.dto.QnaVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class QnaListAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="qna/qnaList.jsp";

        HttpSession session=req.getSession();

        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");

        if(loginUser==null){
            url="NonageServlet?command=login_form";
        }else {
            QnaDAO qnaDAO=QnaDAO.getInstance();
            ArrayList<QnaVO> qnaList=qnaDAO.listQna(loginUser.getId());
            req.setAttribute("qnaList",qnaList);
        }


            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}

