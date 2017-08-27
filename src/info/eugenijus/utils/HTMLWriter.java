package info.eugenijus.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HTMLWriter {
	private String folder;
	
	public HTMLWriter() {
		this.folder= "";
	}
	
	public HTMLWriter(String testFolder) {
		this.folder= testFolder;
	}

	/**
	 * Based on example in this: 
	 * https://stackoverflow.com/questions/21665009/converting-xml-to-an-html-string-using-xslt-in-java
	 * @param xmlFilename
	 * @param xslFilename
	 * @param htmlFilename
	 */
	public boolean convertXMLtoHTML(String xmlFilename, String xslFilename, String htmlFilename) {
		boolean isSuccess = false;
		try {
			FileInputStream xml = new FileInputStream(folder + xmlFilename);  
			FileInputStream xsl = new FileInputStream(folder + xslFilename);
			FileOutputStream html = new FileOutputStream(folder + htmlFilename);
	
			Source xmlDoc =  new StreamSource(xml);
			Source xslDoc =  new StreamSource(xsl);
			Result htmlDoc =  new StreamResult(html);
	
			TransformerFactory factory = TransformerFactory.newInstance();            
			Transformer trans;
		
			trans = factory.newTransformer(xslDoc);
			trans.transform(xmlDoc, htmlDoc);

			isSuccess = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} 
		finally {
			if(isSuccess) {
				System.out.println("Done writing HTML to: " + folder + htmlFilename);
			} else {
				System.out.println("Couldn't write HTML to: " + folder + htmlFilename);
			}
		}
		return isSuccess;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
