package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Global constants.
 * @author flyingPuck(games);
 */

public class Constants {
	
	//TileMap size
	public static final float TOTAL_WIDTH_IN_TILES 		= 20f;
	public static final float TOTAL_HEIGHT_IN_TILES 	= 11f;
	public static final float TILE_UNIT_SIZE 			= 16f;
	
	//Player sizes
	public static final float PLAYER_WIDTH_IN_UNITS 	= .999f / TILE_UNIT_SIZE;
	public static final float PLAYER_HEIGHT_IN_UNITS 	= PLAYER_WIDTH_IN_UNITS * 2;

	//Resources paths
	public static final String TEST_TILEMAP_PATH 		= "maps/plahTilemap.tmx";
	public static final String TEST_PLAYER_TEXTURE_PATH = "data/plahCharacter.png";
	public static final String TEST_BACKGROUND_PATH 	= "maps/background.png";
	
	//Options
	public static final float RESOLUTION_X 	= 1280f;
	public static final float RESOLUTION_Y 	= 720f;
	public static final boolean SOUND 		= true;
	public static final Short SOUND_VOLUME 	= 10;
	public static final Integer[][] RESOLUTIONS = { {800,600},{1024,768},{1280,1024},{1366,768},{1600,1024}};

	//Test positions.
	public static final Vector2 PLAYER_STARTING_POSITION = new Vector2(2,3);
	 
}
