<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
										<b>고혈압과 당뇨 같은 만성질환에는 걷기운동 같은 유산소운동이 좋습니다. </br>당신은 매우 활동적이군요. </br>만약에
											몸에 무리가 있다면 운동 또는 걷기의 강도를 조금 낮추는 것이 좋습니다.
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
										<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. </br>하지만 당신은 하루에 5000보 이하로 너무 적게
											걷고 있습니다. </br> 당신의 건강을 위해 밖으로 나가세요.
										</b>
									</p>
								</div>
							</c:if>
							<c:if test="${SCpoint == '2'}">
								<div class="direct-chat-text">
									<p class="text-yellow">
										<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히 걷는다면
											당신의 건강 관리에 큰 도움이 될것입니다.
										</b>
									</p>
								</div>
							</c:if>
							<c:if test="${SCpoint == '3'}">
								<div class="direct-chat-text">
									<p class="text-yellow">
										<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히 걷는다면
											당신의 건강 관리에 큰 도움이 될것입니다.
										</b>
									</p>
								</div>
							</c:if>
							<c:if test="${SCpoint == '4'}">
								<div class="direct-chat-text">
									<p class="text-green">
										<b>고혈압에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 매우 활동적이군요. </br>만약에 몸에 무리가
											있다면 운동 또는 걷기의 강도를 조금 낮추는 것이 좋습니다.
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
										<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다.</br> 하지만 당신은 하루에 5000보 이하로 너무 적게
											걷고 있습니다. </br> 당신의 건강을 위해 밖으로 나가세요.
										</b>
									</p>
								</div>
							</c:if>
							<c:if test="${SCpoint == '2'}">
								<div class="direct-chat-text">
									<p class="text-yellow">
										<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히 걷는다면
											당신의 건강 관리에 큰 도움이 될것입니다.
										</b>
									</p>
								</div>
							</c:if>
							<c:if test="${SCpoint == '3'}">
								<div class="direct-chat-text">
									<p class="text-yellow">
										<b>당뇨에는 걷기 운동같은 유산소 운동이 좋습니다. 당신은 활동적이네요. </br> 지금처럼 꾸준히 걷는다면
											당신의 건강 관리에 큰 도움이 될것입니다.
										</b>
									</p>
								</div>
							</c:if>



							<c:if test="${SCpoint == '4'}">
								<div class="direct-chat-text">
									<p class="text-green">
										<b>당신은 매우 활동적이군요. </br>만약에 몸에 무리가 있다면 운동 또는 걷기의 강도를 조금 낮추는 것이
											좋습니다.
										</b>
									</p>
								</div>
							</c:if>
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






