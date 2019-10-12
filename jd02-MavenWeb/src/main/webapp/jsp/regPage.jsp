<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Страница регистрации</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="screen" >

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.registration.data" var="regData" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
</head>
<form action="../controller" method="post">
	<input type="hidden" name="command" value="registration" />
		<input type="hidden" name="local" value="ru" /> <input
			type="submit" value="${ru_button}">
	</form>
	<form action="../controller" method="post">
	<input type="hidden" name="command" value="registration" />
		<input type="hidden" name="local" value="en" /> <input
			type="submit" value="${en_button}">
	</form>
<body>
	<form class="transparent2" action="../controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<div class="form-inner">
			<h3>Введите Ваши данные</h3>
			
			<label for="name">Имя пользователя</label>
			<input type="text"  name="name" value="" >
			
			<label for="login">Логин</label>
			<input type="text" name="login" value="" >
			
			<label for="password">Пароль</label>
			 <input id="password" name="password" value="" >
			 
			<label for="position">Должность</label>
			<input type="text" name="position" value="" >
		
			<label for="specialization">Специализация</label>
			<input type="text" name="specialization" value="" >
			
			<input	type="submit" value="Регистрация" > 
			
			<label for="name">
			 <%
     if (request.getAttribute("NullUser") != null) {
    	 out.print("Введены недопустимые данные. Вводите достоверные данные");
     } %>
			</label>
	
		 </div>

		</form>
	
</body>
</html>