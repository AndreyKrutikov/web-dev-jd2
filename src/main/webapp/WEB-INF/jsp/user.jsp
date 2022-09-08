<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div>
    <h1>Personal user page</h1>
</div>

<div>
    <b>
        ${user.name}
    </b>
</div>

<div>
    <table>
        <thead>
            <td>User Id</td>
            <td>User Name</td>
            <td>User Surname</td>
            <td>Birth date</td>
            <td>Created</td>
            <td>Modified</td>
            <td>Rating</td>
            <td>Is Deleted</td>
            <td>Modify profle:</td>
            <td>Delete profile:</td>
        </thead>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td><fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${user.modified}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatNumber type="number" value="${user.rating}"/></td>
            <td>${user.isDeleted}</td>
            <td>
                <button>Edit</button>
            </td>
            <td>
                <button>Delete</button>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
