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
			var init = 1;
			function driver() {
				$.ajax({
					type : "GET",
					url : "/admin/updateTeam",
					async : false,
					error : function() {
						$("#main-content").append("Erreur dans la mise à jour des équipes<br/>");
						throw "Impossible de continuer";
					},
					success : function() {
						$("#main-content").append("Equipes et standings mises à jour<br/>");
					}
				});
				for (i = init; i <= 10; i++) {
					$.ajax({
						type : "GET",
						url : "/admin/partial_update?sched-id=" + i,
						async : true, //Safe!
						error : merde,
						success : ok
					});
				}
			}
			var cpt = init - 1;
			function merde() {
				$("#main-content").append("error : " + (++cpt));
			}
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