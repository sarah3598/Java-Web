package com.csh.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.domain.Users;
import com.csh.service.CompanyProductService;
import com.csh.service.InventoryService;
import com.csh.service.JsonService;
import com.csh.service.ProductService;
import com.csh.service.SlideService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JOptionPane;

/**
 * @author wendy
 *
 */
public class SlideServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// add_slide(request, response);
		String op = request.getParameter("OP");
		if (op == null) {
			getSlideList(request, response);
			return;
		}
		/* String method = request.getParameter("method"); */

		switch (op) {
		case "1":
			add_slide(request, response);
			break;
		case "2":
			update_slide(request, response);
			break;
		case "3":
			del_slide(request, response);
			break;
		}

	}

	private void del_slide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String SLIDE_ID = request.getParameter("SLIDE_ID");

		String sql = "DELETE FROM SLIDER WHERE SLIDE_ID=?";
		Object[] param = new Object[1];
		param[0] = SLIDE_ID;
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";

		if (result == 1) {
			// 添加规则完成后，取出数据，刷新列表
			int pageSize = 10;
			int currentPage = request.getParameter("currentPage") == null ? 1
					: Integer.parseInt(request.getParameter("currentPage"));
			int startRow = (currentPage - 1) * pageSize;
			int endRow = startRow + pageSize;// include the end row

			String listSql = "select * from SLIDER t where 1=1 ";
			String rowSql = "select count(1) from SLIDER t where 1=1 ";

			String rs = SlideService.getSlideListRs(startRow, endRow, listSql, null);
			// int rows = CompanyGradeService.getGradeRuleListRows(rowSql,
			// null);

			if (rs != null) {
				String msg = "删除成功！";
				normal_json = JsonService.getNormalJson(rs, msg);
			} else {
				normal_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
				// rows = 0;
			}

		} else {
			normal_json = JsonService.getOperateResultNormalJson("false", null, "删除失败！");
		}

		JsonService.ResponseJson(request, response, normal_json);
	}

	private void update_slide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String HEAD = request.getParameter("HEAD");
		String DESCR = request.getParameter("DESCR");
		String HREF = request.getParameter("HREF");
		String SLIDE_ID = request.getParameter("SLIDE_ID");

		String sql = "UPDATE SLIDER SET HEAD=?,DESCR=?,HREF=? WHERE SLIDE_ID=?";
		Object[] param = new Object[4];
		param[0] = HEAD;
		param[1] = DESCR;
		param[2] = HREF;
		param[3] = SLIDE_ID;

		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		if (result == 1) {
			// 添加规则完成后，取出数据，刷新列表
			int pageSize = 10;
			int currentPage = request.getParameter("currentPage") == null ? 1
					: Integer.parseInt(request.getParameter("currentPage"));
			int startRow = (currentPage - 1) * pageSize;
			int endRow = startRow + pageSize;// include the end row

			String listSql = "select * from SLIDER  where 1=1 ";
			String rs = SlideService.getSlideListRs(startRow, endRow, listSql, null);
			// int rows = CompanyGradeService.getGradeRuleListRows(rowSql,
			// null);

			if (rs != null) {
				String msg = "更新成功！";
				normal_json = JsonService.getNormalJson(rs, msg);
			} else {
				normal_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
				// rows = 0;
			}

		} else {
			normal_json = JsonService.getOperateResultNormalJson("false", null, "更新失败！");
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	private void getSlideList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;// include the end row

		String listSql = "select * from SLIDER t where 1=1 ";
		String rowSql = "select count(1) from SLIDER t where 1=1 ";

		String rs = SlideService.getSlideListRs(startRow, endRow, listSql, null);
		int rows = SlideService.getSlideListRows(rowSql, null);

		if (rs != null) {
			request.setAttribute("slide_list", rs);
			request.setAttribute("rows", rows);

		} else {
			rs = null;
			rows = 0;
		}
		request.getRequestDispatcher("/zh_CN/company/yb_db_slider_add.jsp").forward(request, response);

	}

	public void add_slide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String SLIDE_ID = request.getParameter("SLIDE_ID");
		String HEAD = request.getParameter("HEAD");
		String DESCR = request.getParameter("DESCR");
		String HREF = request.getParameter("HREF");
		
		String normal_json = "";
		String msg = "";
		
		String sql = "UPDATE SLIDER SET HEAD=?,DESCR=?,HREF=? WHERE SLIDE_ID=? ";
		Object[] param = new Object[4];		
		param[0] = HEAD;
		param[1] = DESCR;
		param[2] = HREF;
		param[3] = SLIDE_ID;

		int flag = BaseDao.exeSql(sql, param);
		
		// 一个slideimg的基本信息添加成功，初始化库存
		if (flag == 1) {
			// 添加规则完成后，取出数据，刷新列表
			int pageSize = 10;
			int currentPage = request.getParameter("currentPage") == null ? 1
					: Integer.parseInt(request.getParameter("currentPage"));
			int startRow = (currentPage - 1) * pageSize;
			int endRow = startRow + pageSize;// include the end row

			String listSql = "select * from SLIDER t where 1=1 ";
			String rowSql = "select count(1) from SLIDER t where 1=1 ";

			String rs = SlideService.getSlideListRs(startRow, endRow, listSql, null);
			// int rows = CompanyGradeService.getGradeRuleListRows(rowSql,
			// null);

			if (rs != null) {
				msg = "添加成功！";
				normal_json = JsonService.getNormalJson(rs, msg);
			} else {
				normal_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
				// rows = 0;
			}

		} else {
			normal_json = JsonService.getOperateResultNormalJson("false", null, "添加失败！");
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

}
