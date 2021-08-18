package spool;
import spool.SpotifyAccess;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.knowm.xchart.RadarChart;
import org.knowm.xchart.SwingWrapper;

public class App 
{
    public static void main( String[] args )
    {
        SpotifyAccess spotifyaccess = new SpotifyAccess("/client.json");
        spotifyaccess.accessToken();

        String[] plids = spotifyaccess.getPlaylistTrackIds("4nntO0cFWLeM2vQrIoZq8o");
        AudioFeatures[] features = spotifyaccess.getAudioFeatures(plids);

        HashMap<String, Float> rcData = Analysis.radarChart(features);
        HashMap<String, String> otherData = Analysis.otherFeatures(features);

        for(Map.Entry<String, Float> e : rcData.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        for(Map.Entry<String, String> e : otherData.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        // System.out.println(spotifyaccess);

        RadarChart rc = Charts.makeChart(rcData);
        
        new SwingWrapper(rc).displayChart();
    }
}
