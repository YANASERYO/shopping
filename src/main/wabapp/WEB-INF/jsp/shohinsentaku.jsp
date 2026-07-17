<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品選択 - 大将の特製ショップ</title>
    <style>
        .product-grid { display: flex; flex-wrap: wrap; gap: 20px; }
        .product-card { border: 1px solid #ccc; padding: 15px; width: 200px; border-radius: 5px; }
        .out-of-stock { background-color: #f0f0f0; color: #888; }
        .btn-disabled { background-color: #aaa; cursor: not-allowed; }
    </style>
</head>
<body>

    <h2>商品一覧</h2>
    
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/menu">メニューへ戻る</a> | 
        <a href="${pageContext.request.contextPath}/cart">ショッピングカートを見る</a>
    </div>

    <div class="product-grid">
        <c:forEach var="product" items="${productList}">
            <div class="product-card ${product.stockQuantity == 0 ? 'out-of-stock' : ''}">
                <img src="${pageContext.request.contextPath}/images/${product.imagePath}" alt="${product.name}" width="150">
                <h3>${product.name}</h3>
                <p>${product.description}</p>
                <p>価格: ￥${product.price}</p>
                <p>在庫状態: 
                    <c:choose>
                        <c:when var="hasStock" test="${product.stockQuantity > 0}">
                            残り ${product.stockQuantity} 点
                        </c:when>
                        <c:otherwise>
                            一時在庫切れ
                        </c:otherwise>
                    </c:choose>
                </p>

                <form action="${pageContext.request.contextPath}/cart/add" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <c:choose>
                        <c:when test="${hasStock}">
                            <label>数量: </label>
                            <input type="number" name="quantity" value="1" min="1" max="${product.stockQuantity}">
                            <button type="submit">カートに入れる</button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn-disabled" disabled>在庫切れ</button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </c:forEach>
    </div>

</body>
</html>