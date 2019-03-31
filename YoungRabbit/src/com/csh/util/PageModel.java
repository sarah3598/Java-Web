package com.csh.util;

import java.util.List;

public class PageModel<T> {

	private List<T> list = null;

	private int currentPage;

	private int pageSize;

	private int totalRecord;
	
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
	
	
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
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

	

}
