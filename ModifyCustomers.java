import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Abstract: Modifies customers.xml to customer_modified.xml by adding elements: customer phone number, customer contact name, customer email address
 * 
 * @author Devin Watters
 * @version 2020
 */
public class ModifyCustomers {
	
	/**
	 * Abstract: Method that modifies customers.xml to customer_modified.xml by adding elements: customer phone number, customer contact name, customer email address
	 */
	public static void WriteToFile() {
		// file to be modified
		String filePath = "customers.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile); // Type of file (xml)
			doc.getDocumentElement().normalize();			
			NodeList customers = doc.getElementsByTagName("Customer"); // name of node that elements are being added to
			Element cus = null;
			
			//loop for each customer node
			for(int i=0; i<customers.getLength();i++){
				cus = (Element) customers.item(i);
				// Creates phone, contact name and email elements
				Element phoneElement = doc.createElement("phonenumber");
				Element contactElement = doc.createElement("contactname");
				Element emailElement = doc.createElement("emailaddress");			
				// adds text to each element phone, contact, and email
				phoneElement.appendChild(doc.createTextNode("5134271234"));
				contactElement.appendChild(doc.createTextNode("Tim Burger"));
				emailElement.appendChild(doc.createTextNode("tburger@mail.com"));
				// Adds the new elements to 'customer' node
				cus.appendChild(phoneElement);
				cus.appendChild(contactElement);
				cus.appendChild(emailElement);
				}
			
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("customer_modified.xml")); // file where changes are saved  
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("XML file updated successfully");
			
		} catch (SAXException | ParserConfigurationException | IOException | TransformerException e1){e1.printStackTrace();}		
		
	}
	
}
