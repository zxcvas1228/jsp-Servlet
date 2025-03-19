package com.nonage.controller;

import com.nonage.controller.action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NonageServlet")
public class NonageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command=req.getParameter("command");
        System.out.println("BoardServlet:"+command);

        ActionFactory af = ActionFactory.getInstance();
        Action action=af.getAction(command);

        if(action!=null){
            action.execute(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
