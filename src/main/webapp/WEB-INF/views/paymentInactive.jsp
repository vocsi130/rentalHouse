<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<t:master>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
		          <div class="container">
    <div>
        <span style="font-size: 1.5em">List of Payments Inactive</span>
        <span style="float:right;">
                <a class="btn btn-primary" href="addPayment">New Payment</a>
            	<button id="btnSubmit" type="submit" class="btn btn-light">Print </button>
			</span>
    </div> 
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Date Payment</th>
            <th scope="col">Tenant Name</th>
            <th scope="col">Tenant FirstName</th>
            <th scope="col">Amount</th>
            <th scope="col">Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payment" items="${payments}">
            <tr>
                <td>${payment.payDate}</td>
                <td>${payment.tenant.firstName}</td>
                <td>${payment.tenant.lastName}</td>
                <td>${payment.amount}</td>
                   <td>
                <a	href="payments/ + ${payment.id}">
				<i class="fa fa-edit" style="font-size: 17px; color: blue;"></i>
				</a>
				
                <a	href="payments/delete/ + ${payment.id}">&nbsp; &nbsp; &nbsp;
				<i class="fa fa-trash" style="font-size: 17px; color: red;"></i>
				</a>
				</td> 
           </tr>
        </c:forEach>
        </tbody>
    </table>
    
</div>

    </jsp:body>
</t:master>