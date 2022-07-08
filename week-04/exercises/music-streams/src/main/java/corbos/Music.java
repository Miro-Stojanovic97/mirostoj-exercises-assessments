package corbos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Music {

    public static void main(String[] args) {

        var dataStore = new DataStore();

        //List<Artist> artists = dataStore.getArtists();
//        artists.stream()
//                .filter(a -> a.getName().startsWith("Pink"))
//                .filter(a -> a.getTracks().stream().anyMatch(t -> t.getPopularity() > 50))
//                .forEach(System.out::println);

//        var mostDanceableTrack = artists.stream()
//                .flatMap(a -> a.getTracks().stream())
//                .filter(t -> t.getDanceability() > 0.75)
//                .sorted(Comparator.comparingDouble(Track::getDanceability).reversed())
//                .findFirst();
//
//        if(mostDanceableTrack.isPresent()) {
//            System.out.println(mostDanceableTrack.get());
//        }

//        artists.stream()
//                .flatMap(a -> a.getTracks().stream())
//                .filter(t -> t.getDanceability() > 0.75)
//                .map(t -> new TrackStats(t.getName(), t.getDanceability(), t.getEnergy()))
//                .forEach(System.out::println);
//
//        var things = artists.stream()
//                .map(a -> a.getName())
//                .map(name -> name.toCharArray())
//                .map(charArr -> charArr[0]);

        dataStore.getTracks().stream()
                .filter(t -> t.getDanceability() > 0.75)
                .collect(Collectors.groupingBy(
                        Track::getKey, Collectors.averagingDouble(Track::getDanceability))).entrySet().stream()
                .sorted((b,a) -> Double.compare(a.getValue(), b.getValue()))
                .forEach(entry -> System.out.printf("%s: %s%n", entry.getKey(), entry.getValue()));


        // 1. filter
        // 2. sort/order
        // 3. transform/map/project
        // 4. aggregate/group

       /* static boolean startsWithPink(Artist artist) {
            return artist.getName().startsWith("Pink");*/
        }
    }


