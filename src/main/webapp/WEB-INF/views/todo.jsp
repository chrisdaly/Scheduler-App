<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<h1>Scheduler items:</h1>
<c:forEach items="${todos}" var="task" varStatus="row">
${row.index}. ${task.text} <br />
</c:forEach>

</html>