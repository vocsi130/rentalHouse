<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head><link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
	<link rel="stylesheet" type="text/css" href="css/register.css">

</head>

<body>
<div class="container">
	<div class="row">
	<div class="modal-content">

    					<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
							<h4 class="modal-title">Ready to Join? Create a New Account</h4>
						</div>

						<form:form modelAttribute="user" action="register" method="post">
						
							<div class="modal-body">
								<div class="row">
									<div class="col-md-6">
								<div class="form-group">
									<label for="userEmail">Email Address</label>
									<form:input path="email" type="text" class="form-control" required="" name="email" value=""/>
									<span class="help-block">Your email address is also used to log in.</span>
								</div>
								</div>
								
									<div class="col-md-6">
										<div class="form-group">
											<label for="password">User Name</label>
											<form:input path="name" type="text" class="form-control" required="" name="name" value=""/>
											<span class="help-block">Choose a user name for your new account.</span>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label for="password">Password</label>
											<form:input path="password" type="password" class="form-control" required="" name="password" value=""/>
											<span class="help-block">Choose a password for your new account.</span>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="passwordr">Repeat Password</label>
											<form:input path="password" type="password" class="form-control" required="" name="passwordr" value=""/>
											<span class="help-block">Type the password again. Passwords must match.</span>
										</div>
									</div>
								</div>
							</div>

							<div class="modal-footer">
								<input type="hidden" name="isEmpty" value="">
								<button type="input" name="submit" value="newAccount" class="btn btn-success btn-icon"><i class="fa fa-check"></i> Create My Account</button>
								<button type="button" class="btn btn-default btn-icon" data-dismiss="modal"><i class="fa fa-times-circle"></i> Cancel</button>
							</div>
						</form:form>

					</div>
	</div>
</div>
</body>
</html>