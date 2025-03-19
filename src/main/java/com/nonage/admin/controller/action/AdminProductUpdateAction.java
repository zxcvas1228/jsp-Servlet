package com.nonage.admin.controller.action;

import com.nonage.admin.dao.AdminDAO;
import com.nonage.controller.action.Action;
import com.nonage.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminProductUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String savePath="product_images";
        int uploadFileSizeLimit=5*1024*1024;
        String encType="UTF-8";
        ServletContext context=req.getSession().getServletContext();

        String uploadFilePath=context.getRealPath(savePath);

        System.out.println(uploadFilePath);


        MultipartRequest multi=new MultipartRequest(
                req,
                uploadFilePath,
                uploadFileSizeLimit,
                encType,
                new DefaultFileRenamePolicy()
        );

        String pseq=multi.getParameter("pseq");
        String kind = multi.getParameter("kind");
        String name=multi.getParameter("name");
        int price1 =Integer.parseInt(multi.getParameter("price1"));
        int price2 =Integer.parseInt(multi.getParameter("price2"));
        int price3 =Integer.parseInt(multi.getParameter("price3"));
        String content=multi.getParameter("content");
        String bestyn= multi.getParameter("bestyn");
        String useyn=multi.getParameter("useyn");
        String image=multi.getFilesystemName("image");

        if(image==null){
            image=multi.getFilesystemName("nonmakeImg");
        }

        ProductVO pVo=new ProductVO();
        pVo.setPseq(Integer.parseInt(pseq));
        pVo.setKind(kind);
        pVo.setName(name);
        pVo.setPrice1(price1);
        pVo.setPrice2(price2);
        pVo.setPrice3(price3);
        pVo.setContent(content);
        pVo.setBestyn(bestyn);
        pVo.setUseyn(useyn);
        pVo.setImage(image);

        AdminDAO aDao=AdminDAO.getInstance();
        aDao.updateProduct(pVo);
        resp.sendRedirect("NonageServlet?command=admin_product_list");
    }
}
