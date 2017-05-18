<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<div class="row">
		<div class="col-md-6">
			<div class="container">
				<h4>조언을 입력하세요</h4>
			</div>
			<sf:form action="${pageContext.request.contextPath}/doctor/advice"
				method="post" modelAttribute="advice">

				<input type="hidden" name="doctorname" value="${advice.doctorname}"
					id="doctorname" />

				<input type="hidden" name="username" value="${advice.username}"
					id="username" />

				<div class="input-group margin">
					<sf:input path="advice" id="advice" class="form-control" />
					<span class="input-group-btn">
						<button type="submit" value="Submit" class="btn btn-info btn-flat">Go!</button>
					</span>
				</div>
			</sf:form>
		</div>
	</div>
</div>