package com.flyingPuckGames.projectFinale.controller;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.flyingPuckGames.projectFinale.MegaGame;

/**
 * A service that manages the background music.
 * <p>
 * Only one music may be playing at a given time.
 */
public class MusicController implements Disposable {
    
	
	private MegaGame megaGame;
	private boolean musicPlaying;
	
	public MusicController(MegaGame megaGame){
		this.megaGame = megaGame;
	}
	
	/**
     * The available music files.
     */
	
    public enum Tracks{
        MENU("music/music.mp3"),
        STEP("music/music.mp3"),
        JUMP("music/music.mp3"),
        LAND("music/collision.wav");


        private final String fileName;

        private Tracks(String fileName){
            this.fileName = fileName;
        }

        public String getFileName(){
            return fileName;
        }
    }

    /**
     * Holds the music currently being played, if any.
     */
    private Music musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float volume = 1f;

    /**
     * Whether the music is enabled.
     */
    private boolean enabled = true;

    /**
     * Creates the music manager.
     */
    public MusicController() {
    }

    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     */
    public void play(Tracks music, boolean loop) {
        // check if the music is enabled
        if (!enabled) return;

        // stop any music being played
        Gdx.app.log("MusicLog", "Playing music: " + music.name());
        stop();

        // start streaming the new music
        FileHandle musicFile = Gdx.files.internal(music.getFileName());
        musicBeingPlayed = Gdx.audio.newMusic(musicFile);
        musicBeingPlayed.setVolume(volume);
        musicBeingPlayed.setLooping(loop);
        musicBeingPlayed.play();
    }

    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop(){
        if (musicBeingPlayed != null) {
            Gdx.app.log( "MusicLog", "Stopping current music" );
            musicBeingPlayed.stop();
            musicBeingPlayed.dispose();
        }
    }

    /**
     * Sets the music volume which must be inside the range [0,1].
     */
    public void setVolume(float volume){
        Gdx.app.log("MusicLog", "Adjusting music volume to: " + volume);

        // check and set the new volume
        if (volume < 0 || volume > 1f) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;
        megaGame.getOptions().setSoundVolume((int) (volume * 10));
        // if there is a music being played, change its volume
        if (musicBeingPlayed != null) {
            musicBeingPlayed.setVolume(volume);
        }
    }

    /**
     * Enables or disabled the music.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
        megaGame.getOptions().setSound(enabled);
        // if the music is being deactivated, stop any music being played
        if (!enabled) {
            stop();
        }
    }

    /**
     * Disposes the music manager.
     */
    public void dispose(){
        Gdx.app.log("MusicLog", "Disposing music manager");
        stop();
    }

	public boolean isEnabled() {
		return enabled;
	}
}
