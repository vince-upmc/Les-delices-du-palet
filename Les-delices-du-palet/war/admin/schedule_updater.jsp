<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Accueil</title>
</head>
<body>
	<div id="main-content">
		Init Schedule

		<button onclick="driver()">Launch</button>
		<script>
			function driver() {
				for (i = 1; i <= 10; i++) {
					$.ajax({
						type : "GET",
						url : "/partial_update?sched-id=" + i,
						async : false, //Safe!
						success : ok
					});
				}
			}
			var cpt = 0;
			function ok() {
				$("#main-content").append(
						(++cpt) == 10 ? ("<p>Finished</p>") : ("<p>" + cpt
								+ " done..." + "</p>"))
			}
		</script>
	</div>
	<%@include file="/WEB-INF/templates/footer.jsp"%>
</body>
</html>