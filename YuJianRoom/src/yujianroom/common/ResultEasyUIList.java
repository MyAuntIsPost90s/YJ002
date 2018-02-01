package yujianroom.common;

import java.util.List;

public class ResultEasyUIList<T> {
	private long total;
	private List<T> rows;
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
