package com.nonage.admin.controller.action;

import com.nonage.admin.dao.AdminDAO;
import com.nonage.admin.dto.AdminVO;
import com.nonage.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="admin/admin_login_fail.jsp";
        HttpSession session= req.getSession();

        //파라메터정보 받기
        String id=req.getParameter("id");
        String pwd=req.getParameter("pwd");


        //DAO 요청
        AdminDAO adminDAO=AdminDAO.getInstance();
        AdminVO adminVO=AdminDAO.getAdmin(id,pwd);

        if(adminVO!=null){
            session.removeAttribute("id");
            session.setAttribute("loginAdmin",adminVO);
            url="NonageServlet?command=admin_product_list&page=1";
        }



        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
