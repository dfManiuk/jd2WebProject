<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Страница пациентов</title>
</head>

<style>
   .b1 {
    border: 1px solid green;
    margin: auto; 
   }
   td {
    text-align: center; 
   }
   }
  </style>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"	media="screen" />
</head>
<body>



<table class="b1">
		<thead>
		</thead>
		<caption>Находящиеся на лечении пациенты</caption>
		<thead>
			<tr>
				<th>Имя пациента</th>
				<th>Дата рождения</th>
				<th>Место жительства</th>
				<th>Телефон</th>
			</tr>
		</thead>
		<c:forEach items="${PatientList}" var="plist">
			<tr>
				<td>${plist.name}</td>
				<td>${plist.data}</td>
				<td>${plist.adress}</td>
				<td>${plist.telephone}</td>
			</tr>
		</c:forEach>
		<c:if test="${Count != null}">
				Количество страниц  <c:forEach var="counter" begin="0"	end="${Count}">
						<c:if test="${counter != PageUsed }">
							<form action="controller" method="post">
								<input type="hidden" name="command"
									value="show_all_patients" /> <input type="hidden"
									name="start" value="${counter}"> <input type="submit"
									value="${counter + 1}">
							</form>
						</c:if>
						<c:if test="${counter == PageUsed }">
							<font color=red> <c:out value="${counter + 1}" />
							</font>
						</c:if>
					</c:forEach>

				<th>Вывести пациентов по:
					<form class="transparent" action="controller" method="post">
						<input type="hidden" name="command" value="show_all_patients" />
						<input type="submit" name="delimeter" value="5"> 
					 <input type="submit" name="delimeter" value="10">
						 <input type="submit" name="delimeter" value="25">
					</form>
				</th>

			</c:if>
	</table>
	<table class="b1">
	<tr>			
					<td colspan="2" style="text-align: center">
							<form action="controller" method="post">
								<input type="hidden" name="command"
									value="patient_dischanged_list"> 
									<input type="submit" value="Выписанные пациенты">
							</form>				
						</td>
			</tr>
			</table>
</body>
</html>