package org.cbsoft.framework;

public class SerializeLogger implements Serializer {

	private Serializer serializer;
	
	public SerializeLogger(Serializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public void generateFile(String filename, Object obj) {
		System.out.println("Serializing file " + filename + "...");
		serializer.generateFile(filename, obj);
		System.out.println("Serialization for file " + filename + " finished!!!");
	}

	public void setPostProcessor(PostProcessor pp) {
	}

	public PostProcessor getPostProcessor() {
		return null;
	}

}
