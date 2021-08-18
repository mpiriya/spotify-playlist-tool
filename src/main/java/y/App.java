package y;
import y.SpotifyAccess;

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
        System.out.println(spotifyaccess);

    }
}
