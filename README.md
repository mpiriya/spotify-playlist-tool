# Spotify Playlist Tool

A Spotify tool written in Java that will analyze a given playlist and display a radar chart describing the playlist in terms of Spotify's music characteristics including 

* Danceability
* Valence
* Energy
* Tempo
* Instrumentalness
* Acousticness

as well as figures describing trends in

* Key
* Mode
* Duration
* Time Signature


## Usage

1. In the `src/main/resources` directory, add a `.json` file that contains `client-id` and `client-secret` string objects.

2. Add the playlist ID in `App.java`

3. Finally, run `mvn clean compile` and then `mvn exec:java -Dexec.mainClass="y.App";`

4. A popup will appear with a radar chart describing the playlist

## Todo

1. Web Server

2. Search artists to see their chart, ability to select an album to see its specific chart

3. Login to see user's playlists (public and private)

4. marketing: GOOGLE ADS for $$

## Dependencies

* JSON
* Spotify Web App Java Wrapper