package org.cbsoft.framework;

import java.util.Map;

public class PropertiesFomatter implements DataFormatter {

	public PropertiesFomatter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] formatData(Map<String, Object> props) {
		StringBuilder propFileBuilder = new StringBuilder();
		for(String prop : props.keySet()){
			propFileBuilder.append(prop + "="+props.get(prop)+"\n");
		}
		byte[] bytes = propFileBuilder.toString().getBytes();
		return bytes;
	}

}
