package y;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;
import org.json.*;

import java.net.URL;
import java.util.Scanner;
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

        System.out.println(clientId + "\n" + clientSecret);
      } else {
        this.spotifyapi = null;
      }
    }
  }

  

  //import cliientID/secret from filename, which has to be relative to the home directory of the project, not where the .java files are written

  // adds access token to the spotifyapi, furthering its abilities
  public void accessToken() {
    try {

      
      ClientCredentialsRequest ccr = spotifyapi.clientCredentials()
        .build();
      final ClientCredentials clientcred = ccr.execute();

      spotifyapi.setAccessToken(clientcred.getAccessToken());
      System.out.println(spotifyapi.getAccessToken());
    } catch(IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void getPlaylist() {
    
  }

  public static void main(String[] args) {

  }
}