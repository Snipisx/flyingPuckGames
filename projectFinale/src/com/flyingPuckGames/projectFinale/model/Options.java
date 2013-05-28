package com.flyingPuckGames.projectFinale.model;

import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.JSONParser;

public class Options {
	
	
	private float resolutionX;
	private float resolutionY;
	private boolean sound;
	private int soundVolume;
	private JSONParser parser;
	
	public Options(){
		parser = new JSONParser();
	}
	
	
	public void saveOptions(){
		parser.saveOptions(this);
	}
	
	public void loadOptions(){
		if(parser.getOptions(this)){
			System.out.println("Options loaded \n" + "Sound = " + isSound());
			return;
		}else{
			
			defaultOptions();
		}
	}
	
	private void defaultOptions(){
		System.out.println("Options default");
		setResolutionX(Constants.RESOLUTION_X);
		setResolutionY(Constants.RESOLUTION_Y);
		setSound(Constants.SOUND);
		setSoundVolume(Constants.SOUND_VOLUME);
		
		parser.saveOptions(this);
	}
	
	public void setVideoOptions(float resolutionX,float resolutionY){
		this.setResolutionX(resolutionX);
		this.setResolutionY(resolutionY);
	}
	
	public void setAudioOptions(Integer volumeLvl, Boolean soundOn){
		setSound(soundOn);
		setSoundVolume(volumeLvl);
	}


	public int getSoundVolume() {
		return soundVolume;
	}


	public void setSoundVolume(int soundVolume) {
		this.soundVolume = soundVolume;
	}


	public boolean isSound() {
		return sound;
	}


	public void setSound(boolean sound) {
		this.sound = sound;
	}


	public float getResolutionX() {
		return resolutionX;
	}


	public void setResolutionX(float resolutionX) {
		this.resolutionX = resolutionX;
	}


	public float getResolutionY() {
		return resolutionY;
	}


	public void setResolutionY(float resolutionY) {
		this.resolutionY = resolutionY;
	}
}
