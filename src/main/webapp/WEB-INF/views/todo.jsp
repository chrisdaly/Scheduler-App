<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
form {
}

h1 {
	margin: 0 auto;
	width: 250px;
}

table, th, td {
   border: ;
}

.table {
	margin: 0 auto;
	width: 60%;
}

.btn {

}
</style>

</head>

<body>

	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Scheduler</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

<!-- 		<h1>Tasks:</h1>
 -->
 	<br />
		<form method="post" action="../todo/"  align=center>
			<input name="text" type="text" value="" > <input name="create task"
				class="btn btn-primary" type="submit" value="Create Task">

		</form>

	</div>

	<br />


	<table class="table table-striped table-hover">
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

				<td><a class="btn btn-primary"
					href="/_edit/{{p.title}}?v={{p.key().id()}}"><i
						class="icon-list"></i>Edit</a></td>

				<td>
					<form method="post" action="${todo.id}">
						<input name="_method" type="hidden" value="delete"> <input
							name="delete" type="submit" value="Delete" class="btn btn-danger">
					</form>
				</td>

			</tbody>
		</c:forEach>


	</table>

</body>
</html>