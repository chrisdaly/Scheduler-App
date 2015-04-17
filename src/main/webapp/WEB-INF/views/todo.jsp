<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<h1>Scheduler items:</h1>

<form method="post" action="../todo/">
	<input name="text" type="text" value=""> <input name="create"
		type="submit" value="Create">
</form>

<c:forEach items="${todos}" var="todo" varStatus="row">
	<form method="post" action="${todo.id}">
		<input name="_method" type="hidden" value="delete"> <input
			name="delete" type="submit" value="Delete"> ${row.index}.
		${todo.text} ${todo.done }
	</form>

</c:forEach>
</html>