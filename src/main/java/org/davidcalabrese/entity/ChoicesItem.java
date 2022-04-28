package org.davidcalabrese.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generated POJO, represents a nested object within the OpenAIResponse
 */
public class ChoicesItem{

	@JsonProperty("finish_reason")
	private String finishReason;

	@JsonProperty("index")
	private int index;

	@JsonProperty("text")
	private String text;

	@JsonProperty("logprobs")
	private Object logprobs;

	public String getFinishReason(){
		return finishReason;
	}

	public int getIndex(){
		return index;
	}

	public String getText(){
		return text;
	}

	public Object getLogprobs(){
		return logprobs;
	}
}