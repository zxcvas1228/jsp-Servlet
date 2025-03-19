<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="adminHeader.jsp" %>
<%@ include file="sub_menu.html" %>
<!--상품목록 시작-->

<%
    int listcount=((Integer)request.getAttribute("listcount")).intValue();
    int nowpage=((Integer)request.getAttribute("page")).intValue();
    int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
    int startpage=((Integer)request.getAttribute("startpage")).intValue();
    int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>




<div id="wrap" align="center">
          <h1>상품 리스트</h1>
          <table>
                <tr>
                    <td>
                        상품명<input type="search"><button type="submit">검색</button>
                        <input type="button" value="전체보기" onclick="location.href='NonageServlet?command=admin_product_list'">
                        <input type="button" value="상품등록" onclick="location.href='NonageServlet?command=admin_product_list'">
                    </td>
                </tr>
          </table>
          <table class="list">
             <tr>
                <th>번호</th>
                <th>상품명</th>
                <th>원가</th>
                <th>판매가</th>
                <th>등록일</th>
                <th>사용유무</th>
             </tr>
             <c:forEach var = "productVO" items="${productList}">
                <tr class="record">
                    <td>${productVO.pseq}</td>
                    <td>
                        <a href="NonageServlet?command=admin_product_detail&pseq=${productVO.pseq}">${productVO.name}</a>
                    </td>
                    <td>${productVO.price1}</td>
                    <td>${productVO.price2}</td>
                    <td><fmt:formatDate value="${productVO.indate}"/></td>
                    <td>${productVO.useyn}</td>
                </tr>
             </c:forEach>



                <tr align="center" height=20>
                                    <td colspan=5 style="font-family:Tahoma;font-size:10pt">
                                        <%
                                            if (nowpage <= 1) {
                                        %>
                                                [이전]&nbsp;&nbsp
                                         <%  }else {  %>

                                            <a href="./NonageServlet?command=admin_product_list&page=<%=nowpage -1 %>">[이전]</a>
                                       <%   } %>

                                        <%
                                            for(int num = startpage;num <= endpage;num++) {
                                                if(num == nowpage) { %>
                                                    [<%= num%>]
                                               <%} else {  %>
                                                    <a href ="./NonageServlet?command=admin_product_list&page=<%=num %>">[<%= num%>]</a>
                                              <% }
                                            }
                                        %>
                                        <%
                                            if (nowpage >= 3) {
                                        %>
                                                [다 음]&nbsp;&nbsp
                                         <%  }else {  %>

                                            <a href="./NonageServlet?command=admin_product_list&page=<%=nowpage +1 %>">[다음]</a>

                                       <%   } %>
                                    </td>
                                </tr>

          </table>
</div>



<!--상품목록 끝-->
<%@ include file="../footer.jsp" %>