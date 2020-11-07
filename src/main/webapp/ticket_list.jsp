<%@ page import="com.company.entity.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket List</title>
</head>
<body>
    <%
        List<Ticket> tickets = (List<Ticket>) request.getAttribute("list");
    %>
    <table>
        <thead>
            <tr>
                <th><%=request.getParameter("date")%>تاریخ حرکت : </th>
                <th><%=request.getParameter("from")%> - <%=request.getParameter("to")%>مسیر : </th>
            </tr>
            <tr>
                <th>شناسه سفر</th>
                <th>ساعت حرکت</th>
                <th>انتخاب</th>
            </tr>
        </thead>
        <%
            for (Ticket ticket : tickets){
        %>
        <tbody>
            <tr>
                <th><%=ticket.getTravelNumber()%></th>
                <th><%=ticket.getTime()%></th>
                <th>
                    <form method="post" action="buy_ticket">
                        <input type="hidden" name="ticket_id" value="<%=ticket.getId()%>">
                        <button type="submit">خرید</button>
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
