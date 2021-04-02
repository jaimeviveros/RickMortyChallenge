package rick.morty.challenge.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rick.morty.challenge.clients.RickMortyRestClient;
import rick.morty.challenge.models.entity.Origin;
import rick.morty.challenge.service.adapter.entity.LocationApi;

@Service
public class LocationAdapterServiceImpl implements LocationAdapterService {

	@Autowired
	private RickMortyRestClient rickMortyRestClient;
	
	@Override
	public Origin getLocationInfo(Integer id) {
		Origin origin = null;
		LocationApi locationApi = rickMortyRestClient.getLocation(id);
		//
		if (locationApi != null) {
			origin = new Origin();
			origin.setName(locationApi.getName());
			origin.setDimension(locationApi.getDimension());
			origin.setUrl(locationApi.getUrl());
			origin.setResidents(locationApi.getResidents());
		}
		//
		return origin;
	}

}
