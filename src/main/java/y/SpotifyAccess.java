package y;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;
import org.apache.hc.core5.http.ParseException;
import org.json.*;

import java.util.ArrayList;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpotifyAccess {
  private final SpotifyApi spotifyapi;
  // private static final SpotifyApi spotifyapi = new SpotifyApi.Builder()
  //   .setClientId(clientID)
  //   .setClientSecret(clientSecret)
  //   .build();

  public SpotifyAccess(String filename) {
    String clientId = null;
    String clientSecret = null;
    try {
      URL url = getClass().getResource(filename);
      File json = new File(url.getPath());
      Scanner sc = new Scanner(json);
      String contents = "";
      while(sc.hasNextLine()) {
        contents += sc.nextLine();
      }
      JSONObject j = new JSONObject(contents);

      clientId = j.getString("client-id");
      clientSecret = j.getString("client-secret");
      
    } catch(FileNotFoundException e) {
      System.out.println("Error:  " + e.getMessage());
    } finally {
      if(clientId != null) {
        this.spotifyapi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientSecret(clientSecret)
          .build();
      } else {
        this.spotifyapi = null;
      }
    }
  }

  // adds access token to the spotifyapi, furthering its abilities
  public void accessToken() {
    try {
      if(spotifyapi != null) {
        ClientCredentialsRequest ccr = spotifyapi.clientCredentials()
          .build();
        final ClientCredentials clientcred = ccr.execute();

        spotifyapi.setAccessToken(clientcred.getAccessToken());
      } else {
        System.out.println("Error: ClientID/Secret invalid");
      }
    } catch(IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private PlaylistTrack[] getPlaylist(String playlistId) {
    try {
      GetPlaylistsItemsRequest pliReq = spotifyapi.getPlaylistsItems(playlistId)
        .build();
      final Paging<PlaylistTrack> paging = pliReq.execute();

      return paging.getItems();
    } catch(IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return null;
  }

  public ArrayList<String> getPlaylistTrackIds(String playlistId) {
    PlaylistTrack[] pltracks = getPlaylist(playlistId);
    ArrayList<String> toReturn = new ArrayList<String>();

    for(PlaylistTrack t : pltracks) {
      toReturn.add(t.getTrack().getId());
    }

    return toReturn;
  }

  //testing async owo
  public ArrayList<AudioFeatures> getAudioFeatures(ArrayList<String> plIds) {
    try {
      ArrayList<CompletableFuture<AudioFeatures>> futures = new ArrayList<CompletableFuture<AudioFeatures>>();
      ArrayList<AudioFeatures> toReturn = new ArrayList<AudioFeatures>();
      GetAudioFeaturesForTrackRequest req;

      for(String id : plIds) {
        req = spotifyapi
          .getAudioFeaturesForTrack(id)
          .build();
        futures.add(req.executeAsync());
      }

      for(CompletableFuture<AudioFeatures> afs : futures) {
        toReturn.add(afs.join());
      }

      return toReturn;
    } catch(CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch(CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
    return null;
  }

  public static void main(String[] args) {

  }
}