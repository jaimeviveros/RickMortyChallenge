package rick.morty.challenge.service.adapter.entity;

import java.io.Serializable;

public class OriginApi implements Serializable {

	private static final long serialVersionUID = 1692793194978250363L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
