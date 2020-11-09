<%@ page import="com.company.entity.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passanger Ticket List</title>
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
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Ticket ID</th>
                    <th>Choose</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Ticket> tickets = (List<Ticket>) request.getAttribute("list");
                for (Ticket ticket : tickets) {
            %>
                <tr>
                    <th><%=ticket.getDate()%></th>
                    <th><%=ticket.getId()%></th>
                    <th>
                        <form method="get" action="your_ticket">
                            <input type="hidden" name="ticket_id" value="<%=ticket.getId()%>">
                            <button type="submit">See Info</button>
                        </form>
                    </th>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
