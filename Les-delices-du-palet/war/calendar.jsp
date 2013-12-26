<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Calendrier</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<h1>Calendar</h1>
			<table class="calendar">
				<tr class="calendar-item">
					<td class="game-time">1/1</td>
					<td class="left-team">Prout</td>
					<td class="right-team">Bla</td>
				</tr>
				<tr class="calendar-item">
					<td class="game-time">1/1</td>
					<td class="left-team">Prout</td>
					<td class="right-team">Bla</td>
				</tr>
				<tr class="calendar-item">
					<td class="game-time">1/1</td>
					<td class="left-team">Prout</td>
					<td class="right-team">Bla</td>
				</tr>
				<tr class="calendar-item">
					<td class="game-time">1/1</td>
					<td class="left-team">Prout</td>
					<td class="right-team">Bla</td>
				</tr>
			</table>
		</div>

		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>

	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>