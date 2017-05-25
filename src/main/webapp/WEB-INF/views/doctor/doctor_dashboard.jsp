<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			My Doctor <small>Dash Board</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i>DashBoard</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">



		<div class="row">
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>
							<c:if test="${heartRate == null}">
							-
						</c:if>
							<c:if test="${heartRate != null}">
							${heartRate.heartRate}
						</c:if>
						</h3>

						<p>심박수</p>
					</div>
					<div class="icon">
						<i class="ion-ios-pulse-strong"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/heartrate"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-green">

					<div class="inner">
						<h3>
							<c:if test="${bloodPressure == null }">
							-/-
						</c:if>
							<c:if test="${bloodPressure != null }">
							${bloodPressure.HR}/${bloodPressure.HP}
						</c:if>
						</h3>

						<p>혈압</p>
					</div>
					<div class="icon">
						<i class="ion-android-favorite"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/bloodPressure"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-maroon">
					<div class="inner">
						<h3>
							<c:if test="${stepCount == null }">
							-
						</c:if>
							<c:if test="${stepCount != null }">
							${stepCount.stepCount}
						</c:if>
						</h3>

						<p>걸음수</p>
					</div>
					<div class="icon">
						<i class="ion-android-walk"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/stepcount"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>
							<c:if test="${bloodSugar == null }">
							-
						</c:if>
							<c:if test="${bloodSugar != null }">
							${bloodSugar.BG}
						</c:if>
						</h3>

						<p>혈당</p>
					</div>
					<div class="icon">
						<i class="ion-waterdrop"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/bloodSugar"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner">
						<h3>
							<c:if test="${sleepingTime == null }">
							-
						</c:if>
							<c:if test="${sleepingTime != null }">
							${sleepingTime.sleepingTime}
						</c:if>
						</h3>

						<p>수면</p>
					</div>
					<div class="icon">
						<i class="ion-ios-alarm"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/sleepingtime"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-purple">
					<div class="inner">
						<h3>
							<c:if test="${weight == null }">
							-
						</c:if>
							<c:if test="${weight != null }">
							${weight.weightValue}
						</c:if>
						</h3>

						<p>체중</p>
					</div>
					<div class="icon">
						<i class="ion-ios-body"></i>
					</div>
					<a href="${pageContext.request.contextPath}/doctor/weight"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
		</div>


		<div class="row">
			<div class="col-md-6">
				<h4>환자 선택</h4>
				<div class="btn-group">
					<button type="button" class="btn btn-default">환자 선택</button>
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Drop down</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<c:forEach var="assignedUser" items="${userList}">
							<li><a href="?username=${assignedUser.username}">${assignedUser.username}</a></li>
						</c:forEach>
					</ul>
				</div>
				<c:if test="${not empty selectUsername}">
					${selectUsername}
				</c:if>
			</div>

			<c:if test="${not empty selectUsername}">

				<div class="col-md-6">
					<h4>조언을 입력하세요</h4>
					<sf:form action="${pageContext.request.contextPath}/doctor/advice"
						method="post" modelAttribute="advice" name="adviceForm"
						onsubmit="return frmsubmit();">

						<input type="hidden" name="doctorname"
							value="${advice.doctorname}" id="doctorname" />

						<input type="hidden" name="username" value="${advice.username}"
							id="username" />

						<div class="input-group margin">
							<sf:input path="advice" id="advice" name="advice"
								class="form-control" />

							<span class="input-group-btn">
								<button type="submit" value="Submit"
									class="btn btn-info btn-flat" onclick="chkValue()">Go!</button>
							</span>
						</div>
					</sf:form>
				</div>

			</c:if>
		</div>
	</section>
	<!-- /.content -->
</div>

<script type="text/javascript">
	function chkValue() {
	}

	function frmsubmit() {
		var tmp = document.adviceForm.advice.value.replace(/\s|　/gi, '');
		// 정규식으로 공백, 엔터, 탭, 특수문자 공백 문자를 빈문자로 바꿈
		// 입력된 값에 대하여 위 정규식 처리를 하고 뭔가 남아있지 않다면
		// 값이 무의미 하다고 판단함.

		if (tmp == '') {
			alert('내용을 입력해 주세요.');
			return false;
		} else {
			alert('${advice.username}님에게 조언이 입력되었습니다.');
			return true;
		}
	}
</script>