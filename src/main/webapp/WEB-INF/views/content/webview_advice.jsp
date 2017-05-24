<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-6">
	<div class="box box-success direct-chat direct-chat-success">
		<div class="box-header with-border">
			<h3 class="box-title">데이터 분석 결과</h3>


		</div>
		<!-- /.box-header -->
		<div class="box-body" style="min-height: 500px;">
			<!-- Conversations are loaded here -->
			<div class="direct-chat-messages">

				<!-- Message. Default to the left -->
				<c:if test="${analysisData.dis != null }">
					<div class="direct-chat-msg">

						<!-- /.direct-chat-info -->
						<img class="direct-chat-img"
							src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
							alt="Message User Image">
						<!-- /.direct-chat-img -->

						<c:if test="${analysisData.dis == '1' }">
							<div class="direct-chat-text">
								데이터 분석 결과 당신은 고혈압과 당뇨가 의심됩니다. 혈압과,혈당 모두 관리가 필요합니다.</br>담당의사와 상담하세요.
							</div>
						</c:if>
						<c:if test="${analysisData.dis == '2' }">
							<div class="direct-chat-text">
								데이터 분석 결과 당신은 고혈압이 의심됩니다. 혈압관리가 필요합니다.</br>담당의사와 상담하세요.
							</div>
						</c:if>
						<c:if test="${analysisData.dis == '3' }">
							<div class="direct-chat-text">
								데이터 분석 결과 당신은 당뇨가 의심됩니다. 혈당관리가 필요합니다.</br>담당의사와 상담하세요.
							</div>
						</c:if>
						<c:if test="${analysisData.dis == '4' }">
							<div class="direct-chat-text">데이터 분석 결과 당신은 건강합니다.</div>
						</c:if>

						<!-- /.direct-chat-text -->
					</div>
				</c:if>




			</div>


		</div>
		<!-- /.box-body -->

		<!-- /.box-footer-->
	</div>
	<!-- /.box -->
</div>