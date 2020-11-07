<%@ page import="com.company.entity.Ticket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Ticket</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>بلیط اتوبوس</th>
            </tr>
        </thead>
        <%
            Ticket ticket = (Ticket) request.getAttribute("ticket");
        %>
        <tbody>
            <tr>
                <th>شناسه بلیط</th>
                <th><%=ticket.getId()%></th>
            </tr>
            <tr>
                <th>نام مسافر</th>
                <th><%=ticket.getPassanger().getUsername()%></th>
            </tr>
            <tr>
                <th>جنسیت</th>
                <th><%=ticket.getPassanger().getGender()%></th>
            </tr>
            <tr>
                <th>مبدا</th>
                <th><%=ticket.getRoute().getFrom()%></th>
            </tr>
            <tr>
                <th>مقصد</th>
                <th><%=ticket.getRoute().getTo()%></th>
            </tr>
            <tr>
                <th>تاریخ حرکت</th>
                <th><%=ticket.getDate()%></th>
            </tr>
            <tr>
                <th>ساعت حرکت</th>
                <th><%=ticket.getTime()%></th>
            </tr>
            <tr>
                <th>شناسه سفر</th>
                <th><%=ticket.getTravelNumber()%></th>
            </tr>
        </tbody>
    </table>
</body>
</html>
