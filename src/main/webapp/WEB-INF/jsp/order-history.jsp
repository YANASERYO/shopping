<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="com.example.demo.model.Account" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購入履歴 | KINARI</title>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee&family=M+PLUS+Rounded+1c:wght@900&family=Philosopher:ital,wght@0,400;0,700;1,400;1,700&family=Zen+Old+Mincho:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
<header >
	<div class="logotitle">
    <img class="logo" src="${pageContext.request.contextPath}/images/logo.png">
    <h1>KINARI</h1>
    </div>
    <%Account account = (Account) session.getAttribute("account");%>
 	<div class="header-menu">
 	<a href="${pageContext.request.contextPath}/menu">
	メニューへ戻る</a>
	<a href="${pageContext.request.contextPath}/cart">
	カートを見る</a>
	</div>
	<div class="user-area">
	<p class="username">ようこそ <%= account.getAccountName() %> さん</p>
    <a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
    </div>
  </header>
  <main  class="order-history">
<h2>購入履歴</h2>

<c:choose>
    <c:when test="${empty detailList}">
        <p class="not-order-history">購入履歴はありません。</p>
    </c:when>
    <c:otherwise>
    
    	<div class="order-list">
        <c:set var="previousShoppingId" value="" />
        <c:forEach var="detail" items="${detailList}">
            <c:if test="${previousShoppingId != detail.shoppingId}">
                <c:if test="${not empty previousShoppingId}">
                    </tbody>
                    </table>
                   </div>
        
                </c:if>
        <div class="order-card">  
                
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
				<div class="shopping-info">
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
        </div>
        </div>
    </c:otherwise>
</c:choose>
<div class="link-btn">
<p>
    <a href="${pageContext.request.contextPath}/menu">
        メニューへ戻る
    </a>
</p>
</div>
</main>
<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>

</body>
</html>