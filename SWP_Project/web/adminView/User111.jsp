<%-- 
    Document   : User111
    Created on : 21-01-2024, 11:31:51
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lấy giá trị ngày hiện tại trong JSP</title>
    </head>
    <body>
        <h1>Ngày hiện tại</h1>
        <%
            // Lấy ngày hiện tại
            java.util.Date currentDate = new java.util.Date();

            // Định dạng ngày theo định dạng mong muốn
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");

            // Chuyển đổi ngày thành chuỗi theo định dạng đã chọn
            String formattedDate = dateFormat.format(currentDate);
        %>

        <!-- Hiển thị ngày hiện tại -->
        <p>Ngày hiện tại là: <%= formattedDate %></p>








        <h1>So sánh ngày trong JSP sử dụng JSTL</h1>

        <%-- Tạo hai biến để lưu trữ ngày --%>
        <c:set var="ngay1" value="<%= formattedDate %>" />
        <c:set var="ngay2" value="25/02/2002" />

        <%-- Kiểm tra xem ngày 1 có trước ngày 2 không --%>
        <c:if test="${ngay1 lt ngay2}">
            Ngày 1 trước Ngày 2
        </c:if>

        <%-- Kiểm tra xem ngày 1 có sau ngày 2 không --%>
        <c:if test="${ngay1 gt ngay2}">
            Ngày 1 sau Ngày 2
        </c:if>

        <%-- Nếu không phải trường hợp nào trên, thì ngày 1 và ngày 2 giống nhau --%>
        <c:if test="${ngay1 eq ngay2}">
            Ngày 1 và Ngày 2 giống nhau
        </c:if>


    </body>
</html>