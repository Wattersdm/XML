

public class Student {
	
	    private String name;
	    private String gender;
	    private int age;
	    private String major;
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getGender() {
	        return gender;
	    }
	    public void setGender(String gender) {
	        this.gender = gender;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }
	    public String getMajor() {
	        return major;
	    }
	    public void setMajor(String major) {
	        this.major = major;
	    }
	    
	    @Override
	    public String toString() {
	        return "Student:: Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender +
	                " Major=" + this.major;
	    }
}





