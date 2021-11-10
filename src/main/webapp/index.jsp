<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Seminarios> lista = (List<Seminarios>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de seminarios</h1>
        <p>
            <a href="MainController?op=nuevo">Nuevo</a>

        </p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Cupo</th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.hora}</td>
                    <td>${item.cupo}</td>
                    
                    <td><a href="MainController?op=editar&id=${item.id}">Editar
                        </a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}" onclick="
                        return(confirm('Esta seguro de eliminar'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
