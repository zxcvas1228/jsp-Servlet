package com.nonage.controller.action;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class IndexAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="/index.jsp";
        //DAO 요청
        ProductDAO productDAO = ProductDAO.getInstance();
        ArrayList<ProductVO> newProductList = productDAO.listNewProduct();
        ArrayList<ProductVO> bestProductList = productDAO.listBestProduct();

        req.setAttribute("newProductList",newProductList);
        req.setAttribute("bestProductList",bestProductList);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);


    }
}
