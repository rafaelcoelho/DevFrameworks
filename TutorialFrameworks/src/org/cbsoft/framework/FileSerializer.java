package org.cbsoft.framework;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FileSerializer implements Serializer {

	private PostProcessor pp;
	private DataFormatter df;
	
	@Override
	public PostProcessor getPostProcessor() {
		return pp;
	}

	@Override
	public void setPostProcessor(PostProcessor pp) {
		this.pp = pp;
	}

	public FileSerializer(PostProcessor pp, DataFormatter df) {
		super();
		this.pp = pp;
		this.df = df;
	}

	/* (non-Javadoc)
	 * @see org.cbsoft.framework.Serializer#generateFile(java.lang.String, java.lang.Object)
	 */
	@Override
	public void generateFile(String filename, Object obj) {
		byte[] bytes = df.formatData(getPropertiesList(obj));
		
		try {
			bytes = pp.postProcess(bytes);	
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file",e);
		}
		
	}
	
	private Map<String, Object> getPropertiesList(Object obj) {
		Map<String,Object> props = new HashMap<String, Object>();
		Class<?> clazz = obj.getClass();
		
		for (Method m: clazz.getMethods()) {
			if (isAllawedGetter(m)) {
				try {
					Object value = m.invoke(obj);
					value = FormatValue(m, value);
					String getterName = m.getName();
					String propName = getterName.substring(3, 4).toLowerCase()
							+ getterName.substring(4);
					value = FormatValue(m, value);
					
					props.put(propName, value);
				} catch (Exception e) {
					throw new RuntimeException("Cannot retrieve properties ...");
				}
			}
		}
		return props;
	}

	private Object FormatValue(Method m, Object value)
			throws InstantiationException, IllegalAccessException {
		for(Annotation an : m.getAnnotations()) {
			Class<?> anType = an.annotationType();
			
			if (anType.isAnnotationPresent(FormatterImplementation.class)) {
				FormatterImplementation fi = anType.getAnnotation(FormatterImplementation.class);
				Class <? extends Valueformatter> c = fi.value();
				Valueformatter vf = c.newInstance();
				vf.readAnnotation(an);
				value = vf.formatValue(value);
			}
		}
		return value;
	}

	private boolean isAllawedGetter(Method m) {
		return m.getName().startsWith("get") &&
				m.getParameterTypes().length == 0 && 
				m.getReturnType() != void.class && 
				!m.getName().equals("getClass") && 
				!m.isAnnotationPresent(DontIncludeOnFIle.class);
	}
}