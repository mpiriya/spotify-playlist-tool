package spool;
import spool.SpotifyAccess;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        HashMap<String, Float> rcData = Analysis.radarChart(features);
        HashMap<String, String> otherData = Analysis.otherFeatures(features);

        for(Map.Entry<String, Float> e : rcData.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        for(Map.Entry<String, String> e : otherData.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        // System.out.println(spotifyaccess);

    }
}
