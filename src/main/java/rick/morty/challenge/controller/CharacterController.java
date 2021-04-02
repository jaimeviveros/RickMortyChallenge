package rick.morty.challenge.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rick.morty.challenge.models.entity.Character;

import rick.morty.challenge.models.service.CharacterService;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
	
	@Autowired
	private CharacterService characterService;
	
	@GetMapping("/{id}")
	public @ResponseBody Character get(HttpServletResponse response, @PathVariable Integer id) {
		Character character = characterService.getCharacterInfo(id);
		if (character == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return character;
	}

}
