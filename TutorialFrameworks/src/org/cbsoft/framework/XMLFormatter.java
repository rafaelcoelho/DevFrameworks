package org.cbsoft.framework;

import java.util.Map;

public class XMLFormatter implements DataFormatter {

	public XMLFormatter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] formatData(Map<String, Object> props) {
		StringBuilder propFileBuilder = new StringBuilder();
		propFileBuilder.append("<properties>");
		for(String prop : props.keySet()){
			propFileBuilder.append("<"+prop + ">"+props.get(prop)+"</"+prop + ">");
		}
		propFileBuilder.append("</properties>");
		byte[] bytes = propFileBuilder.toString().getBytes();
		return bytes;
	}

}
