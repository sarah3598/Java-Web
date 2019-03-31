package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import net.StrUtils;



import org.hibernate.Query;

import com.csh.dao.BaseDao;
import com.csh.domain.GoodsKinds;
import com.csh.domain.Image;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.domain.Store;
import com.csh.domain.Users;
import com.csh.domain.WebGoodsBasicInfo;
import com.csh.domain.WebOrders;
import com.csh.util.PageModel;
/*********************
 * Author shaohui-chen
 ******************** */
public class WebProductsService {

// 查询products

	public static List<WebGoodsBasicInfo> findGoodsInfo(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		List<WebGoodsBasicInfo> list = new ArrayList<WebGoodsBasicInfo>();
		try {
			while (rs.next()) {
				WebGoodsBasicInfo wg = new WebGoodsBasicInfo();

				//int USER_ID = rs.getInt("USER_ID");
				int goodId = rs.getInt("GOOD_ID");
				String title = rs.getString("TITLE");
				String description = rs.getString("DESCRIPTION");
				int imagesId = rs.getInt("IMAGES_ID");
				Long price = rs.getLong("PRICING");
				//int STORE_ID = rs.getInt("STORE_ID");

				wg.setGoodId(goodId);
				wg.setTitle(title);
				wg.setDescription(description);
				wg.setImagesId(imagesId);
				wg.setPricing(price);
	
				list.add(wg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// 查询商品列表分页对象
	public static PageModel<WebGoodsBasicInfo> findGoodsPageModel(int currentPage, int pageSize, String sql,
			String rowContSql, Object[] param) {
		List<WebGoodsBasicInfo> list = findGoodsInfo(sql, param);
		int rowCount = BaseDao.getRowCount(rowContSql, null);

		PageModel<WebGoodsBasicInfo> pageModel = new PageModel<WebGoodsBasicInfo>();
		pageModel.setCurrentPage(currentPage);
		pageModel.setList(list);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(rowCount);
		return pageModel;
		
	}

	// 删除商品条目
	public static int deleteProduct(String goodId) {
		String sql = "delete from WEB_GOODS_BASIC_INFO  where GOOD_ID=" + goodId;
		// String sql1= "delete from family_member WHERE Person_id="+goodId;
		int i = BaseDao.exeSql(sql, null);
		if (i == -1) {
			return -1;
		}
		// i = BaseDao.exeSql(sql1, null);
		return i;

	}
	
	
	
}
