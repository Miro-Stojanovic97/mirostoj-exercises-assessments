package corbos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Track {

    private static final BigDecimal THOUSAND = new BigDecimal("1000");

    private String identifier;
    private String name;
    private int popularity;
    private double danceability;
    private double energy;
    private int key;
    private BigDecimal tempo;
    private int milliseconds;

    private ArrayList<String> genres = new ArrayList<>();
    private Artist artist;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public double getDanceability() {
        return danceability;
    }

    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BigDecimal getTempo() {
        return tempo;
    }

    public void setTempo(BigDecimal tempo) {
        this.tempo = tempo;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public BigDecimal getSeconds() {
        return new BigDecimal(milliseconds).divide(THOUSAND);
    }

    public List<String> getGenres() {
        return new ArrayList<>(genres);
    }

    public void addGenre(String genre) {
        this.genres.add(genre);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", key=" + key +
                ", tempo=" + tempo +
                ", seconds=" + getSeconds() +
                ", genres=" + genres +
                ", artist=" + artist.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return popularity == track.popularity && Double.compare(track.danceability, danceability) == 0 && Double.compare(track.energy, energy) == 0 && key == track.key && milliseconds == track.milliseconds && Objects.equals(identifier, track.identifier) && Objects.equals(name, track.name) && Objects.equals(tempo, track.tempo) && Objects.equals(genres, track.genres) && Objects.equals(artist, track.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, popularity, danceability, energy, key, tempo, milliseconds, genres, artist);
    }
}
