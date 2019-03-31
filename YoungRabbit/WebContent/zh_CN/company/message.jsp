<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="baseHeader_1.jsp"></jsp:include>

<html>
	<body>
		<center>
			<table cellspacing="0" cellpadding="0" width="1000">
				<tbody>
				<tr>
						<th style="text-align: left; padding-left: 15px;" width="600">
							<p
								style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
								<span style="color: red; font-size: 16px;">${message}</span>
								<br />
								<input type="button" onclick="window.history.back()" value="返回" />
							</p>
						</th>

					</tr>
				</tbody>
			</table>

		</center>
	</body>
</html>
