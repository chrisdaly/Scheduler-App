<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<sec:authentication property="principal" var="user" />



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- My CSS -->
<link rel="stylesheet" href="/static/main.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<title>Scheduler</title>

<style>
body {
	position: relative;
	height: 100%;
	font-family: calibri;
	font-size: 14px;
	width: 825px;
	margin: 0 auto;
	padding: 10px;
	color: #333;
	text-decoration: none;
}

.navbar-brand {
	position: absolute;
	width: 100%;
	left: 0;
	text-align: center;
	margin: auto;
}

#footer {
	clear: both;
	position: relative;
	z-index: 10;
	height: 3em;
	margin-top: -3em;
}

html, body, #container {
	height: 100%;
}

body>#container {
	height: auto;
	min-height: 100%;
}

#content {
	padding-bottom: 3em;
}
</style>

</head>

<body>

	<div id="container">

		<div id="content">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<a class="navbar-brand" href="#">Scheduler</a>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-left">
						<li><a>Welcome ${user.username}</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="../logout">Logout</a></li>
					</ul>
				</div>
			</nav>

			<%
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
				out.print("<h3 align=\"center\">" + ft.format(dNow) + "</h3>");
			%>

			<%
				Date dNow2 = new Date();
				SimpleDateFormat ft2 = new SimpleDateFormat("E dd/MM/yyyy");
				out.print("<h4 align=\"center\">" + ft2.format(dNow2) + "</h4>");
			%>

			<br />
			<form method="post" action="../todo/" align=center>
				<input name="text" type="text" value=""
					placeholder="Assignment#College"> <input name="create task"
					class="btn btn-primary" type="submit" value="Create Task" />

			</form>

			<br />
			<div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Tag</th>
							<th>Task</th>
							<th>Status</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>

					<c:forEach items="${todos}" var="todo" varStatus="row">
						<tbody>
							<td>${todo.tag}</td>
							<td>${todo.text}</td>
							<td>${todo.done}</td>

							<td>


								<form method="post" action="${todo.id}">
									<input name="_method" type="hidden" value="put"> <input
										name="put" type="submit" value="Edit" class="btn btn-primary">
								</form>
							</td>




							<td>
								<form method="post" action="${todo.id}">
									<input name="_method" type="hidden" value="delete"> <input
										name="delete" type="submit" value="Delete"
										class="btn btn-danger">
								</form>
							</td>

						</tbody>
					</c:forEach>


				</table>
			</div>

		</div>

	</div>

	<div id="footer">
		<a class="btn btn-block "
			href="https://github.com/chrisdaly/Scheduler-App"><img
			src='http://sarahtattersall.github.io/PIPE/images/GitHub-Mark-32px.png' /></a>

	</div>


</body>
</html>