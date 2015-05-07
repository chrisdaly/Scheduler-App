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
	border:;
}

.table {
	margin: 0 auto;
	width: 60%;
}

.btn {
	padding: 30px
}

tbody tr:nth-child(odd) {
	/*    background-color: #ccc; */
	
}

/*
 * Globals
 */

/* Links */
a, a:focus, a:hover {
	color: #fff;
}

/* Custom default button */
.btn-default, .btn-default:hover, .btn-default:focus {
	color: #333;
	text-shadow: none; /* Prevent inheritence from `body` */
	background-color: #fff;
	border: 1px solid #fff;
}

/*
 * Base structure
 */
html, body {
	height: 100%;
	background-color: #333;
}

body {
	color: #fff;
	text-align: center;
	text-shadow: 0 1px 3px rgba(0, 0, 0, .5);
}

/* Extra markup and styles for table-esque vertical and horizontal centering */
.site-wrapper {
	display: table;
	width: 100%;
	height: 100%; /* For at least Firefox */
	min-height: 100%;
	-webkit-box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
	box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
}

.site-wrapper-inner {
	display: table-cell;
	vertical-align: top;
}

.cover-container {
	margin-right: auto;
	margin-left: auto;
}

/* Padding for spacing */
.inner {
	padding: 30px;
}

/*
 * Header
 */
.masthead-brand {
	margin-top: 10px;
	margin-bottom: 10px;
}

.masthead-nav>li {
	display: inline-block;
}

.masthead-nav>li+li {
	margin-left: 20px;
}

.masthead-nav>li>a {
	padding-right: 0;
	padding-left: 0;
	font-size: 16px;
	font-weight: bold;
	color: #fff; /* IE8 proofing */
	color: rgba(255, 255, 255, .75);
	border-bottom: 2px solid transparent;
}

.masthead-nav>li>a:hover, .masthead-nav>li>a:focus {
	background-color: transparent;
	border-bottom-color: #a9a9a9;
	border-bottom-color: rgba(255, 255, 255, .25);
}

.masthead-nav>.active>a, .masthead-nav>.active>a:hover, .masthead-nav>.active>a:focus
	{
	color: #fff;
	border-bottom-color: #fff;
}

@media ( min-width : 768px) {
	.masthead-brand {
		float: left;
	}
	.masthead-nav {
		float: right;
	}
}

/*
 * Cover
 */
.cover {
	padding: 0 20px;
}

.cover .btn-lg {
	padding: 10px 20px;
	font-weight: bold;
}

/*
 * Footer
 */
.mastfoot {
	color: #999; /* IE8 proofing */
	color: rgba(255, 255, 255, .5);
}

/*
 * Affix and center
 */
@media ( min-width : 768px) {
	/* Pull out the header and footer */
	.masthead {
		position: fixed;
		top: 0;
	}
	.mastfoot {
		position: fixed;
		bottom: 0;
	}
	/* Start the vertical centering */
	.site-wrapper-inner {
		vertical-align: middle;
	}
	/* Handle the widths */
	.masthead, .mastfoot, .cover-container {
		width: 100%;
		/* Must be percentage or pixels for horizontal alignment */
	}
}

@media ( min-width : 992px) {
	.masthead, .mastfoot, .cover-container {
		width: 700px;
	}
}
</style>

</head>



<body>

	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">

				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Scheduler</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li class="active"><a href="#">Chris</a></li>
								<li><a href="#">Logout</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<h1>17:53</h1>
				<h2>Friday</h2>
				<h2>24th April 2015</h2>

				<div class="inner cover">
					<form method="post" action="../todo/" align=center>
						<input name="text" type="text" value="">

						<p class="lead">
							<br /> <input name="create task" class="btn btn-lg btn-default"
								type="submit" value="Create Task">
						</p>

					</form>

				</div>

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

								<td><a class="btn btn-primary" href=""></i>Edit</a></td>

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


				<div class="mastfoot">
					<div class="inner">
						<p>
							App by <a href="https://github.com/chrisdaly/Scheduler-App/">Chris
								Daly.</a>
						</p>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>

// /////////////////////////////