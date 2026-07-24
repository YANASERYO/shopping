<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
<h1>メニュー</h1>
<ul>

	<li><a href="${pageContext.request.contextPath}/products">商品を選択する</a></li>
	<li><a href="${pageContext.request.contextPath}/cart">ショッピングカートを見る</a></li>
	<li><a href="${pageContext.request.contextPath}/orders">注文履歴を見る</a></li>
	<li><a href="${pageContext.request.contextPath}/account-edit">会員情報の変更</a></li>

</ul>
<a href="${pageContext.request.contextPath}/logout">ログアウト</a>
</body>
</html>