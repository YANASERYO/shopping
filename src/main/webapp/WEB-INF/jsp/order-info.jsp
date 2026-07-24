<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認</title>
</head>
<body>

<h2>注文者情報入力</h2>

<form action="<%= request.getContextPath() %>/order/confirm" method="post">

発送先<br>
<input type="text" name="shippingAddress"><br><br>

支払い方法<br>
<input type="text" name="payment"><br><br>

<input type="submit" value="注文確認">

</form>

</body>
</html>