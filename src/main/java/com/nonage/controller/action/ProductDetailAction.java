package com.nonage.controller.action;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductDetailAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="product/productDetail.jsp";

        //파라메터정보 받기
        String pseq=req.getParameter("pseq");


        //DAO 요청
        ProductDAO productDAO = ProductDAO.getInstance();
        ProductVO productVO=productDAO.getProduct(pseq);

        req.setAttribute("productVO",productVO);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
