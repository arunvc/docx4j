package org.docx4j.openpackaging.parts.SpreadsheetML;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.xlsx4j.sml.Worksheet;

public class WorksheetPart extends JaxbSmlPart<Worksheet> {
	
	public WorksheetPart(PartName partName) throws InvalidFormatException {
		super(partName);
		init();
	}

	public WorksheetPart() throws InvalidFormatException {
		super(new PartName("/xl/worksheets/sheet1.xml"));
		init();
	}
	
	public void init() {
				
		// Used if this Part is added to [Content_Types].xml 
		setContentType(new  org.docx4j.openpackaging.contenttype.ContentType( 
				org.docx4j.openpackaging.contenttype.ContentTypes.SPREADSHEETML_WORKSHEET));

		// Used when this Part is added to a rels 
		setRelationshipType(Namespaces.SPREADSHEETML_WORKSHEET);
		
	}
	
	@Override
	public void setJaxbElement(Worksheet jaxbElement) {
		super.setJaxbElement(jaxbElement);
		jaxbElement.setParent(this); // if you create a new WorksheetPart
	}
	
	@Override
    public Worksheet unmarshal( java.io.InputStream is ) throws JAXBException {
		
		Worksheet w = super.unmarshal(is);
		w.setParent(this); // workaround for JAXB in Java 8 setting parent to JAXBElement!
		return w;
	}
	
	@Override
    public Worksheet unmarshal(org.w3c.dom.Element el) throws JAXBException {

		Worksheet w = super.unmarshal(el);
		w.setParent(this); // presume JAXB gets in wrong here too
		return w;
		
	}
}