<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-6">
	<div class="box box-success direct-chat direct-chat-success">
		<div class="box-header with-border">
			<h3 class="box-title">담당의사 조언 및 데이터 분석 결과</h3>


		</div>
		<!-- /.box-header -->
		<div class="box-body" style="min-height: 500px;">
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