<%-- 
    Document   : create
    Created on : Oct 16, 2024, 4:45:34 PM
    Author     : sonnt-local
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Plan</title>

        <!-- Internal CSS for Styling -->
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #f3a4b5, #8e88f3);
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            .container {
                background-color: #ffffff;
                padding: 30px;
                width: 90%;
                max-width: 500px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
                border-radius: 15px;
                text-align: center;
            }
            h2 {
                color: #333;
                margin-bottom: 20px;
                position: relative;
            }
            h2::after {
                content: "";
                width: 60px;
                height: 3px;
                background: #8e88f3;
                position: absolute;
                left: 50%;
                bottom: -10px;
                transform: translateX(-50%);
            }
            label {
                font-weight: bold;
                margin-top: 15px;
                display: block;
                text-align: left;
            }
            input[type="text"], input[type="date"], select {
                width: calc(100% - 20px);
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }
            table {
                width: 100%;
                margin: 20px 0;
                border-collapse: collapse;
            }
            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #8e88f3;
                color: white;
            }
            input[type="submit"] {
                background: linear-gradient(90deg, #f76b8a, #ff508b);
                color: white;
                padding: 15px;
                border: none;
                border-radius: 25px;
                cursor: pointer;
                width: 100%;
                font-size: 16px;
                transition: background-color 0.3s;
            }
            input[type="submit"]:hover {
                background: linear-gradient(90deg, #ff508b, #f76b8a);
            }
            .form-group {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Create a New Plan</h2>

            <!-- Form for creating a new plan -->
            <form action="create" method="POST">

                <div class="form-group">
                    <label for="name">Plan Title:</label>
                    <input type="text" id="name" name="name" placeholder="Enter plan title" required/>
                </div>

                <div class="form-group">
                    <label for="from">From:</label>
                    <input type="date" id="from" name="from" required/>
                </div>

                <div class="form-group">
                    <label for="to">To:</label>
                    <input type="date" id="to" name="to" required/>
                </div>

                <div class="form-group">
                    <label for="did">Workshop:</label>
                    <select id="did" name="did" required>
                        <option value="" disabled selected>Select a workshop</option>
                        <c:forEach items="${requestScope.depts}" var="d">
                            <option value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Table for products and their details -->
                <table>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Cost</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.products}" var="p">
                            <tr>
                                <td>
                                    ${p.name}
                                    <input type="hidden" value="${p.id}" name="pid"/>
                                </td>
                                <td><input type="text" name="quantity${p.id}" placeholder="Enter quantity"/></td>
                                <td><input type="text" name="cost${p.id}" placeholder="Enter cost"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Submit button -->
                <input type="submit" value="Save Plan"/>

            </form>
        </div>

    </body>
</html>
