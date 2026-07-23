<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>ログイン画面</title>
</head>

<body>

<h1>ログイン</h1>


<form action="${pageContext.request.contextPath}/login" method="post">

    ユーザー名：<br>
    <input type="text" name="accountId"><br><br>

    パスワード：<br>
    <input type="password" name="accountPass"><br><br>

    <input type="submit" value="ログイン">
    
    
</form>
 <p><a href="${pageContext.request.contextPath}/account-create">新規会員登録</a></p>

</body>
</html>
