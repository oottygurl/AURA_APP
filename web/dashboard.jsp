<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("user_id") %></h1>
    <a href="personal.jsp">Personal Growth</a><br>
    <a href="health.jsp">Health & Wellness</a><br>
    <a href="professional.jsp">Professional Goals</a><br>
    <a href="safety.jsp">Safety Network</a><br>
</body>
</html>