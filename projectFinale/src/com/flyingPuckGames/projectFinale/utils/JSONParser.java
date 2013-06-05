package com.flyingPuckGames.projectFinale.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.model.enemy.Enemy;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


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
	
	public void saveEnemy(){
		JsonWriter writer;		
		writer = new JsonWriter(Gdx.files.local("enemys.json").writer(false));
		writer.setIndent(" ");
		try {
			writer.beginObject();
			for(int id = 0; id < 10; id++){	
				writer.name("enemy" + id).beginObject();
					writer.name("name").value("enemy" + id);
					writer.name("level").value(id);
					writer.name("hp").value(id);
					writer.name("id").value(id);
					writer.name("strongAgainst").value("strong" + id);
					writer.name("inmuneAgainst").value("inmune" + id);
					writer.name("weakAgainst").value("weak" + id);
					writer.name("absorb").value("absorb" + id);
					writer.name("drop").value("item" + id);
					writer.name("exp").value(id);
					writer.name("desc").value("enemyDesc" + id);
					writer.endObject();
			}
			writer.endObject();
			writer.close();	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public Enemy getEnemy(int id){
		JsonReader reader;
		String name;	
		Enemy enemy = new Enemy();
		try {
			reader = new JsonReader(Gdx.files.internal("json/enemys.json").reader());
			reader.beginObject();
			while(reader.hasNext()){
				
				name = reader.nextName();
				
				if(name.equals("enemy" + id)){
					System.out.println(name);
					reader.beginObject();
						while(reader.hasNext()){
							name = reader.nextName();
							System.out.println(name);
							if(name.equals("name")){
								enemy.setName(reader.nextString());
							}else if(name.equals("level")){
								enemy.setLevel(reader.nextInt());
							}else if(name.equals("hp")){
								enemy.setHp(reader.nextInt());
							}else if(name.equals("id")){
								enemy.setId(reader.nextInt());
							}else if(name.equals("strongAgainst")){
								enemy.setStrongAgainst(reader.nextString());
							}else if(name.equals("inmuneAgainst")){
								enemy.setInmuneAgainst(reader.nextString());
							}else if(name.equals("weakAgainst")){
								enemy.setWeakAgainst(reader.nextString());
							}else if(name.equals("absorb")){
								enemy.setAbsorb(reader.nextString());
							}else if(name.equals("drop")){
								enemy.setDrop(reader.nextString());
							}else if (name.equals("exp")){
								enemy.setExp(reader.nextLong());
							}else if(name.equals("desc")){
								enemy.setDesc(reader.nextString());
							}
						}
						reader.endObject();
				}else{
					reader.skipValue();
				}
			}
			reader.endObject();
			reader.close();
		} catch (GdxRuntimeException e) {
		} catch (IOException e1){
		}
		
		System.out.println(enemy.toString());
		return enemy;
		
		
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
