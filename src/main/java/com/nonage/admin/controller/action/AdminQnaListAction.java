package com.nonage.admin.controller.action;

import com.nonage.admin.dao.AQnaDAO;
import com.nonage.admin.dto.AdminVO;
import com.nonage.controller.action.Action;
import com.nonage.dto.QnaVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminQnaListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 이동 페이지
        String url = "/admin/qna/qnaList.jsp";

        // 로그인 유무 확인
        HttpSession session = req.getSession();
        AdminVO loginUser = (AdminVO)session.getAttribute("loginAdmin");

        if(loginUser == null) {
            url = "NonageServlet?command=admin_login_form";
        }else{
            AQnaDAO aqnaDAO = AQnaDAO.getInstance();
            ArrayList<QnaVO> qnaList = aqnaDAO.listQna();
            req.setAttribute("qnaList",qnaList);


        }



        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
