package edu.nju.software.network.datagenerator;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class XmlSerialize<T> {
	
	public void serializeMultipleObject(FileOutputStream os, List<T> objs){
		XMLEncoder xe = new XMLEncoder(os,"UTF-8",true,0);  
		//XMLEncoder xe = new XMLEncoder(os);  
		xe.writeObject(objs);
		xe.close();
	} 
	public List<T> deserializeSingleObject(FileInputStream is){
		XMLDecoder xd = new XMLDecoder(is);  
		List<T> objs = (List<T>)xd.readObject();  
		xd.close();  
		return objs;  
	}
}
