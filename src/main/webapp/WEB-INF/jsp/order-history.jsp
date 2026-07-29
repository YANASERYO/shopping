<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
</head>
<body>
<h2>購入履歴</h2>

<c:choose>
    <c:when test="${empty detailList}">
        <p>購入履歴はありません。</p>
    </c:when>
    <c:otherwise>
        <c:set var="previousShoppingId" value="" />
        <c:forEach var="detail" items="${detailList}">
            <c:if test="${previousShoppingId != detail.shoppingId}">
                <c:if test="${not empty previousShoppingId}">
                    </tbody>
                    </table>
                    <hr>
                </c:if>
                <h3>
                    注文番号：${detail.shoppingId}
                </h3>
                <table border="1">
                    <thead>
                        <tr>
                            <th>商品名</th>
                            <th>価格</th>
                            <th>数量</th>
                            <th>小計</th>
                            <th>税込金額</th>
                        </tr>
                    </thead>
                    <tbody>
                <c:set var="previousShoppingId"
                       value="${detail.shoppingId}" />
            </c:if>
            <tr>
                <td>
                    ${detail.productName}
                </td>
                <td>
                    <fmt:formatNumber value="${detail.productPrice}" pattern="#,###" />円
                </td>
                <td>
                    ${detail.productPieces}
                </td>
                <td>
                    <fmt:formatNumber value="${detail.productTotal}" pattern="#,###" />円
                </td>
                <td>
					<fmt:formatNumber
						value="${detail.productTaxAndPrice}"
						pattern="#,###" />円
				</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:otherwise>
</c:choose>
<p>
    <a href="${pageContext.request.contextPath}/menu">
        メニューへ戻る
    </a>
</p>

</body>
</html>