package org.cbsoft.application;

import java.lang.annotation.Annotation;

import org.cbsoft.framework.Valueformatter;

public class Uppercaseformatter implements Valueformatter {

	@Override
	public Object formatValue(Object value) {
		return value.toString().toUpperCase();
	}

	@Override
	public void readAnnotation(Annotation an) {
	}

}
