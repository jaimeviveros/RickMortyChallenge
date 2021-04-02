package rick.morty.challenge.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rick.morty.challenge.service.adapter.entity.CharacterApi;
import rick.morty.challenge.service.adapter.entity.LocationApi;


@FeignClient(name = "rickandmortyapi", url = "https://rickandmortyapi.com/api")
public interface RickMortyRestClient {
	
	@GetMapping("/character/{id}")
	public CharacterApi getCharacter(@PathVariable Integer id);
	
	@GetMapping("/location/{id}")
	public LocationApi getLocation(@PathVariable Integer id);

}
