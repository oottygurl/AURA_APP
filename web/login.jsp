<%@page import="java.sql.*"%>
<%
String act = request.getParameter("action");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/watch?useSSL=false", "root", "root");
Statement st = con.createStatement();
if(act.equals("Login")){
    ResultSet rs=st.executeQuery(String.format("select * from brands where email='%s' and password='%s'",request.getParameter("email"),request.getParameter("password")));
    if(rs.next()){
        session.setAttribute("username",rs.getString("username"));
        out.print("<script>sessionStorage.setItem('username','"+rs.getString("username")+"');setTimeout(function() { document.location = 'bdash.jsp'; }, 100);</script>");
    }
    else out.print("<script>alert('wrong username or password')</script>");
    out.print("<a href='brandlog.html'>Log in</a>");
}
else if(act.equals("Signup")){
    String password=request.getParameter("password");
    String confpass=request.getParameter("confpass");
    try{
        if(confpass.equals(password)){
            st.executeUpdate(String.format("insert into brands values('%s','%s','%s')",request.getParameter("email"),request.getParameter("username"),password));
            out.print("Brand created, please <a href='brandlog.html'>login</a>");
            
        }
        else out.print("<script>alert('passwords do not match')</script>");
    }
    catch(Exception e){
        throw e;
        //out.print("<script>alert('Error creating, brand already exists')</script>");
    }
}
%>