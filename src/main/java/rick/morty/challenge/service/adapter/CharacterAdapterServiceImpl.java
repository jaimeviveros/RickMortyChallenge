package rick.morty.challenge.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rick.morty.challenge.clients.RickMortyRestClient;
import rick.morty.challenge.models.entity.Character;
import rick.morty.challenge.models.entity.Origin;
import rick.morty.challenge.service.adapter.entity.CharacterApi;
import rick.morty.challenge.service.adapter.entity.OriginApi;

@Service
public class CharacterAdapterServiceImpl implements CharacterAdapterService {

	@Autowired
	private RickMortyRestClient rickMortyRestClient;
	
	@Override
	public Character getCharacterInfo(Integer id) {
		Character character = null;
		//
		CharacterApi characterApi = rickMortyRestClient.getCharacter(id);
		//
		if (characterApi != null) {
			character = new Character();
			character.setId(characterApi.getId());
			character.setName(characterApi.getName());
			character.setStatus(characterApi.getStatus());
			character.setSpecies(characterApi.getSpecies());
			character.setType(characterApi.getType());
			//
			Integer episodeCount = characterApi.getEpisode() != null ? characterApi.getEpisode().size() : 0;
			character.setEpisodeCount(episodeCount);
			//
			OriginApi originApi = characterApi.getOrigin();
			if (originApi != null) {
				Origin originCharacter = new Origin();
				originCharacter.setUrl(originApi.getUrl());
				//
				character.setOrigin(originCharacter);
			}
		}
		//
		return character;
	}

}
