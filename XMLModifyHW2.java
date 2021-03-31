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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLModifyHW2 {

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	private static Student getStudent(Node node) {
		//XMLReaderDOM domReader = new XMLReaderDOM();
		Student std = new Student();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			std.setName(getTagValue("name", element));
			std.setAge(Integer.parseInt(getTagValue("age", element)));
			std.setGender(getTagValue("gender", element));
			std.setMajor(getTagValue("major", element));
//			std.setGPA(getTagValue("gpa", element));
		}
		return std;
	}
	
	private static void addElement(Document doc) {
		NodeList students = doc.getElementsByTagName("Student");
		Element std = null;

		//loop for each students
		for(int i=0; i<students.getLength();i++){
			std = (Element) students.item(i);
			Element GPAElement = doc.createElement("gpa");
			GPAElement.appendChild(doc.createTextNode("3.0"));
			std.appendChild(GPAElement);
		}
	}

	//private static void deleteElement(Document doc) {
	//		    NodeList students = doc.getElementsByTagName("Student");
	//		    Element std = null;
	//		    //loop for each students
	//		    for(int i=0; i<students.getLength();i++){
	//		    	std = (Element) students.item(i);
	//		        Node genderNode = std.getElementsByTagName("gender").item(0);
	//		        std.removeChild(genderNode);
	//		    }
	//    
	//}

	private static void updateElementValue(Document doc) {
		NodeList students = doc.getElementsByTagName("Student");
		Element std = null;
		//loop for each students
		for(int i=0; i<students.getLength();i++){
			std = (Element) students.item(i);
			Node name = std.getElementsByTagName("name").item(0).getFirstChild();
			name.setNodeValue(name.getNodeValue().toUpperCase());
		}
	}

	private static void updateAttributeValue(Document doc) {
		NodeList students = doc.getElementsByTagName("Student");
		Element std = null;
		//loop for each students
		for(int i=0; i<students.getLength();i++){
			std = (Element) students.item(i);
			String gender = std.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
			if(gender.equalsIgnoreCase("male")){
				//prefix id attribute with M
				std.setAttribute("id", "M"+std.getAttribute("id"));
			}else{
				//prefix id attribute with F
				std.setAttribute("id", "F"+std.getAttribute("id"));
			}
		}
	}
	
	
	public static void main(String[] args) {
		String filePath = "std.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			//update attribute value
			updateAttributeValue(doc);

			//update Element value
			updateElementValue(doc);

			//delete element
			//deleteElement(doc);

			//add new element GPA
			// Students need GPA element to view standing, get recognition or alert advisor for further academic help.
			addElement(doc);

			//write the updated document to file or console
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("std_updated.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("XML file updated successfully");

		} catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
			e1.printStackTrace();
		}
	}	

}


