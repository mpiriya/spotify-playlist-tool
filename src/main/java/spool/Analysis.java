package spool;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.enums.Modality;


public class Analysis {

  // compute the radarChart values
  // will compute the averages of: danceability, valence, energy, instrumentalness, acousticness
  public static HashMap<String, Float> radarChart(ArrayList<AudioFeatures> feet) {
    Float danceabilitySum = 0.0f;
    Float valenceSum = 0.0f;
    Float energySum = 0.0f;
    Float instrumentalSum = 0.0f;
    Float acousticSum = 0.0f;

    //
    for(AudioFeatures f : feet) {
      danceabilitySum += f.getDanceability();
      valenceSum += f.getValence();
      energySum += f.getEnergy();
      instrumentalSum += f.getInstrumentalness();
      acousticSum += f.getAcousticness();
    }

    HashMap<String, Float> toReturn = new HashMap<String, Float>();
    int size = feet.size();

    toReturn.put("Danceability", danceabilitySum / size);
    toReturn.put("Valence", valenceSum / size);
    toReturn.put("Energy", energySum / size);
    toReturn.put("Instrumentalness", instrumentalSum / size);
    toReturn.put("Acousticness", acousticSum / size);

    return toReturn;
  }

  // analysis of other features (basically the not floats)

  public static HashMap<String, String> otherFeatures(ArrayList<AudioFeatures> feet) {
    Integer keySum = 0;
    Integer minor = 0;
    Integer major = 0;
    Long durationSum = 0l; //milliseconds
    HashMap<Integer, Integer> timeSig = new HashMap<Integer, Integer>();
    Float tempoSum = 0.0f;

    for(AudioFeatures f : feet) {
      keySum += f.getKey();
      durationSum += f.getDurationMs();
      tempoSum += f.getTempo();

      if(Modality.MINOR == f.getMode()) {
        minor++;
      } else {
        major++;
      }
      
      Integer currentTimeSig = f.getTimeSignature();
      if(timeSig.containsKey(currentTimeSig)) {
        timeSig.put(currentTimeSig, timeSig.get(currentTimeSig) + 1);
      } else {
        timeSig.put(currentTimeSig, 1);
      }
    }

    int size = feet.size();
    Double avgSecs = durationSum / size / 1000.0;
    String dur = "" + (avgSecs.intValue() / 60) + ":" + String.format("%2.2f", (avgSecs % 60));
    Integer mostCommonTS = 0;
    Integer appearances = -1;
    for(Map.Entry ts : timeSig.entrySet()) {
      if((Integer) ts.getValue() > appearances) {
        mostCommonTS = (Integer) ts.getKey();
        appearances = (Integer) ts.getValue();
      }
    }

    HashMap<String, String> toReturn = new HashMap<String, String>();
    
    toReturn.put("Key", "" + (keySum / size));
    toReturn.put("Mode", (minor > major) ? "Minor" : (minor < major) ? "Major" : "Equal");
    toReturn.put("Duration", dur);
    toReturn.put("Time Signature", mostCommonTS.toString());
    toReturn.put("Tempo", "" + (tempoSum.intValue() / size));

    return toReturn;
  }
}