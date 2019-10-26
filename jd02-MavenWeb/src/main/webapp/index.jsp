<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#centerLayer {
	position: absolute; 
	width: 350px; 
	height: 380px; 
	left: 50%; 
	top: 50%;
	margin-left: -150px; 
	margin-top: -150px; 
	background: #00BFFF; 
	border: solid 1px black; 
	padding: 10px; 
	overflow: auto; 
}
</style>
</head>
<body>	
<div id="centerLayer">
		<h1>Вход в систему</h1>
		<form class="transparent" method="post">
	<input type="hidden" name="command" value="logination" />
   <div class="form-inner">
    <h3>ЛОГИНАЦИЯ</h3>
     <label for="username">имя</label>
     <input type="text" name="login" value="">
     <label for="password">пароль</label>
     <input type="text" name="password" value="">
     <input type="submit" value="ВВод" >
     <input type="submit" value="Регистрация" formaction= "jsp/regPage.jsp">
   </div>
  
</form>
	</div>	
</body>
</html>