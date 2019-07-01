<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>All Shops</title>
    </head>
    <body>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Water</th>
        <th>Pants</th>
    </tr>
    <c:forEach items="${shopList}" var="row">
       <tr>
       					<c:forEach var = "cell" items = "${row}">
       						<td> <c:out value = "${cell}"/> </td>
       					</c:forEach>
       </tr>
    </c:forEach>
</table>

   </body>
</html>