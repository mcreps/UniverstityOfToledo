
public class Course {
	
	private String courseId;
	private int instructorId; 
	private int creditHrs; 
	private String semester; 
	private int year;
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public int getCreditHrs() {
		return creditHrs;
	}
	public void setCreditHrs(int creditHrs) {
		this.creditHrs = creditHrs;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [courseId=");
		builder.append(courseId);
		builder.append(", instructorId=");
		builder.append(instructorId);
		builder.append(", creditHrs=");
		builder.append(creditHrs);
		builder.append(", semester=");
		builder.append(semester);
		builder.append(", year=");
		builder.append(year);
		builder.append("]");
		return builder.toString();
	}

}
