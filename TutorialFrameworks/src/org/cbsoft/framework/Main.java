package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		
		CompositePostProcessor comp = new CompositePostProcessor(new Crypto(5), new Compressor());
		FileSerializer cxs = new FileSerializer(comp, new XMLFormatter());
		cxs.generateFile("product.zip", p);
	}
}
