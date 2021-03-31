import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Abstract: Reads a specified file and prints to console a formatted view  
 *  
 * @author Devin Watters
 * @version 2020
 */
public class ViewCustomers {  
	
	/**
	 * Abstract: gets a value of an element.
	 * 
	 * @param tag name of element
	 * @param element node type
	 * @return value of element
	 */
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
	
	/**
	 * Abstract: gets a value of an attribute.
	 * 
	 * @param tag name of attribute
	 * @param element name of element
	 * @return value of attribute
	 */
	public static String getAttributeValue(String tag,Element element) {
		Node nodeList = element.getAttributeNode(tag);		
		String id = nodeList.getNodeValue();		
		return id;		   
	}
	
	/**
	 * Abstract: Method used to assign element values to class variables
	 * 
	 * @param node name of node
	 * @return class instance
	 */
	private static Customer getCustomer(Node node) {	       
		Customer cus = new Customer();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;			
			cus.setID(getAttributeValue("id",element));
			cus.setName(getTagValue("name", element));
			cus.setType(getTagValue("type", element));
			cus.setAddress(getTagValue("address", element));
			cus.setCity(getTagValue("city", element));
			cus.setState(getTagValue("state", element));
			cus.setZipcode(getTagValue("zipcode", element));
		}

		return cus;
	}

	/**
	 * Abstract: Method opens the file and reads contents to console
	 * 
	 * @param file - file name to read
	 */
	public static void ReadFile(String file) {		
		try {
			//prepare the document for processing
			String filePath = file;
			File xmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//Parse the content of the given InputStream as an XML document and return a new DOM Document object.
			Document doc = dBuilder.parse(xmlFile);
			//access to the child node that is the document element of the document
			//and "validate" parameter to verify if the value matches the validity constraint for standalone document declaration as defined in [XML 1.0]
			doc.getDocumentElement().normalize();
			//get the Root element node name
			System.out.println("Root element is : " + doc.getDocumentElement().getNodeName() + "\n");
			//returns a collection of all elements in the document with the specified tag name, as a NodeList object
			NodeList nodeList = doc.getElementsByTagName("Customer");
			//now XML is loaded as Document in memory, convert it to Object List
			List<Customer> cusList = new ArrayList<Customer>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				cusList.add(getCustomer(nodeList.item(i)));
			}
			//print Employee list information
			for (Customer emp : cusList) {	            	
				System.out.println(emp.toString());
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {e1.printStackTrace();}

	}
	
}



