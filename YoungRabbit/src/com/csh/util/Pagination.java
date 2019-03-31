package com.csh.util;
public class Pagination {
	private int currentPage;

	private int pageSize;

	private int totalRecord;

	private String currentSql;
	
	private String pageParams;
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPage(){
		return (this.getTotalRecord()+pageSize-1)/pageSize;
	}

	/**
	 * 首页
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}
	/**
	 * 上一页
	 * @return
	 */
	public int getUpPage() {
		if(this.getCurrentPage()<=1){
			return 1;
		}
		return this.getCurrentPage()-1;
	}
	/**
	 * 下一页
	 * @return
	 */
	public int getDownPage(){
		if(this.getCurrentPage()>=this.getTotalPage()){
			return this.getTotalPage();
		}
		return this.getCurrentPage()+1;
	}

	/**
	 * 尾页
	 * @return
	 */
	public int getLastPage(){
		return this.getTotalPage();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	
	public String getCurrentSql() {
		return currentSql;
	}
	

	public void setCurrentSql(String currentSql) {
		this.currentSql = currentSql;
	}

	public String getPageParams() {
		return pageParams;
	}

	public void setPageParams(String pageParams) {
		this.pageParams = pageParams;
	}

	

}
