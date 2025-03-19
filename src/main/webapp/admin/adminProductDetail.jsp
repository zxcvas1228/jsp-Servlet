<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_menu.html" %>
<!--상세페이지 시작-->

<article>
    <h1> Item </h1>
    <div id="itemdetail" >
      <form  method="post" name="formm">
        <fieldset>
          <legend> Item detail Info</legend>
          <a href="">
            <span style="float: left;">
              <img  src="product_images/${adminProductVO.image}"  />
            </span>
            <h2> ${adminProductVO.name} </h2>
          </a>
          <label> 분 류 :  </label>
          <p> ${kindCheck} </p>
          <label> 원 가 :  </label>
          <p> ${adminProductVO.price1} 원</p>
          <label> 판매가 :  </label>
          <p> ${adminProductVO.price2} 원</p>
          <label> 마 진 :  </label>
          <p> ${adminProductVO.price3} 원</p>
          <label> 설 명 :  </label>
          <p> ${adminProductVO.content} </p>

          <input  type="hidden"    name="pseq"       value="${adminProductVO.pseq}"><br>
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">

                <input type="button" value="수정"   class="submit" onclick =
                 "location.href ='NonageServlet?command=admin_product_update_form&pseq=${adminProductVO.pseq}'">
                <input type="button" value="목록"   class="submit" >

        </div>
      </form>
    </div>
  </article>


<!--상세페이지 끝-->
<%@ include file="../footer.jsp" %>