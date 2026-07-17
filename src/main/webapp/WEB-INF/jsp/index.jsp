<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>ログイン画面</title>
</head>

<body>

<title>ログイン</title>


<form action="login" method="post">

    ユーザー名：<br>
    <input type="text" name="accountName"><br><br>

    パスワード：<br>
    <input type="password" name="accountPass"><br><br>

    <input type="submit" value="ログイン">
    
     <input type="button" value="新規会員登録"
           onclick="location.href='account-create'">

</form>

</body>
</html>
