<%@ page contentType="text/html; charset=UTF-8"pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<body>
<h1>管理者メニュー</h1>
<p><a href="${pageContext.request.contextPath}/admin/products/create">商品を登録する</a></p>
<p><a href="${pageContext.request.contextPath}/admin/products">商品を編集する</a></p>
<p><a href="${pageContext.request.contextPath}/logout">ログアウト</a></p>
</body>
</html>