<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<div class="row">
		<div class="col-md-6">
			<div class="container">
				<h4>
					<c:if test="${assignedUser.doctorname == null}">
							의사를 선택해주세요.
						</c:if>

					<c:if test="${assignedUser.doctorname != null}">
							${assignedUser.doctorname} 님이 선택되어 있습니다.
							</br>
						<small>(재 입력시 변경. 공백 입력시 선택 해제 됩니다.)</small>
					</c:if>

					<c:if test="${not empty error}">
						<div style="color: #ff0000">
							<h6>${error}</h6>
						</div>
					</c:if>
				</h4>
			</div>



			<sf:form action="${pageContext.request.contextPath}/chooseDoctor"
				method="post" modelAttribute="assignedUser">

				<input type="hidden" name="username"
					value="${assignedUser.username}" id="username" />

				<div class="input-group margin">
					<sf:input path="doctorname" id="doctorname" class="form-control" />
					<span class="input-group-btn">
						<button type="submit" value="Submit" class="btn btn-info btn-flat">선택</button>
					</span>
				</div>
			</sf:form>
		</div>
	</div>
</div>