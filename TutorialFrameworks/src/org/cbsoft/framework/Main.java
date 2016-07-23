package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		p.setSecretCode("Not appear ...");
		
//		CompositePostProcessor comp = new CompositePostProcessor(new Crypto(5), new Compressor());
//		FileSerializer cxs = new FileSerializer(comp, new XMLFormatter());
		
		FileSerializer cxs = new FileSerializer(new Compressor(), new PropertiesFomatter());
		cxs.generateFile("product.zip", p);
	}
}
