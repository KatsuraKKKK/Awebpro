package com.github.katsurakkkk.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomTest {
	
	int indent = 0;

	public void testDom(String path) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		// InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		Document doc = db.parse(new File(path));
		NodeList nodes = doc.getChildNodes();
//		for (int index = 0; index < nodes.getLength(); index++) {
//			echo(nodes.item(index));
//		}
		
	}

	public static void main(String[] rags) {
		DomTest dt = new DomTest();
		try {
			dt.testDom("src/main/resources/sw.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printlnCommon(Node n) {
		System.out.print(" nodeName=\"" + n.getNodeName() + "\"");
		String val = n.getNamespaceURI();
		if (val != null) {
			System.out.print(" uri=\"" + val + "\"");
		}
		val = n.getPrefix();
		if (val != null) {
			System.out.print(" pre=\"" + val + "\"");
		}
		val = n.getLocalName();
		if (val != null) {
			System.out.print(" local=\"" + val + "\"");
		}
		val = n.getNodeValue();
		if (val != null) {
			System.out.print(" nodeValue=");
			if (val.trim().equals("")) {
				// Whitespace
				System.out.print("[WS]");
			} else {
				System.out.print("\"" + n.getNodeValue() + "\"");
			}
		}
		System.out.println();
	}
	
	private void echo(Node n) {
		for (int i = 0; i < indent; i++) {
			System.out.print("    ");
		}
		
	    int type = n.getNodeType();

	    switch (type) {
	        case Node.ATTRIBUTE_NODE:
	            System.out.print("ATTR:");
	            printlnCommon(n);
	            break;

	        case Node.CDATA_SECTION_NODE:
	            System.out.print("CDATA:");
	            printlnCommon(n);
	            break;

	        case Node.COMMENT_NODE:
	            System.out.print("COMM:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_FRAGMENT_NODE:
	            System.out.print("DOC_FRAG:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_NODE:
	            System.out.print("DOC:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_TYPE_NODE:
	            System.out.print("DOC_TYPE:");
	            printlnCommon(n);
	            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
	            for (int i = 0; i < nodeMap.getLength(); i++) {
	                Entity entity = (Entity)nodeMap.item(i);
	                indent++;
	                echo(entity);
	                indent--;
	            }
	            break;

	        case Node.ELEMENT_NODE:
	            System.out.print("ELEM:");
	            printlnCommon(n);

	            NamedNodeMap atts = n.getAttributes();
	            for (int i = 0; i < atts.getLength(); i++) {
	                Node att = atts.item(i);
	                indent++;
	                echo(att);
	                indent--;
	            }
	            break;

	        case Node.ENTITY_NODE:
	            System.out.print("ENT:");
	            printlnCommon(n);
	            break;

	        case Node.ENTITY_REFERENCE_NODE:
	            System.out.print("ENT_REF:");
	            printlnCommon(n);
	            break;

	        case Node.NOTATION_NODE:
	            System.out.print("NOTATION:");
	            printlnCommon(n);
	            break;

	        case Node.PROCESSING_INSTRUCTION_NODE:
	            System.out.print("PROC_INST:");
	            printlnCommon(n);
	            break;

	        case Node.TEXT_NODE:
	            System.out.print("TEXT:");
	            printlnCommon(n);
	            break;

	        default:
	            System.out.print("UNSUPPORTED NODE: " + type);
	            printlnCommon(n);
	            break;
	    }

	    for (Node child = n.getFirstChild(); child != null;
	         child = child.getNextSibling()) {
	    	indent++;
	        echo(child);
	        indent--;
	    }
	}
	
	public Node findSubNode(String name, Node node) {
	    if (node.getNodeType() != Node.ELEMENT_NODE) {
	        System.err.println("Error: Search node not of element type");
	        System.exit(22);
	    }

	    if (! node.hasChildNodes()) return null;

	    NodeList list = node.getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.ELEMENT_NODE) {
	           if (subnode.getNodeName().equals(name)) 
	               return subnode;
	        }
	    }
	    return null;
	}
	
	public String getText(Node node) {
	    StringBuffer result = new StringBuffer();
	    if (! node.hasChildNodes()) return "";

	    NodeList list = node.getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.TEXT_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
	            result.append(getText(subnode));
	        }
	    }

	    return result.toString();
	}
}
