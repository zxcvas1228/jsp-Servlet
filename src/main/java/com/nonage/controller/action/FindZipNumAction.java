package com.nonage.controller.action;

import com.nonage.dao.AddressDAO;
import com.nonage.dao.MemberDAO;
import com.nonage.dto.AddressVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FindZipNumAction implements Action{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이동 페이지
        String url="member/findZipNum.jsp";

        //파라메터정보 받기
        String dong=req.getParameter("dong");


        //DAO 요청
        if(dong!=null&&dong.equals("")==false){
            AddressDAO addressDAO=AddressDAO.getInstance();
            ArrayList<AddressVO> addressList=addressDAO.selectAddressByDong(dong.trim());
            req.setAttribute("addressList",addressList);
        }



        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
