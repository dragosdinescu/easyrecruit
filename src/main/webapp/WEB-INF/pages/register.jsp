<%-- 
    Document   : login
    Created on : Dec 25, 2020, 11:08:29 AM
    Author     : M Radu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Sign up">
    <html>
        <head>
            <c:if test="${register_error_message != null}">
            <div class="alert alert-warning" role="alert">
                ${register_error_message}
            </div>
        </c:if>
        <meta charset="utf-8">
        <link rel="stylesheet" href="../css/registerstyle.css">
        </head>
        <body>
            <div class="wrapper">
                <div class="title"> Register </div>
                <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Users/Register"
                      oninput='retypepassword.setCustomValidity(retypepassword.value != password.value ? "Passwords do not match." : "")'>
                    <div class="field">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" value="" required>
                        <div class="invalid-feedback">
                            Username is required.
                        </div>
                    </div>
                    <div class="field">
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name" value="" required>
                        <div class="invalid-feedback">
                            First name is required.
                        </div>
                    </div>
                    <div class="field">
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" value="" required>
                        <div class="invalid-feedback">
                            Last name is required.
                        </div>
                    </div>
                    <div class="field">
                        <input type="text" pattern="^\+(?:[0-9] ?){6,14}[0-9]$" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" value="" required>
                        <div class="invalid-feedback">
                            Phone number is required.
                        </div> 
                    </div>
                    <div class="field">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email address" value="" required>
                        <div class="invalid-feedback">
                            Email is required.
                        </div>
                    </div>
                    <div class="field">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="" required>
                        <div class="invalid-feedback">
                            Password is required.
                        </div>
                    </div>
                    <div class="field">
                        <input type="password" class="form-control" id="retypepassword" name="retypepassword" placeholder="Confirm password" value="" required>
                        <div class="invalid-feedback">
                            Please confirm the password
                        </div>
                    </div>
                    <div class="field">
                        <input type="submit" value="Register">
                    </div>
<!--                    <div class="signup-link">
                        Already a member? <a href="/WEB-INF/pages/login.jsp"">Log in</a> <%-- To do for later (if time allows) --%>
                    </div>-->
                </form>
            </div>
            <script>
                // Example starter JavaScript for disabling form submissions if there are invalid fields
                (function () {
                    'use strict'

                    window.addEventListener('load', function () {
                        // Fetch all the forms we want to apply custom Bootstrap validation styles to
                        var forms = document.getElementsByClassName('needs-validation')

                        // Loop over them and prevent submission
                        Array.prototype.filter.call(forms, function (form) {
                            form.addEventListener('submit', function (event) {
                                if (form.checkValidity() === false) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }

                                form.classList.add('was-validated')
                            }, false)
                        })
                    }, false)
                })()
            </script>
        </body>
    </html>
</t:pageTemplate>




