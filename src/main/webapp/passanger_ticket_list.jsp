<%@ page import="com.company.entity.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passanger Ticket List</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>لیست بلیط های خریداری شده</th>
            </tr>
            <tr>
                <th>تاریخ</th>
                <th>شناسه بلیط</th>
                <th>انتخاب</th>
            </tr>
        </thead>
        <%
            List<Ticket> tickets = (List<Ticket>) request.getAttribute("list");
            for (Ticket ticket : tickets) {
        %>
        <tbody>
            <tr>
                <th><%=ticket.getDate()%></th>
                <th><%=ticket.getId()%></th>
                <th>
                    <form method="get" action="your_ticket">
                        <%=request.setAttribute("ticket_id" , ticket.getId())%>
                        <button type="submit">مشاهده بلیط</button>
                    </form>
                </th>
            </tr>
        </tbody>
        <%
            }
        %>
    </table>
</body>
</html>
