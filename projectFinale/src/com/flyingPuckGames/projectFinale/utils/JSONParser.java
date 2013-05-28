package com.flyingPuckGames.projectFinale.utils;

import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.SerializationException;
import com.flyingPuckGames.projectFinale.model.Options;


public class JSONParser {
	
	public JSONParser(){

	}
	
	public void saveOptions(Options options){
		JsonWriter writer;
		
		writer = new JsonWriter(Gdx.files.external("projectFinale/json/options.json").writer(false));
		
		try {
			writer.object();
			writer.name("volumen").value(options.getSoundVolume());
			writer.name("volumeOn").value(options.isSound());
			writer.name("sizeX").value(options.getResolutionX());
			writer.name("sizeY").value(options.getResolutionY());
			writer.close();	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean getOptions(Options options){
		JsonReader reader;
		
		reader = new JsonReader();
		try{
			reader.parse(Gdx.files.external("projectFinale/json/options.json"));
		}catch(SerializationException e){
			return false;
		}
		
		options.setSoundVolume(reader.parse(Gdx.files.internal("json/options.json")).getInt("volumen"));
		options.setResolutionX(reader.parse(Gdx.files.internal("json/options.json")).getFloat("sizeX"));
		options.setResolutionY(reader.parse(Gdx.files.internal("json/options.json")).getFloat("sizeY"));
		options.setSound(reader.parse(Gdx.files.internal("json/options.json")).getBoolean("volumeOn"));
		return true;
	}
	
	
	
}
