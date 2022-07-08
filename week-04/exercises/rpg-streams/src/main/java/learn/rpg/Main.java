package learn.rpg;

import learn.rpg.data.NameRepository;
import learn.rpg.data.PlayerRepository;
import learn.rpg.domain.PlayerService;
import learn.rpg.models.Player;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // getPlayers()
        //Stream<Player> playerStream = getPlayers().stream();

        /*// getPlayers() from Thailand
        getPlayers().stream()
                .filter(player -> player.getCountry().equalsIgnoreCase("Thailand"))
                .forEach(System.out::println);*/

        /*// getPlayers() starting w B
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .forEach(System.out::println);*/

        //Display 1000 players
        //playerStream.forEach(System.out::println);
        //playerStream.forEach(player -> System.out.println(player));

        // Skip over 100 of the 106 players who have a last name that starts with "B"
        // and print the last 6.
        /*getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .skip(100)
                .forEach(System.out::println);*/

        // Only print the first 5 players who have a last name that starts with "B".
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .limit(5)
                .forEach(System.out::println);

        /*// Skip the first 500 players, then print the next 10 players.
        getPlayers().stream()
                .skip(500)
                .limit(10)
                .forEach(System.out::println);*/
    }

    static List<Player> getPlayers() {
        PlayerRepository playerRepo = new PlayerRepository("players.csv");
        NameRepository nameRepo = new NameRepository("characters.csv");
        PlayerService service = new PlayerService(playerRepo, nameRepo);
        return service.generate();
    }
}
