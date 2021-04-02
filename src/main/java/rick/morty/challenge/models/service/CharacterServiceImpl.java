package rick.morty.challenge.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rick.morty.challenge.helper.LocationHelper;
import rick.morty.challenge.models.entity.Character;
import rick.morty.challenge.models.entity.Origin;
import rick.morty.challenge.service.adapter.CharacterAdapterService;
import rick.morty.challenge.service.adapter.LocationAdapterService;

@Service
public class CharacterServiceImpl implements CharacterService {

	@Autowired
	private CharacterAdapterService characterAdapterService;
	
	@Autowired
	private LocationAdapterService locationAdapter; 
	
	@Override
	public Character getCharacterInfo(Integer id) {
		Character character = characterAdapterService.getCharacterInfo(id);
		if (character != null && character.getOrigin() != null) {
			String url = character.getOrigin().getUrl();
			Integer idLocation = LocationHelper.getIdLocationByUrl(url);
			//
			Origin origin = locationAdapter.getLocationInfo(idLocation);
			character.setOrigin(origin);
		}
		return character;
	}
	

}