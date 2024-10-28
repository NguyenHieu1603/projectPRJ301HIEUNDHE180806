<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List of Plans</title>

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
                max-width: 1000px;
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
            table {
                width: 100%;
                margin: 20px 0;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                overflow: hidden;
            }
            th, td {
                padding: 20px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #8e88f3;
                color: white;
            }
            tr:hover {
                background-color: #f5f5f5;
            }
            button {
                background: linear-gradient(90deg, #f76b8a, #ff508b);
                color: white;
                padding: 15px;
                border: none;
                border-radius: 25px;
                cursor: pointer;
                width: 200px;
                font-size: 16px;
                transition: background-color 0.3s;
                margin-top: 20px;
            }
            button:hover {
                background: linear-gradient(90deg, #ff508b, #f76b8a);
            }
            td button {
                background-color: #007BFF;
                color: white;
                padding: 10px 20px;
                margin: 0;
                border: none;
                border-radius: 3px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            td button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>List of Plans</h2>  

            <!-- Table for List of Plans -->
            <table>
                <thead>
                    <tr>
                        <th>Department Name</th>
                        <th>Plan Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.p}" var="plan">
                        <tr>
                            <td>${plan.dept.name}</td>
                            <td>${plan.name}</td>
                            <td>${plan.start}</td>
                            <td>${plan.end}</td>
                            <td><button onclick="location.href='/AssignmentPRJ301.HieuNDHE180806_1/plan?id=${plan.id}';">View Detail</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Button to Create a New Plan -->
            <button onclick="location.href='/AssignmentPRJ301.HieuNDHE180806_1/plan/create';">Create New Plan</button>
        </div>
        
    </body>
</html>
