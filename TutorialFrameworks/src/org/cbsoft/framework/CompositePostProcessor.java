package org.cbsoft.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositePostProcessor implements PostProcessor {

	private List<PostProcessor> processors;
	
	public CompositePostProcessor(PostProcessor... list) {
		super();
		this.processors = new ArrayList<PostProcessor>();

		for (PostProcessor p : list) {
			this.processors.add(p);
		}
	}



	@Override
	public byte[] postProcess(byte[] bytes) throws IOException {
		for (PostProcessor p : processors) {
			bytes = p.postProcess(bytes);
		}
		
		return bytes;
	}

}
