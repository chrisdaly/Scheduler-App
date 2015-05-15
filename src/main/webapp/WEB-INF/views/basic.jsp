<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<sec:authentication property="principal" var="user" />
${user.username}
<a href="../logout">Logout</a>
<h1>TODO items:</h1>

<form method="post" action="../todo/">
	<input name="text" type="text" value=""> <input name="create"
		type="submit" value="Create"> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<c:forEach items="${todos}" var="todo" varStatus="row">
	<form method="post" action="${todo.id}">
		<input name="_method" type="hidden" value="delete"> <input
			name="delete" type="submit" value="Delete"> ${row.index}.
		${todo.text} ${todo.done }
	</form>

	<form method="post" action="${todo.id}">
		<input name="_method" type="hidden" value="put"> <input
			name="put" type="submit" value="Edit"> ${row.index}.
		${todo.text} ${todo.done }
	</form>

</c:forEach>
</html>