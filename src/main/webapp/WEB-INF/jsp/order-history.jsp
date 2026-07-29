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
                <p>
					購入日：${detail.shoppingDate}
				</p>
				<p>
					注文合計：
						<fmt:formatNumber
							value="${detail.shoppingTotalPrice}"
							pattern="#,###" />円
					<br>
					税込み金額：
						<fmt:formatNumber
						value="${detail.shoppingTaxAndPrice}"
						pattern="#,###" />円
				</p>
				<div>
					<h4>発送先情報</h4>
					<p>
						発送先氏名：${detail.shippingName}
					</p>
					<p>
						郵便番号：〒${detail.shippingPostalCode}
					</p>
					<p>
						住所：${detail.shippingAddress}
					</p>
					<p>
						電話番号：${detail.shippingPhone}
					</p>
					<p>
						メールアドレス：${detail.shippingEmail}
					</p>
					<p>
						支払い方法：${detail.shippingPayment}
					</p>
				</div>
                <table border="1">
                    <thead>
                        <tr>
                            <th>商品名</th>
                            <th>価格</th>
                            <th>数量</th>
                            <th>小計</th>
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