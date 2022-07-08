package corbos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {

    private final String name;
    private ArrayList<Track> tracks = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return new ArrayList<>(tracks);
    }

    public void addTrack(Track track) {
        if (!tracks.stream().anyMatch(t -> t.getIdentifier().equals(track.getIdentifier()))) {
            tracks.add(track);
        }
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) && Objects.equals(tracks, artist.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tracks);
    }
}
