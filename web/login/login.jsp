<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        /* Style the background gradient */
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #ff99cc, #6699ff);
            font-family: 'Times New Roman', serif;
        }

        .login-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 320px;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
            font-weight: bold;
            position: relative;
        }

        h1::after {
            content: '';
            width: 40px;
            height: 4px;
            background-color: #ba68c8;
            display: block;
            margin: 0 auto;
            margin-top: 5px;
            border-radius: 2px;
        }

        input[type="text"],
        input[type="password"] {
            font-size: 16px;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-bottom: 2px solid #ba68c8;
            width: 100%;
            outline: none;
            background: transparent;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            font-size: 14px;
            color: #888;
        }

        input[type="checkbox"] {
            margin-right: 5px;
        }

        input[type="submit"] {
            font-size: 18px;
            color: white;
            background: linear-gradient(90deg, #ba68c8, #ff4081);
            border: none;
            padding: 10px;
            border-radius: 50px;
            cursor: pointer;
            width: 100%;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background: linear-gradient(90deg, #ff4081, #ba68c8);
        }

        .forgot-password,
        .register {
            font-size: 14px;
            margin-top: 10px;
            color: #888;
        }

        .forgot-password a,
        .register a {
            text-decoration: none;
            color: #ba68c8;
        }

        /* Style for the Show Password checkbox */
        .show-password {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            font-size: 14px;
            margin: 10px 0;
        }
    </style>
    <script>
        function togglePasswordVisibility() {
            var passwordField = document.getElementById("password");
            if (passwordField.type === "password") {
                passwordField.type = "text";
            } else {
                passwordField.type = "password";
            }
        }
    </script>
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>
        <form action="login" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>

            <div class="show-password">
                <input type="checkbox" id="showPassword" onclick="togglePasswordVisibility()">
                <label for="showPassword">Show Password</label>
            </div>
            
            <div class="forgot-password">
                <a href="#">Forgot Password?</a>
            </div>

            <input type="submit" value="Login">
        </form>
        
        <div class="register">
            <p>Don't have an Account? <a href="#">Register here</a></p>
        </div>
    </div>
</body>
</html>
