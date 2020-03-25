package coronavirusAPITest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestTest {

	@Test
	public void shouldReturnAllCases() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
				.asJson();
		
		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}
	
	@Test
	public void shouldReturnCaseByCountry() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
				.asJson();
		
		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}

	@Test
	public void deberiaRetornarUnCodigo202EnUnGetCountry() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://orlando-gelves-arsw-t2.herokuapp.com/covid19/getCasesByCountry/Canada")
				.asJson();

		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}

	@Test
	public void deberiaRetornarUnCodigo202EnUnGetAll() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://orlando-gelves-arsw-t2.herokuapp.com/covid19/getAllCases")
				.asJson();

		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}

	@Test
	public void deberiaRetornarColombia() throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("https://orlando-gelves-arsw-t2.herokuapp.com/covid19/getCasesByCountry/Colombia")
				.asString();
		JSONArray respuesta = new JSONArray(response.getBody());
		JSONObject informacionColombia = (JSONObject) respuesta.get(0);
		assertNotNull(informacionColombia);
		assertEquals("Colombia",informacionColombia.get("country"));
	}

	@Test
	public void deberiaRetornarLas33ProvinciasDeChina() throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("https://orlando-gelves-arsw-t2.herokuapp.com/covid19/getCasesByCountry/China")
				.asString();
		JSONArray respuesta = new JSONArray(response.getBody());
		assertNotNull(respuesta);
		assertEquals(33,respuesta.length());
	}
	@Test
	public void deberiaRetornarLos169PaisesMapeadasEnElApi() throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("https://orlando-gelves-arsw-t2.herokuapp.com/covid19/getAllCases")
				.asString();
		JSONArray respuesta = new JSONArray(response.getBody());
		assertNotNull(respuesta);
		assertEquals(169,respuesta.length());
	}

	@Test
	public void deberiaRetornarLas3417ProviniciasTotalesEnLaAPI() throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
				.asString();
		JSONObject json = new JSONObject(response.getBody());
		JSONObject data = new JSONObject(json.get("data").toString());
		JSONArray covid19Stats = new JSONArray(data.get("covid19Stats").toString());
		assertNotNull(covid19Stats);
		assertEquals(3417,covid19Stats.length());
	}
}
