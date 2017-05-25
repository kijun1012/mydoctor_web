<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
					<a href="${pageContext.request.contextPath}/heartrate"
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
					<a href="${pageContext.request.contextPath}/bloodPressure"
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
					<a href="${pageContext.request.contextPath}/stepcount"
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
					<a href="${pageContext.request.contextPath}/bloodSugar"
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
					<a href="${pageContext.request.contextPath}/sleepingtime"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-purple">
					<div class="inner">
						<h3>${weight}</h3>

						<p>체중</p>
					</div>
					<div class="icon">
						<i class="ion-ios-body"></i>
					</div>
					<a href="${pageContext.request.contextPath}/weight"
						class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
		</div>

		<div class="row">
			<div class="col-md-6">

				<h4>위험 지수</h4>

				<div class="row">
					<div class="col-md-12">
						<div class="info-box">
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">고혈압 위험</span>
								<c:if test="${BPpoint == '4'}">
									<span class="info-box-number" style="color: green">정상</span>
								</c:if>
								<c:if test="${BPpoint == '3-1'}">
									<span class="info-box-number" style="color: yello">고혈압
										전단계 1기</span>
								</c:if>
								<c:if test="${BPpoint == '3-2'}">
									<span class="info-box-number" style="color: yello">고혈압
										전단계 2기</span>
								</c:if>
								<c:if test="${BPpoint == '2-1'}">
									<span class="info-box-number" style="color: orange">고혈압
										1기</span>
								</c:if>
								<c:if test="${BPpoint == '2-2'}">
									<span class="info-box-number" style="color: orange">고혈압
										2기</span>
								</c:if>
								<c:if test="${BPpoint == '1'}">
									<span class="info-box-number" style="color: red">수축기 단독
										고혈압(위험)</span>
								</c:if>
							</div>
							<!-- /.info-box-content -->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<!-- small box -->
						<div class="info-box">
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">제2형 당뇨병</span> <span
									class="info-box-number" style="color: green">보통</span>
							</div>
							<!-- /.info-box-content -->
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">
						<!-- small box -->
						<div class="info-box">
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">BMI 지수</span> <span
									class="info-box-number"> <c:if test="${height != null }">
										<fmt:formatNumber var="bmi"
											value="${weight/((height/100)*(height/100))}" pattern=".00" />
										${bmi }
									</c:if>
								</span>
							</div>
							<!-- /.info-box-content -->
						</div>
					</div>
				</div>

				<div class="box box-solid">
					<div class="box-header with-border">
						<h3 class="box-title">참고 사항</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="box-group" id="accordion">
							<!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
							<div class="panel box box-primary">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne" aria-expanded="true" class="collapsed">
											My Doctor가 처음이세요? </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse"
									aria-expanded="true" style="height: 0px;">
									<div class="box-body">
										<h4>
											<b>My Doctor의 오신 것을 환영합니다.</b>
										</h4>
										</br> 1. 먼저 안드로이드 App <b>MyDoctor</b>를 설치하십시오.</br> 2. App으로 측정한 당신의
										Data에 따른 서비스를 이곳에서 체험 할 수 있습니다.</br> 3. 당신의 건강 Data를 확인하고 건강 관리를
										시작하십시오.</br> 4. <b>데이터 분석결과</b>와 <b>위험 지수</b>를 확인하세요!!</br> 5. 당신의 더 나은
										건강 관리를 위해 <b>담당 의사</b>를 선택하세요!!
									</div>
								</div>
							</div>
							<div class="panel box box-danger">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseTwo" class="" aria-expanded="true"> 데이터 분석
											결과에 대한 가이드 </a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse"
									aria-expanded="true">
									<div class="box-body">
										<b>당신의 건강 Data의 따라서 위험지수와 데이터 분석결과가 일치하지 않을 수 있습니다.</b></br> </br> 위험지수는
										단순한 건강지표별 계산법에 따른 경고입니다.</br> 하지만 데이터 분석 결과는 건강보험공단에서 제공하는 표본Data </br>
										<b>'2013~2014년 일반검진 및 생애전환기 건강검진 데이터 19,451,956건'</b>을 이용하여
										만들어진 기계 학습 모델을 통해 정확도 92%정도로 예측된 결과입니다. </br> </br> 데이터 분석은 당신의 <b>나이,
											성별, 수축기혈압, 이완기혈압, 혈당, BMI지수</b> 지표를 이용하여 분석됩니다. 만약 당신의 건강의 대하여
										위험지수나 데이터 분석결과가 위험하다고 알리고 있다면 꼭 담당 의사와 상담 하기를 바랍니다.

									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
			</div>

			<div class="col-md-6">
				<br /> <br />
				<div class="box box-success direct-chat direct-chat-success">
					<div class="box-header with-border">
						<h3 class="box-title">담당의사 조언 및 데이터 분석 결과</h3>


					</div>
					<!-- /.box-header -->
					<div class="box-body" style="height: 425px;">
						<!-- Conversations are loaded here -->
						<div class="direct-chat-messages" style="height: 420px;">

							<c:if test="${analysisData.dis != null }">
								<div class="direct-chat-msg">

									<!-- /.direct-chat-info -->
									<img class="direct-chat-img"
										src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
										alt="Message User Image">
									<!-- /.direct-chat-img -->

									<c:if test="${analysisData.dis == '1' }">
										<div class="direct-chat-text">
											<p class="text-red">
												<b>데이터 분석 결과 당신은 고혈압과 당뇨가 의심됩니다. 혈압과,혈당 모두 관리가 필요합니다.</br>담당의사와
													상담하세요.
												</b>
											</p>
										</div>
									</c:if>
									<c:if test="${analysisData.dis == '2' }">
										<div class="direct-chat-text">
											<p class="text-yellow">
												<b>데이터 분석 결과 당신은 고혈압이 의심됩니다. 혈압관리가 필요합니다.</br>담당의사와 상담하세요.
												</b>
											</p>
										</div>
									</c:if>
									<c:if test="${analysisData.dis == '3' }">
										<div class="direct-chat-text">
											<p class="text-yellow">
												<b>데이터 분석 결과 당신은 당뇨가 의심됩니다. 혈당관리가 필요합니다.</br>담당의사와 상담하세요.
												</b>
											</p>
										</div>
									</c:if>
									<c:if test="${analysisData.dis == '4' }">
										<div class="direct-chat-text">
											<p class="text-green">
												<b>데이터 분석 결과 당신은 건강합니다.</b>
											</p>
										</div>
									</c:if>

									<!-- /.direct-chat-text -->
								</div>
							</c:if>
							<!-- Message. Default to the left -->
							<c:forEach var="advice" items="${advices}">
								<div class="direct-chat-msg">

									<!-- /.direct-chat-info -->
									<img class="direct-chat-img"
										src="${pageContext.request.contextPath}/resources/dist/img/doctor.jpg"
										alt="Message User Image">
									<!-- /.direct-chat-img -->
									<div class="direct-chat-text">${advice.advice}</div>
									<!-- /.direct-chat-text -->
								</div>
							</c:forEach>




						</div>
					</div>
					<!-- /.box-body -->

					<!-- /.box-footer-->
				</div>
				<!-- /.box -->
			</div>
		</div>

	</section>
	<!-- /.content -->
</div>


<!-- /.content-wrapper -->
