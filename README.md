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

2. TODO

3. Finally, run `mvn clean compile` and then `mvn exec:java -Dexec.mainClass="y.App";`

## Dependencies

* JSON
* Spotify Web App Java Wrapper