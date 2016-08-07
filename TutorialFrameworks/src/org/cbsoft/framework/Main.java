package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		p.setSecretCode("Not appear ...");

		SerializeBuilder builder = new SerializeBuilder();
		Serializer cxs = builder.createPropertiesSerializer()
				.withEncryption(1)
				.withLogging().build();
		cxs.generateFile("product.txt", p);
	}
}
