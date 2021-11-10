<%@page import="com.emergentes.modelo.Seminarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Seminarios lib = (Seminarios) request.getAttribute("lib"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="${lib.id==0}">
                Nuevo Registro
            </c:if>
            <c:if test="${lib.id!=0}">
                Editar registro
            </c:if>
        </h1>
        <form action="MainController" method="post">
            <input  type="hidden" name="id" value="${lib.id}">
            <table>
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${lib.titulo}"></td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text" name="expositor" value="${lib.expositor}" /></td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="${lib.fecha}" /></td>
                </tr>
                <tr>
                    <td>Hora</td>
                    <td><input type="text" name="hora" value="${lib.hora}" /></td>
                </tr>
                <tr>
                    <td>Cupo</td>
                    <td><input type="text" name="cupo" value="${lib.cupo}" /></td>
                </tr>
                <td></td>
                <td><input type="submit" value="enviar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
