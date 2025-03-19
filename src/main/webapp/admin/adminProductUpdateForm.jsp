<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_menu.html" %>
<!--상품수정 시작-->

<div id="wrap" align="center">
        <form method="post" enctype="multipart/form-data" name="frm" action="NonageServlet?command=admin_product_update">
            <input type="hidden" name="pseq" value="${adminProductVO.pseq}">
            <input type="hidden" name="nonmakeImg" value="${adminProductVO.image}">
            <table>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${empty adminProductVO.image}">
                                <img src="product_images/logo.gif">
                            </c:when>
                            <c:otherwise>
                                <img src="product_images/${adminProductVO.image}">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td>분류명</td>
                                <td>
                                    <select name="kind">
                                        <option value=1>Heels</option>
                                        <option value=2>Boots</option>
                                        <option value=3>Sandals</option>
                                        <option value=4>Sneakers</option>
                                        <option value=5>On sale</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 80px">상품명</td>
                                <td>
                                    <input type="text" name="name" value="${adminProductVO.name}" size="80">
                                </td>
                            </tr>
                            <tr>
                                <td>원가</td>
                                <td>
                                    <input type="text" name="price1" value="${adminProductVO.price1}" size="20">
                                </td>
                                    <td>판매가</td>
                                <td>
                                    <input type="text" name="price2" value="${adminProductVO.price2}" size="20">
                                </td>
                                <td>마진</td>
                                <td>
                                    <input type="text" name="price3" value="${adminProductVO.price3}" size="20">
                                </td>
                            </tr>
                            <tr>
                                <td>베스트상품</td>
                                <td>
                                    <input type="checkbox" name="bestyn" value="y">
                                </td>
                                <td>사용유무</td>
                                <td>
                                    <input type="checkbox" name="useyn" value="y">
                                </td>
                            </tr>
                            <tr>
                                <td>설 명</td>
                                <td>
                                    <textarea cols="90" rows="10" name="content">
                                    ${adminProductVO.content}
                                    </textarea>
                                </td>
                            </tr>
                            <span style="float: left;">
                                 <img  src="product_images/${adminProductVO.image}"  />
                            </span>
                            <tr>
                                <td>사 진</td>
                                <td>
                                    <input type="file" name="image"><br>
                                    (주의사항 : 이미지를 변경할때만 사용하십시오)
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="수정">
            <input type="reset" value="다시작성">
            <input type="button" value="목록" onclick="location.href='NonageServlet?command=admin_product_list'">
        </form>
    </div>



<!--상품수정 끝-->
<%@ include file="../footer.jsp" %>