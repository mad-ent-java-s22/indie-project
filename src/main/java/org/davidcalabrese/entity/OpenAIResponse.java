package org.davidcalabrese.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Generated POJO representing response of requests to the OpenAI API
 */
public class OpenAIResponse{

	@JsonProperty("created")
	private int created;

	@JsonProperty("model")
	private String model;

	@JsonProperty("id")
	private String id;

	@JsonProperty("choices")
	private List<ChoicesItem> choices;

	@JsonProperty("object")
	private String object;

	public int getCreated(){
		return created;
	}

	public String getModel(){
		return model;
	}

	public String getId(){
		return id;
	}

	public List<ChoicesItem> getChoices(){
		return choices;
	}

	public String getObject(){
		return object;
	}
}