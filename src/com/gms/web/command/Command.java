package com.gms.web.command;
import com.gms.web.constants.*;

import lombok.Getter;
import lombok.Setter;


public class Command implements Commandable {
	@Getter
	protected String action, pageNumber, view, search, column;
	
	@Getter @Setter
	protected String startRow, endRow, dir, page;

	public void setSearch(String search) {
		this.search = 
				(search==null)?
						"none":search;
	}
	
	public void setColumn(String column) {
		this.column = 
				(column==null)?
						"none":column;
	}
	
	public void setPageNumber(String pageNumber) {
		this.pageNumber = 			
				(pageNumber==null)?
						"1":pageNumber;
		System.out.println("페이지번호 : " + pageNumber);
	}
	public void setAction(String action) {
		this.action = 
				(action==null)?
						"move":action;
		System.out.println("액션은 : " + action);
	}
	
	@Override
	public void process() {
		this.view=
				(dir.equals("home"))?
						"/WEB-INF/view/common/home.jsp":
							Path.VIEW + dir + Path.SEPARATOR + page + Extension.JSP;
	}

	@Override
	public String toString() {
		return "Command [dest=" + dir + Path.SEPARATOR + page + ".jsp" + ", Action=" + action + "]";
	}

}
