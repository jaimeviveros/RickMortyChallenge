package rick.morty.challenge.requests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import rick.morty.challenge.controller.CharacterController;
import rick.morty.challenge.models.entity.Character;
import rick.morty.challenge.models.entity.Origin;
import rick.morty.challenge.models.service.CharacterService;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTests {
			
	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private CharacterService characterService;
	
	private JsonSchema jsonSchema;
	
	@Before
	public void init() throws IOException, ProcessingException {
		String apiSchemaFile = "api-schema.json";
		jsonSchema = getApiSchema(apiSchemaFile);
	}
	
	/**
	 * Verificamos que es correcta la respuesta con respecto al esquema json esperado
	 * 
	 * @throws Exception
	 */
	@Test
    public void test_SuccessfulInvoke() throws Exception {
		// preparamos respuesta
		Character characterSuccessful = new Character();
		characterSuccessful.setId(1);
		characterSuccessful.setName("Rick Sanchez");
		characterSuccessful.setStatus("Alive");
		characterSuccessful.setSpecies("Human");
		characterSuccessful.setType("");
		characterSuccessful.setEpisodeCount(2);
		//
		Origin origin = new Origin();
		origin.setName("Earth (C-137)");
		origin.setUrl("https://rickandmortyapi.com/api/location/1");
		origin.setDimension("Dimension C-137");
		List<String> residents = new ArrayList<>();
		residents.add("https://rickandmortyapi.com/api/character/38");
		residents.add("https://rickandmortyapi.com/api/character/45");
		origin.setResidents(residents);
		characterSuccessful.setOrigin(origin);
		//
		Mockito.when(characterService.getCharacterInfo(1)).thenReturn(characterSuccessful);
		//
		ResultActions result = mvc.perform(get("/api/character/1")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk());
		//
		MvcResult mvcResult = result.andReturn();
		JsonNode response = JsonLoader.fromString(mvcResult.getResponse().getContentAsString());
		assertTrue("El resultado no corresponde con el esquema", jsonSchema.validate(response).isSuccess());
	}
	
	/**
	 * Validamos comportamiento cuando no existe el personaje que estamos buscando
	 * @throws Exception
	 */
	@Test
    public void test_CharacterDoesntExist() throws Exception {
		Mockito.when(characterService.getCharacterInfo(1)).thenReturn(null);
		//
		mvc.perform(get("/api/character/1")).andExpect(status().is(404));
	}
	
	/**
	 * En este caso, la respuesta no es suficiente para el esquema
	 * @throws Exception
	 */
	@Test
    public void test_ResponseNotValidForSchema() throws Exception {
		// preparamos respuesta
		Character characterNotValid = new Character();
		//
		Mockito.when(characterService.getCharacterInfo(1)).thenReturn(characterNotValid);
		//
		ResultActions result = mvc.perform(get("/api/character/1")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk());
		//
		MvcResult mvcResult = result.andReturn();
		JsonNode response = JsonLoader.fromString(mvcResult.getResponse().getContentAsString());
		assertFalse(jsonSchema.validate(response).isSuccess());
	}
	
	public File getFileFromResources(String name) {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(name).getFile());
	}
	
	public JsonSchema getApiSchema( String apiSchemaFile ) throws IOException, ProcessingException {
		File file = getFileFromResources(apiSchemaFile);
		JsonNode apiSchema = JsonLoader.fromFile(file);
		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		return factory.getJsonSchema(apiSchema);
	}
	
}
