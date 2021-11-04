<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List,entity.MySchedule,java.util.Objects,java.util.Arrays" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
request.setAttribute("yearNumbers", yearNumbers);
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
request.setAttribute("monthNumbers", monthNumbers);
List<Integer> dateNumbers = IntStream.range(1, 32).boxed().collect(Collectors.toList());
request.setAttribute("dateNumbers", dateNumbers);
List<Integer> hourNumbers = IntStream.range(1, 25).boxed().collect(Collectors.toList());
request.setAttribute("hourNumbers", hourNumbers);
List<String> minuteNumbers = Arrays.asList("00", "15", "30", "45");
request.setAttribute("minuteNumbers", minuteNumbers);

MySchedule ms = (MySchedule)request.getAttribute("ms");
%>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/addSchedule.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai 予定登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>予定の登録</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="<%= request.getContextPath() + "/AddSchedule" %>" method="post">
			<table>
				<tr><td class="item_name">日付</td>
				<!-- 年 -->
				<td>
					<select name="year">
						<c:set var="selectYear" value="${ms.year}"/>
						<option value="">-</option>
						<c:forEach var="year" items="${yearNumbers}">
							<c:choose>
								<c:when test="${year == selectYear}">
									<option value="${year}" selected><c:out value="${year}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${year}"><c:out value="${year}"/></option>
								</c:otherwise>						
							</c:choose>
						</c:forEach>
					</select>年 
					<!-- 月 -->
					<select name="month">
						<c:set var="selectMonth" value="${ms.month}"/>
						<option value="">-</option>
						<c:forEach var="month" items="${monthNumbers}">
							<c:choose>
								<c:when test="${month == selectMonth}">
									<option value="${month}" selected><c:out value="${month}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${month}"><c:out value="${month}"/></option>
								</c:otherwise>						
							</c:choose>
						</c:forEach>
					</select>月 
					<!-- 日 -->
					<select name="date">
						<c:set var="selectDate" value="${ms.date}"/>
						<option value="">-</option>
						<c:forEach var="date" items="${dateNumbers}">
							<c:choose>
								<c:when test="${date == selectDate}">
									<option value="${date}" selected><c:out value="${date}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${date}"><c:out value="${date}"/></option>
								</c:otherwise>						
							</c:choose>
						</c:forEach>
					</select>日
				</td></tr>
				<tr><td class="item_name">時間</td>
				<!-- 開始時間の時 -->
				<td>
					<select name="startHour">
						<c:set var="selectStartHour" value="${ms.startHour}"/>
						<option value="">-</option>
						<c:forEach var="hour" items="${hourNumbers}">
							<c:choose>
								<c:when test="${hour == selectStartHour}">
									<option value="${hour}" selected><c:out value="${hour}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${hour}"><c:out value="${hour}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>:
					<!-- 開始時間の分 -->
					<select name="startMinute">
						<c:set var="selectStartMinute" value="${ms.startMinute}"/>
						<option value="">-</option>
						<c:forEach var="minute" items="${minuteNumbers}">
							<c:choose>
								<c:when test="${minute == selectStartMinute}">
									<option value="${minute}" selected><c:out value="${minute}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${minute}"><c:out value="${minute}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					
					〜
					
					<!-- 終了時間の時 -->
					<select name="finishHour">
						<c:set var="selectFinishHour" value="${ms.finishHour}"/>
						<option value="">-</option>
						<c:forEach var="hour" items="${hourNumbers}">
							<c:choose>
								<c:when test="${hour == selectFinishHour}">
									<option value="${hour}" selected><c:out value="${hour}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${hour}"><c:out value="${hour}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>:
					<!-- 終了時間の分 -->
					<select name="finishMinute">
						<c:set var="selectFinishMinute" value="${ms.finishMinute}"/>
						<option value="">-</option>
						<c:forEach var="minute" items="${minuteNumbers}">
							<c:choose>
								<c:when test="${minute == selectFinishMinute}">
									<option value="${minute}" selected><c:out value="${minute}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${minute}"><c:out value="${minute}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td></tr>
				
				<tr><td class="item_name">色</td>
				<td>
					<c:choose>
						<c:when test="${ms.color == 'transparent'}">
							<label><input type="radio" name="color" value="transparent" checked > 無 </label>
						</c:when>
						<c:when test="${ms.color == null}">
							<label><input type="radio" name="color" value="transparent" checked > 無 </label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="transparent"> 無 </label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ms.color == 'rgba(250,60,60,0.4)'}">
							<label><input type="radio" name="color" value="rgba(250,60,60,0.4)" checked ><span style="background-color:rgba(250,60,60,0.4);">　</span></label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="rgba(250,60,60,0.4)"><span style="background-color:rgba(250,60,60,0.4);">　</span></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ms.color == '#ffff7a'}">
							<label><input type="radio" name="color" value="#ffff7a" checked ><span style="background-color:#ffff7a;">　</span></label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="#ffff7a"><span style="background-color:#ffff7a;">　</span></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ms.color == '#abf9ff'}">
							<label><input type="radio" name="color" value="#abf9ff" checked ><span style="background-color:#abf9ff;">　</span></label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="#abf9ff"><span style="background-color:#abf9ff;">　</span></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ms.color == '#abf9a8'}">
							<label><input type="radio" name="color" value="#abf9a8" checked ><span style="background-color:#abf9a8;">　</span></label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="#abf9a8"><span style="background-color:#abf9a8;">　</span></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ms.color == 'rgba(212,60,250,0.4)'}">
							<label><input type="radio" name="color" value="rgba(212,60,250,0.4)" checked ><span style="background-color:rgba(212,60,250,0.4);">　</span></label>
						</c:when>
						<c:otherwise>
							<label><input type="radio" name="color" value="rgba(212,60,250,0.4)"><span style="background-color:rgba(212,60,250,0.4);">　</span></label>
						</c:otherwise>
					</c:choose>
				</td></tr>
				
				<tr><td class="item_name">件名</td>
				<td><input class="subject" type="text" name="subject" value="${ms.subject}" required></td></tr>
				
				<tr><td class="item_name">メモ</td>
				<td><textarea name="memo" wrap="hard">${ms.memo}</textarea></td><tr>
			</table>
			
			<input type="hidden" name="skdId" value="${ms.skdId}">
			<br><input class="btn" type="submit" value="登録する">
		</form>
		<c:if test="${fn:length(ms.subject) != 0}">
			<form action="<%= request.getContextPath() + "/AddSchedule" %>" method="post">
				<input type="hidden" name="action" value="delete">
				<input type="hidden" name="skdId" value="${ms.skdId}">
				<input class="btn" type="submit" value="削除">
			</form>
		</c:if>
		<a class="btn" href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>