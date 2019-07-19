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
        <span style="font-size: 1.5em">List of Apartments</span>
        <span style="float:right;">
                <a class="btn btn-primary" href="addApartment">New Apartement</a>
                	<a href="pdfApartment" class="btn btn-light">Print </a>
		</span>
    </div> 
 
   
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Build Date</th>
            <th scope="col">Square</th>
            <th scope="col">Street</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Zip</th>
            <th scope="col">Floor</th>
            <th scope="col">Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="apartment" items="${apartments}">
            <tr>
                <td>${apartment.builtDate}</td>
                <td>${apartment.squareFt}</td>
                <td>${apartment.address.street}</td>
                <td>${apartment.address.city}</td>
                <td>${apartment.address.state}</td>
                <td>${apartment.address.zip}</td>
                <td>${apartment.floor}</td>
                   <td>
                <a	href="apartments/ + ${apartment.id}">
				<i class="fa fa-edit" style="font-size: 17px; color: blue;"></i>
				</a>
				
                <a	href="apartments/delete/ + ${apartment.id}">&nbsp; &nbsp; &nbsp;
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