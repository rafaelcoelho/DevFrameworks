package org.cbsoft.framework;

public class SerializeBuilder {

	private Serializer serializer;
	
	public SerializeBuilder createXMLSerializer() {
		serializer = new FileSerializer(new NullPostProcessor(), new XMLFormatter());
		return this;
	}

	public SerializeBuilder createPropertiesSerializer() {
		serializer = new FileSerializer(new NullPostProcessor(), new PropertiesFomatter());
		return this;
	}
	
	public SerializeBuilder withEncryption(int number) {
		PostProcessor pp = new Crypto(number);
		addPostProcessor(pp);
		return this;
	}
	
	public SerializeBuilder withCompression() {
		PostProcessor pp = new Compressor();
		addPostProcessor(pp);
		return this;
	}
	
	private void addPostProcessor(PostProcessor pp) {
		PostProcessor current = serializer.getPostProcessor();
		if (current instanceof NullPostProcessor) {
			serializer.setPostProcessor(pp);
		} else
		{
			CompositePostProcessor composite = 
					new CompositePostProcessor(current, pp);
			
			serializer.setPostProcessor(composite);
		}
	}
	
	public SerializeBuilder withLogging() {
		serializer = new SerializeLogger(serializer);
		return this;
	}
	
	public Serializer build() {
		return serializer;
	}
}
