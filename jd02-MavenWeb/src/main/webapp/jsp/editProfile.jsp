<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/styleChange.css" media="screen" >

</head>
<body>
Hello!! This is edit profile page
<h1>Внесите изминения</h1>
		<form action="../controller" method="post">
			<input type="hidden" name="command" value="user_edit_profile" />
			<h5>Введите имя пользователя</h5>
			<input type="text" name="login" value="" />
			<h5>Введите пароль</h5>
			<input type="text" name="password" value="" /><br /> <br /> <input
				type="submit" value="Ввод" /> <br />
			<h5>Введите специализацию</h5>
			<input type="text" name="specialization" value="" /><br /> <br /> <input
				type="submit" value="Ввод" /> <br />
		</form>
</body>
</html>