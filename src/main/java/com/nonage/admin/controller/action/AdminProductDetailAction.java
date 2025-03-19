package com.nonage.admin.controller.action;

import com.nonage.admin.dao.AdminDAO;
import com.nonage.admin.dto.AdminVO;
import com.nonage.controller.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminProductDetailAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="admin/adminProductDetail.jsp";

        //파라메터정보 받기
        String pseq=req.getParameter("pseq");


        //DAO 요청
        AdminDAO adminDAO = AdminDAO.getInstance();
        ProductVO adminProductVO=adminDAO.getAdminProduct(pseq);
        String kindCheck = adminDAO.kind_check(adminProductVO.getKind());

        req.setAttribute("adminProductVO",adminProductVO);
        req.setAttribute("kindCheck",kindCheck);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
