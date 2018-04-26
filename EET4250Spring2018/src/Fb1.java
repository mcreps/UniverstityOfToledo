
public class Fb1 {
	private int rowid;
	private String col1;
	private String col2;
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fb1 [rowid=");
		builder.append(rowid);
		builder.append(", col1=");
		builder.append(col1);
		builder.append(", col2=");
		builder.append(col2);
		builder.append("]");
		return builder.toString();
	}
	
}
