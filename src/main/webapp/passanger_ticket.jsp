<%@ page import="com.company.entity.Ticket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Ticket</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            margin: auto;
            text-align: center;
            margin-top: 20px;
        }
        .container {
            width: 500px;
        }
    </style>
</head>
<body>
    <div class="container">
        <table class="table table-striped">
            <%
                Ticket ticket = (Ticket) request.getAttribute("ticket");
            %>
            <tbody>
            <tr>
                <th>Ticket ID</th>
                <th><%=ticket.getId()%></th>
            </tr>
            <tr>
                <th>Username</th>
                <th><%=ticket.getPassanger().getUsername()%></th>
            </tr>
            <tr>
                <th>Gender</th>
                <th><%=ticket.getPassanger().getGender()%></th>
            </tr>
            <tr>
                <th>From</th>
                <th><%=ticket.getRoute().getFrom()%></th>
            </tr>
            <tr>
                <th>To</th>
                <th><%=ticket.getRoute().getTo()%></th>
            </tr>
            <tr>
                <th>Date</th>
                <th><%=ticket.getDate()%></th>
            </tr>
            <tr>
                <th>Time</th>
                <th><%=ticket.getTime()%></th>
            </tr>
            <tr>
                <th>Travel Number</th>
                <th><%=ticket.getTravelNumber()%></th>
            </tr>
            </tbody>
        </table>
        <form method="post" action="cancel_ticket">
            <input type="hidden" name="ticket_id" value="<%=ticket.getId()%>">
            <button type="submit">Cancel</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
