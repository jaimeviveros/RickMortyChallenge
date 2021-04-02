package rick.morty.challenge.service.adapter.entity;

import java.io.Serializable;
import java.util.List;

public class CharacterApi implements Serializable {

	private static final long serialVersionUID = -8606611410868314019L;

	private int id;
	private String name;
	private String status;
	private String species;
	private String type;
	private OriginApi origin;
	private List<String> episode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OriginApi getOrigin() {
		return origin;
	}

	public void setOrigin(OriginApi origin) {
		this.origin = origin;
	}

	public List<String> getEpisode() {
		return episode;
	}

	public void setEpisode(List<String> episode) {
		this.episode = episode;
	}

}
