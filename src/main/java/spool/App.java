package spool;
import spool.SpotifyAccess;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Spotify Client ID: ");
        // String id = sc.nextLine();
        // System.out.println("Spotify Client Secret: ");
        // String sec = sc.nextLine();
        SpotifyAccess spotifyaccess = new SpotifyAccess("/client.json");
        spotifyaccess.accessToken();

        ArrayList<String> plids = spotifyaccess.getPlaylistTrackIds("75xfeRCvAy3hhYGfjuOl4S");
        ArrayList<AudioFeatures> features = spotifyaccess.getAudioFeatures(plids);
        // System.out.println(spotifyaccess);

    }
}
