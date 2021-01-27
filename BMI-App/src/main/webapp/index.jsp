<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>BMI Calculator</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>

<div class="container container-table">
    <form action="calculate-bmi" method="post" style="max-width: 500px; margin: auto;">
        <h1>BMI Calculator</h1>
        <br/>
        <div class="form-group">
            <label>What is your name? </label>
            <input class="form-control" name="name" type="text" placeholder="Please Enter Name Here..." required
            ></div>
        <div class="form-group">
            <label>What is your Height? (in cm) </label>
            <input class="form-control" name="height" type="number" min="0" value="0" step=".01"
                   placeholder="Please Enter Height Here..." required>
        </div>
        <div class="form-group">
            <label>What is your Weight? (in kg)? </label>
            <input class="form-control" name="weight" type="number" min="0" value="0" step=".01"
                   placeholder="Please Enter Weight Here..." required>
        </div>
        <div class="form-group" style="float: right">
            <input type='submit' class='btn btn-primary' value="Calculate"/>
        </div>
    </form>
</div>
</body>
</html>
