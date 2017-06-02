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
								<c:if test="${BPpoint == '0'}">
									<span class="info-box-number" style="color: black">데이터
										없음</span>
								</c:if>
								<c:if test="${BPpoint == '4'}">
									<span class="info-box-number" style="color: green">정상</span>
								</c:if>
								<c:if test="${BPpoint == '3-1'}">
									<span class="info-box-number" style="color: orange">고혈압
										전단계 1기</span>
								</c:if>
								<c:if test="${BPpoint == '3-2'}">
									<span class="info-box-number" style="color: orange">고혈압
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
								<span class="info-box-text">제2형 당뇨병</span>
								<c:if test="${BSpoint == '0'}">
									<span class="info-box-number" style="color: black">데이터
										없음</span>
								</c:if>
								<c:if test="${BSpoint == '1'}">
									<span class="info-box-number" style="color: green">정상</span>
								</c:if>
								<c:if test="${BSpoint == '2'}">
									<span class="info-box-number" style="color: orange">주의</span>
								</c:if>
								<c:if test="${BSpoint == '3'}">
									<span class="info-box-number" style="color: red">위험</span>
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

							<div class="panel box box-danger">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseThree" class="" aria-expanded="true">고혈압에
											대하여</a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse"
									aria-expanded="true">
									<div class="box-body">
										운동 능력이 많은 사람의 경우 수축기 혈압과 이완기 혈압 모두 평균보다 낮은 것으로 알려져 있습니다. 체중과
										키, 연령을 추가하여 비교할 때에도 운동능력이 평균보다 높은 사람들이 평균혈압보다 낮게 나타납니다.</br> 8년간
										추적연구결과 운동하는 사람이 안 하는 사람보다 고혈압 발생 위험이 <b>52%</b>나 낮게 나타났다는 보고도
										있습니다.</br> </br> <b>경증(1기) 고혈압</b>에서 효과가 가장크며, <b>2기 고혈압</b>에서도 어느 정도
										효과가 있습니다.</br> 하지만 <b>3기 중증 고혈압</b>에서는 운동과 혈압의 영향에 대한 자료가 거의 없으며,
										실제로도 혈압 감소 효과는 거의 없는 것으로 추측됩니다.</br> </br> 일반적으로 <b>고혈압 환자</b>들에게는 우리 몸
										전체의 관절과 근육들을 상당시간 동안 움직이는 <b>유산소 운동이 좋습니다.</b> 걷기, 조깅, 자전거타기,
										수영, 체조, 줄넘기 등이 유산소 운동에 속합니다. 하지만 무거운 것을 드는 운동은 일시적으로 혈압을 상승 시킬
										수 있기 때문에 주의해야 합니다. </br> </br> <b>운동을 시작하여 혈압을 낮추고 심폐기능을 개선하여 체중을
											낮추세요!!</b></br> <b>혈중 콜레스테롤 수치</b>를 개선하고 <b>스트레스 해소</b> 등을 통해 고혈압 환자에게
										매우 효과적입니다!! <b>일주일 3~5회</b> 정도 규칙적인 운동을 하여 처음 운동을 할 때에는 <b>10~20분</b>
										정도로 천천히 운동량을 늘려서 <b>30~50회</b> 정도 실시하는 것이 적당합니다. </br> </br> <b>하지만
											자신의 몸상태에 맞는 운동을 하는 것이 중요하기 때문에 담당의사와 상담하세요!!</b>

									</div>
								</div>
							</div>

							<div class="panel box box-danger">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseFour" class="" aria-expanded="true">당뇨에
											대하여</a>
									</h4>
								</div>
								<div id="collapseFour" class="panel-collapse collapse"
									aria-expanded="true">
									<div class="box-body">
										당뇨 환자의 경우에는 인슐린의 분비 부족과 함께 인슐린 저항성이 문제가 되기 때문에 운동을 통해서 인슐린
										저항성을 개선하고 체중을 조절하면 <b>혈당을 낮추는데 많은 도움이 됩니다.</b> <br /> <br />
										<b>일주일에 4회 이상</b> 규칙적으로 운동하는 당뇨 환자는 약 30%에 불과합니다. 운동을 하면 <b>혈당관리</b>뿐만
										아니라 <b>당뇨병성 합병증</b>을 유발하는 여러 위험요인들을 개선시킵니다. 그러므로 의사와 상담에서 특별한
										금기사항이 없는 한 규칙적인 운동이 반드시 병행되어야 합니다. <br /> <br /> <b>당뇨인에게
											운동은 선택이 아니라 필수입니다!!</b>

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
												<b>데이터 분석 결과 당신은 고혈압과 당뇨가 의심됩니다.</br> 혈압과,혈당 모두 관리가 필요합니다.</br> 왼쪽
													하단에 참고사항을 확인해주세요</br>담당의사와 상담하세요.
												</b>
											</p>
										</div>
										<c:if test="${SCpoint == '1'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압과 당뇨 같은 만성질환에는 걷기운동 같은 유산소운동이 좋습니다.</br> 하지만 당신은 하루에
														5000보 이하로 너무 적게 걷고 있습니다. </br> 당신의 건강을 위해 밖으로 나가세요.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '2'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압과 당뇨 같은 만성질환에는 걷기운동 같은 유산소운동이 좋습니다.</br> 당신은 활동적이네요. </br>
														지금처럼 꾸준히 걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '3'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압과 당뇨 같은 만성질환에는 걷기운동 같은 유산소운동이 좋습니다.</br> 당신은 활동적이네요. </br>
														지금처럼 꾸준히 걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '4'}">
											<div class="direct-chat-text">
												<p class="text-green">
													<b>고혈압과 당뇨 같은 만성질환에는 걷기운동 같은 유산소운동이 좋습니다. </br>당신은 매우
														활동적이군요. </br>만약에 몸에 무리가 있다면 운동 또는 걷기의 강도를 조금 낮추는 것이 좋습니다.
													</b>
												</p>
											</div>
										</c:if>

									</c:if>

									<c:if test="${analysisData.dis == '2' }">
										<div class="direct-chat-text">
											<p class="text-yellow">
												<b>데이터 분석 결과 당신은 고혈압이 의심됩니다. 혈압관리가 필요합니다.</br> 왼쪽 하단에 참고사항을
													확인해주세요</br>담당의사와 상담하세요.
												</b>
											</p>
										</div>

										<c:if test="${SCpoint == '1'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. </br>하지만 당신은 하루에 5000보 이하로 너무
														적게 걷고 있습니다. </br> 당신의 건강을 위해 밖으로 나가세요.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '2'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히
														걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '3'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히
														걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '4'}">
											<div class="direct-chat-text">
												<p class="text-green">
													<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 매우 활동적이군요. </br>만약에 몸에
														무리가 있다면 운동 또는 걷기의 강도를 조금 낮추는 것이 좋습니다.
													</b>
												</p>
											</div>
										</c:if>

									</c:if>

									<c:if test="${analysisData.dis == '3' }">
										<div class="direct-chat-text">
											<p class="text-yellow">
												<b>데이터 분석 결과 당신은 당뇨가 의심됩니다. 혈당관리가 필요합니다.</br> 왼쪽 하단에 참고사항을
													확인해주세요</br>담당의사와 상담하세요.
												</b>
											</p>
										</div>

										<c:if test="${SCpoint == '1'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다.</br> 하지만 당신은 하루에 5000보 이하로 너무
														적게 걷고 있습니다. </br> 당신의 건강을 위해 밖으로 나가세요.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '2'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히
														걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>
										<c:if test="${SCpoint == '3'}">
											<div class="direct-chat-text">
												<p class="text-yellow">
													<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히
														걷는다면 당신의 건강 관리에 큰 도움이 될것입니다.
													</b>
												</p>
											</div>
										</c:if>



										<c:if test="${SCpoint == '4'}">
											<div class="direct-chat-text">
												<p class="text-green">
													<b>당신은 매우 활동적이군요. </br>만약에 몸에 무리가 있다면 운동 또는 걷기의 강도를 조금 낮추는
														것이 좋습니다.
													</b>
												</p>
											</div>
										</c:if>
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
									<div class="direct-chat-text">${advice.advice}

										<div class="pull-right">
											<a href="${pageContext.request.contextPath}/advice/delete/${advice.id}">
											 <i class="glyphicon glyphicon-remove"></i>
											</a>

										</div>

									</div>
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
