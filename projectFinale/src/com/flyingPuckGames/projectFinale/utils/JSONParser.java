package com.flyingPuckGames.projectFinale.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JSONParser {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().addSerializationExclusionStrategy(new ExclusionStrategy() {
		
		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			 return f.getName().toLowerCase().contains("texture"); 
		}
		
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}).create();
	
	public JSONParser(){

	}
	
	public void saveOptions(Options options){
		JsonWriter writer;
		
		//writer = new JsonWriter(Gdx.files.external("projectFinale/json/options.json").writer(false));
		writer = new JsonWriter(Gdx.files.local("options.json").writer(false));
		try {
			writer.beginObject();
			writer.name("volumen").value(gson.toJson(options.getSoundVolume()));
			writer.name("volumeOn").value(gson.toJson(options.isSound()));
			writer.name("sizeX").value(gson.toJson(options.getResolutionX()));
			writer.name("sizeY").value(gson.toJson(options.getResolutionY()));
			writer.endObject();
			writer.close();	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean getOptions(Options options){
		JsonReader reader;
		String name;		
		try {
			reader = new JsonReader(Gdx.files.local("options.json").reader());
			reader.beginObject();
			while(reader.hasNext()){
				name = reader.nextName();
				if(name.equals("volumen")){
					options.setSoundVolume(reader.nextInt());
				}else if(name.equals("volumenOn")){
					options.setSound(reader.nextBoolean());
				}else if(name.equals("sizeX")){
					options.setResolutionX(reader.nextLong());
				}else if(name.equals("sizeY")){
					options.setResolutionY(reader.nextLong());
				} else {
					reader.skipValue(); 
				}
			}
			reader.endObject();
			reader.close();
		} catch (GdxRuntimeException e) {
			return false;
		} catch (IOException e1){
			return false;
		}
		return true;
	}
	
	public void savePlayer(Player player){

		try {
			FileWriter writer1 = new FileWriter(Gdx.files.local("save.json").path(),false);
			String json = gson.toJson(player);
			writer1.write(json);
			writer1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Player loadPlayer(){
		
		BufferedReader br = new BufferedReader(Gdx.files.local("save.json").reader());
		Player jugador = new Player();
		jugador = gson.fromJson(br, Player.class);
		
		return jugador;
		
	}
	
	
	
}
