<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文完了</title>
</head>
<body>

<h2>注文が完了しました</h2>

<p>ご注文ありがとうございました。</p>


<a href="<%= request.getContextPath() %>/orders"> 注文履歴を確認 </a><br>
<a href="<%= request.getContextPath() %>/menu"> メニューへ戻る </a>


</body>
</html>