package com.nonage.admin.controller.action;

import com.nonage.controller.action.Action;
import com.nonage.admin.dao.AdminDAO;
import com.nonage.admin.dto.AdminVO;
import com.nonage.dto.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminProductListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "admin/adminProductList.jsp";
        AdminDAO adminDAO=AdminDAO.getInstance();

        HttpSession session=req.getSession();
        AdminVO loginAdmin=(AdminVO) session.getAttribute("loginAdmin");

        if(loginAdmin==null){
            url="NonageServlet?command=admin_login_form";
        }

        //시작페이지번호
        int page = 1;
        int limit = 5;

        // 요청한 페이지 번호
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }

            // 전체 게시물의 갯수
            int listCount = adminDAO.getListCount();

            //List<BoardVO> boardList = bDao.selectAllBoards();
            List<ProductVO> productList = adminDAO.getProductList(page, limit);

            // 총 페이지 갯수
            int maxpage = (int) ((double) listCount / limit + 0.95);
            // 블럭의 시작 페이지 번호(1,11,21,31...)
            int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
            // 블럭의 마지막 번호(10,20,30...)
            int endpage = maxpage;

            // 현재 블럭의 마지막 페이지 번호
            if (endpage > startpage + 10 - 1) {
                endpage = startpage + 10 - 1;
            }


            req.setAttribute("page", page);
            req.setAttribute("maxpage", maxpage);
            req.setAttribute("startpage", startpage);
            req.setAttribute("endpage", endpage);
            req.setAttribute("listcount", listCount);
            req.setAttribute("productList", productList);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
