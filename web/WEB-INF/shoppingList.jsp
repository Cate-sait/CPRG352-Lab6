
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <STYLE type="text/css">
            ul {padding-left: 10px}
            li { display: list-item; list-style: none}
        </STYLE>
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username} <a href="ShoppingList?action=logout">Logout</a></p>
        
        <h1>List</h1>
        <form action="" method="POST">
            <label>Add item: </label>
            <input type="text" name="item" value="${item}">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <form action="" method="POST">
            <c:if test="${itemList.size() gt 0}">
                <ul>
                    <c:forEach items="${itemList}" var="item">
                        <li><input type="radio" name="item" value="${item}">${item}</li>
                    </c:forEach>
                </ul>
            
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
            </c:if>
        </form>
    </body>
</html>
