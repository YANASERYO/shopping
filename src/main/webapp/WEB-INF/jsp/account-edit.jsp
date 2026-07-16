<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント編集</title>
</head>
<body>

<h2>アカウント編集</h2>

<form action="accountEdit" method="post">

氏名<br>
<input type="text" name="accountName"><br><br>

郵便番号<br>
<input type="text" name="postalCode"><br><br>

住所<br>
<input type="text" name="accountAddress"><br><br>

電話番号<br>
<input type="text" name="accountPhone"><br><br>

メールアドレス<br>
<input type="text" name="email"><br><br>

<input type="submit" value="更新">

</form>

</body>
</html>