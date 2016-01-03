package com.github.katsurakkkk.xml.xstream;

import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Persons")
public class Persons {
	@XStreamAsAttribute
    @XStreamAlias("xmlns")
    String xmlns;
	
	@XStreamAsAttribute
    @XStreamAlias("xmlns:xsi")
	String xmlnsxsi;
	
    private String type;
    private List<Person> listPerson;

    public Persons(List<Person> listPerson, String type) {
        this.listPerson = listPerson;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }
    

    /**
	 * @return the xmlns
	 */
	public String getXmlns() {
		return xmlns;
	}

	/**
	 * @param xmlns the xmlns to set
	 */
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	
	
	/**
	 * @return the xmlnsxsi
	 */
	public String getXmlnsxsi() {
		return xmlnsxsi;
	}

	/**
	 * @param xmlnsxsi the xmlnsxsi to set
	 */
	public void setXmlnsxsi(String xmlnsxsi) {
		this.xmlnsxsi = xmlnsxsi;
	}

	public String toString() {
        StringBuffer sb=new StringBuffer();
        for(Iterator<Person> it=listPerson.iterator();it.hasNext();){
            @SuppressWarnings("unused")
			Person p=(Person)it.next();
            sb.append(it.toString());
        }
        return "Persons{" +
                "type='" + type + '\'' +
                ", listPerson=" + sb.toString() +
                "}\n";
    }
}
