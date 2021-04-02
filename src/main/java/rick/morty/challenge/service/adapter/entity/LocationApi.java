package rick.morty.challenge.service.adapter.entity;

import java.io.Serializable;
import java.util.List;

public class LocationApi implements Serializable {

	private static final long serialVersionUID = 6709096240535318520L;

	private String name;
	private String url;
	private String dimension;
	private List<String> residents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<String> getResidents() {
		return residents;
	}

	public void setResidents(List<String> residents) {
		this.residents = residents;
	}

}
