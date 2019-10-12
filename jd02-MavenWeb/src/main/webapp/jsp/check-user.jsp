<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
SPRING в действии :(

${userSpring.login};
 ${userSpring.password};
 		<spring:form method="post"  modelAttribute="userSpring" action="check-user" enctype="utf8">
 		 <spring:input path="name" type="text" /> 
		</spring:form>
	 	
</h2>
</body>
</html>