package corbos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayList {

    private final String name;
    private ArrayList<Track> tracks = new ArrayList<>();

    public PlayList(String name) {
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
        return "PlayList{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return Objects.equals(name, playList.name) && Objects.equals(tracks, playList.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tracks);
    }
}
