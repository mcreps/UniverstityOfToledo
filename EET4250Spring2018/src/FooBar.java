
public class FooBar {

	private int fooBarID;
	private String fooBarCol;
	private String fooBarCol1;
	private String fooBarCol2;
	private String fooBarCol3;
	private String fooBarCol4;
	public int getFooBarID() {
		return fooBarID;
	}
	public void setFooBarID(int fooBarID) {
		this.fooBarID = fooBarID;
	}
	public String getFooBarCol() {
		return fooBarCol;
	}
	public void setFooBarCol(String fooBarCol) {
		this.fooBarCol = fooBarCol;
	}
	public String getFooBarCol1() {
		return fooBarCol1;
	}
	public void setFooBarCol1(String fooBarCol1) {
		this.fooBarCol1 = fooBarCol1;
	}
	public String getFooBarCol2() {
		return fooBarCol2;
	}
	public void setFooBarCol2(String fooBarCol2) {
		this.fooBarCol2 = fooBarCol2;
	}
	public String getFooBarCol3() {
		return fooBarCol3;
	}
	public void setFooBarCol3(String fooBarCol3) {
		this.fooBarCol3 = fooBarCol3;
	}
	public String getFooBarCol4() {
		return fooBarCol4;
	}
	public void setFooBarCol4(String fooBarCol4) {
		this.fooBarCol4 = fooBarCol4;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FooBar [fooBarID=");
		builder.append(fooBarID);
		builder.append(", fooBarCol=");
		builder.append(fooBarCol);
		builder.append(", fooBarCol1=");
		builder.append(fooBarCol1);
		builder.append(", fooBarCol2=");
		builder.append(fooBarCol2);
		builder.append(", fooBarCol3=");
		builder.append(fooBarCol3);
		builder.append(", fooBarCol4=");
		builder.append(fooBarCol4);
		builder.append("]");
		return builder.toString();
	}
}
