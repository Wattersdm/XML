/**
 * Abstract: A class used to assign elements and attributes from xml file to java. 
 *  
 * @author Devin Watters
 * @version 2020
 */
public class Customer {
	
	// Private class variable
	private String ID;
	private String name;
    private String type;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    
    // Getters 
    public String getID() {return ID;}
    
    public String getName() {return name;}
	
    public String getType() {return type;}
    
    public String getAddress() {return address;}
    
    public String getcity() {return city;}
    
    public String getState() {return state;}
    
    public String getzipcode() {return zipcode;}    
    
    // Setters   
    public void setID(String ID) {this.ID = ID;}
    
    public void setName(String name) {this.name = name;}
    
    public void setType(String type) {this.type = type;}
    
    public void setAddress(String address) {this.address = address;}
    
    public void setCity(String city) {this.city = city;}
    
    public void setState(String state) {this.state = state;}
    
    public void setZipcode(String zipcode) {this.zipcode = zipcode;}
    
        
    // changes the '.toString' attribute    
    @Override
    public String toString() {
        return "Customer ID " + this.ID + "\n" +
        		"\tName: " + this.name + "\n" + 
        		"\tType: " + this.type + "\n" + 
        		"\tAddress: " +this.address + " " + "\n" + 
        		"\t\t " +this.city + "," + this.state + " " + this.zipcode;
    }   
    
}
