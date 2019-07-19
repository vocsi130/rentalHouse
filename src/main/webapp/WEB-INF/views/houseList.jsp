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
        <span style="font-size: 1.5em">List of Houses</span>
        <span style="float:right;">
                <a class="btn btn-primary" href="addHouse">New House</a>
                     	<a href="pdfHouse" class="btn btn-light">Print </a>
	</span>
    </div> 
  <!--    <div style="float: right;">

				<a href="/addHouse" class="btn btn-outline-warning"
					style="margin-right: 2em;">Add</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Print
					</button>
			</div>-->
    
   
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Build Date</th>
            <th scope="col">Square</th>
            <th scope="col">Street</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Zip</th>
            <th scope="col">Lot Size</th>
            <th scope="col">Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="house" items="${houses}">
            <tr>
                <td>${house.builtDate}</td>
                <td>${house.squareFt}</td>
                <td>${house.address.street}</td>
                <td>${house.address.city}</td>
                <td>${house.address.state}</td>
                <td>${house.address.zip}</td>
                <td>${house.lotSize}</td>
                 <td>
                <a	href="houses/ + ${house.id}">
				<i class="fa fa-edit" style="font-size: 17px; color: blue;"></i>
				</a>
				
                <a	href="houses/delete/ + ${house.id}">&nbsp; &nbsp; &nbsp;
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