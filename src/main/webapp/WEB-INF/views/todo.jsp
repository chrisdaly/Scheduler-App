<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<h1>Scheduler items:</h1>

<form method="post" action="../todo/">
	<input name="text" type="text" value=""> <input name="create"
		type="submit" value="Create">
</form>


<c:forEach items="${todos}" var="task" varStatus="row">
${row.index} &nbsp; ${task.text} &nbsp; ${task.tag} &nbsp; ${task.done} <br />
</c:forEach>

</html>