package com.doctusoft.smiling.guice;

import java.util.HashSet;
import java.util.Set;

import com.doctusoft.smiling.hospital.HospitalApi;
import com.doctusoft.smiling.security.RequestFilter;
import com.doctusoft.smiling.smile.SmileApi;
import com.doctusoft.smiling.user.SmilingUserApi;
import com.doctusoft.smiling.equipment.EquipmentApi;
import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;

public class SmilingSystemServiceServletModule extends GuiceSystemServiceServletModule {

	@Override
	protected void configureServlets() {
		super.configureServlets();

		bind(ObjectifyFilter.class).in(Singleton.class);
		filter("/*").through(ObjectifyFilter.class);
		filter("/*").through(RequestFilter.class);

		Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
		serviceClasses.add(HospitalApi.class);
		serviceClasses.add(SmilingUserApi.class);
		serviceClasses.add(EquipmentApi.class);
		serviceClasses.add(SmileApi.class);
		this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
	}
}
