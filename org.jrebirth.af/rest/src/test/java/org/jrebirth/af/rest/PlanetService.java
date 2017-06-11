package org.jrebirth.af.rest;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.wave.WaveItemBase;
import org.jrebirth.af.rest.service.AbstractCRUDRestService;
import org.jrebirth.af.rest.service.DefaultCRUDService;

public class PlanetService extends AbstractCRUDRestService<Planet> {

	
	@Link
	private DefaultCRUDService<Planet> service;
	
	private WaveChecker checker = w -> {
		return w.getData(ELEMENT) instanceof Planet;
	};

	@Override
	protected void initService() {
		super.initService();

		//listen(checker, DO_CREATE_OBJECT,DO_UPDATE_OBJECT);

		WaveItem<DefaultCRUDService<Planet>> item = new WaveItemBase<DefaultCRUDService<Planet>>(){
		};
		
		Class<DefaultCRUDService<Planet>> cls = (Class<DefaultCRUDService<Planet>>) item.type();
		
		UniqueKey<DefaultCRUDService<Planet>> key = Key.create(cls);
		
		try {
			Object o = cls.newInstance();
			System.out.println("");
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//DefaultCRUDService<Planet> service = localFacade().retrieve(key);
	}

}
