<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="adminHeader.jsp" %>
<%@ include file="sub_menu.html" %>
<!--로그인 시작-->

<article>
    <h1>관리자 Login</h1>
    <form method="post" action="NonageServlet?command=admin_login">
        <fieldset>
        <legend></legend>
          <label>Admin ID</label>
          <input name="id" type="text" value="${id}"><br>
          <label>Password</label>
          <input name="pwd" type="password" value="${pwd}"><br>
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">
            <input type="submit" value="업무 로그인" class="submit">
        </div>
    </form>
  </article>



<!--로그인 끝-->
<%@ include file="../footer.jsp" %>