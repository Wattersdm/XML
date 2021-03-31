import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class XMLCreateHW1 {	
	
    //utility method to create text node
    private static Node getStudentElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
	
    private static Node getStudent(Document doc, String id, String name, String age, String major, String gender) {
        Element student = doc.createElement("Student");

        //set id attribute
        student.setAttribute("id", id);

        //create name element
        student.appendChild(getStudentElements(doc, student, "name", name));

        //create age element
        student.appendChild(getStudentElements(doc, student, "age", age));

        //create role element
        student.appendChild(getStudentElements(doc, student, "major", major));

        //create gender element
        student.appendChild(getStudentElements(doc, student, "gender", gender));

        return student;
    }
    
    
	public static void main(String[] args) {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			//create an instance of the DocumentBuilder
		    dBuilder = dbFactory.newDocumentBuilder();
		    //create new instance of a DOM Document object to build a DOM tree with
		    Document doc = dBuilder.newDocument();
		    //add elements to Document
		    Element rootElement =
		        doc.createElementNS("https://www.w3.org/1999/xhtml", "Students");
		    //append root element to document
		    doc.appendChild(rootElement);

		    //append first child element to root element
		    rootElement.appendChild(getStudent(doc, "1", "Rob", "29", "CPDM", "Male"));
		    
		    //append second child element to root element
		    rootElement.appendChild(getStudent(doc, "2", "Jane", "19", "CPDM", "Female"));
		    
		    //append third child element to root element
		    rootElement.appendChild(getStudent(doc, "3", "Clare", "17", "CPDM", "Female"));
		    
		    //append fourth child element to root element
		    rootElement.appendChild(getStudent(doc, "4", "Timmy", "21", "CPDM", "Male"));
		    
		    //append fifth child element to root element
		    rootElement.appendChild(getStudent(doc, "5", "Ruth", "52", "CPDM", "Female"));
		    
		    //for output to file, console
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    //for indenting and printing line by line
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    
		    DOMSource source = new DOMSource(doc);

		    //write to console or file
		    StreamResult console = new StreamResult(System.out);
		    StreamResult file = new StreamResult(new File("std.xml"));

		    //write data
		    transformer.transform(source, console);
		    transformer.transform(source, file);
		    System.out.println("DONE");

		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}

