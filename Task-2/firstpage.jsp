<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Student Form</title>
    </head>

    <body>

        <h2>Student Information</h2>

        <form action="result.jsp" method="post">

            Full Name:
            <input type="text" name="fullName">
            <br><br>

            Password:
            <input type="password" name="password">
            <br><br>

            Age:
            <input type="text" name="age">
            <br><br>

            Address (Radio Buttons):
            <input type="radio" name="radioAddress" value="Cairo"> Cairo
            <input type="radio" name="radioAddress" value="Alex"> Alex
            <input type="radio" name="radioAddress" value="Menofia"> Menofia
            <br><br>

            Address (Select):
            <select name="selectAddress">
                <option value="Cairo">Cairo</option>
                <option value="Alex">Alex</option>
                <option value="Menofia">Menofia</option>
            </select>
            <br><br>

            <input type="submit" value="Submit">

        </form>

    </body>

    </html>