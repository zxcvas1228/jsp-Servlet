package com.nonage.controller.action;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductKindAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="product/productKind.jsp";

        //파라메터정보 받기
        String kind=req.getParameter("kind");


        //DAO 요청
        ProductDAO productDAO = ProductDAO.getInstance();
        ArrayList<ProductVO> productKindList=productDAO.listKindProduct(kind);
        req.setAttribute("productKindList",productKindList);


        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
