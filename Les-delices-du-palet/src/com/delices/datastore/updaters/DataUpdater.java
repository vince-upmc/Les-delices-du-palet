package com.delices.datastore.updaters;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public abstract class DataUpdater<T> {

	protected T unmarshallContent(InputStream in, String subpackagename)
			throws JAXBException {

		final JAXBContext jc = JAXBContext
				.newInstance("com.delices.datastore.jaxb." + subpackagename);
		Unmarshaller um = jc.createUnmarshaller();

		@SuppressWarnings("unchecked")
		JAXBElement<T> st = (JAXBElement<T>) um.unmarshal(in);
		return st.getValue();
	}

	abstract void updateContent() throws UpdateFailureException;
}
