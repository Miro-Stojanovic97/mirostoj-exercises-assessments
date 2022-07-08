package corbos;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStore {

    private HashMap<String, Artist> artists = new HashMap<>();
    private HashMap<String, PlayList> playLists = new HashMap<>();
    private HashMap<String, Track> tracks = new HashMap<>();

    public DataStore() {
        load("./data/alternative_music_data.csv");
        load("./data/blues_music_data.csv");
        load("./data/hiphop_music_data.csv");
        load("./data/indie_alt_music_data.csv");
        load("./data/metal_music_data.csv");
        load("./data/pop_music_data.csv");
        load("./data/rock_music_data.csv");
    }

    public List<Artist> getArtists() {
        return new ArrayList<>(artists.values());
    }

    public List<PlayList> getPlayLists() {
        return new ArrayList<>(playLists.values());
    }

    public List<Track> getTracks() {
        return new ArrayList<>(tracks.values());
    }

    private void load(String filePath) {

        try (var reader = new CSVReaderHeaderAware(new FileReader(filePath))) {
            String[] tokens;
            while ((tokens = reader.readNext()) != null) {
                processTopLevel(tokens);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /*
    00 Artist Nam
    01 Track Nam
    02 Popularity
    03 Genre
    04 Playlist
    05 danceability
    06 energy
    07 key
    08 loudness
    09 mode
    10 speechiness
    11 acousticness
    12 instrumentalness
    13 liveness
    14 valence
    15 tempo
    16 id
    17 uri
    18 track_href
    19 analysis_url
    20 duration_ms
    21 time_signature
     */

    private void processTopLevel(String[] tokens) {

        Artist artist = artists.get(tokens[0]);
        if (artist == null) {
            artist = new Artist(tokens[0]);
            artists.put(tokens[0], artist);
        }

        PlayList playList = playLists.get(tokens[4]);
        if (playList == null) {
            playList = new PlayList(tokens[4]);
            playLists.put(tokens[4], playList);
        }

        Track track = tracks.get(tokens[16]);
        if (track == null) {
            track = processTrack(tokens);
            tracks.put(tokens[16], track);
        }

        track.setArtist(artist);
        artist.addTrack(track);
        playList.addTrack(track);
    }

    private Track processTrack(String[] tokens) {
        Track track = new Track();
        track.setName(tokens[1]);
        track.setPopularity(Integer.parseInt(tokens[2]));
        processGenres(track, tokens[3]);
        track.setDanceability(Double.parseDouble(tokens[5]));
        track.setEnergy(Double.parseDouble(tokens[6]));
        track.setKey(Integer.parseInt(tokens[7]));
        track.setTempo(new BigDecimal(tokens[15]));
        track.setIdentifier(tokens[16]);
        track.setMilliseconds(Integer.parseInt(tokens[20]));
        return track;
    }

    private void processGenres(Track track, String genres) {

        boolean isReading = false;
        int start = 0;
        char delimiter = 0;

        for (int i = 1; i < genres.length() - 1; i++) {
            char c = genres.charAt(i);
            if (!isReading) {
                if (c == '"' || c == '\'') {
                    delimiter = c;
                    start = i + 1;
                    isReading = true;
                }
            } else {
                if (c == delimiter) {
                    track.addGenre(genres.substring(start, i));
                    isReading = false;
                }
            }
        }
    }
}
