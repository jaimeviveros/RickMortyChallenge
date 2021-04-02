package rick.morty.challenge.helper;

public class LocationHelper {

	public static Integer getIdLocationByUrl(String url) {
		Integer id = null;
		try {
			//
			if (url != null && !url.isEmpty()) {
				String idByUrl = url.substring(url.lastIndexOf("/")+1);
				id = Integer.valueOf(idByUrl);
			}
			//
			
		} catch (NumberFormatException e) {
			System.out.println("El id no es un numero");
		}
		return id;
	}
	
}
