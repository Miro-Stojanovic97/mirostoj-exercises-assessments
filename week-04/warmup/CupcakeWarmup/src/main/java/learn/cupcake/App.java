package learn.cupcake;

import learn.cupcake.data.Repository;
import learn.cupcake.models.Entry;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        /*

        Cupcake

        https://trends.google.com/trends/explore?date=all&q=%2Fm%2F03p1r4

        Numbers represent search interest relative to the highest point on the chart for the given region and time.
        A value of 100 is the peak popularity for the term. A value of 50 means that the term is half as popular.
        A score of 0 means there was not enough data for this term.

        1) Use manual looping to answer the following questions:

        a) Display the rankings for 2010
        b) Which month/year has the highest ranking?
        c) Which month was the first month to have a ranking of 50 or greater?
        d) Which year has the highest average ranking?

        2) Use the Streams API to answer the same questions.

         */

        Repository repository = new Repository("./data/google-trends-data.csv");

        List<Entry> entries = repository.getEntries();

        //A
        /*for(Entry e : entries) {
            if(e.getYearMonth().getYear() == 2010) {
               // System.out.println(e);
            }
        }*/

        /*List<Entry> result = entries.stream()
                .filter(entry -> entry.getYearMonth().getYear() == 2010)
                .collect(Collectors.toList());
        for(Entry entry : result) {
            //System.out.println(entry);
        }*/

       // System.out.println(entries.size());

        //B
        /*int max =  0;
        YearMonth yearMonth = null;
        for(Entry e : entries) {
            if (e.getScore() > max) {
                max = e.getScore();
                yearMonth = e.getYearMonth();
            }
        }
        System.out.println("The year with the highest score was:  ");
        System.out.println(yearMonth + " with a score of " + max);*/


     /*   int highestScoreStream = entries.stream()
                .collect(Collectors.summingInt(Entry::getScore)).getMax();

        List<Entry> highestStream = entries.stream()
                .filter(entry -> entry.getScore() == highestScoreStream)
                .collect(Collectors.toList());

        for(Entry e : highestStream) {
            System.out.println(e);
        }*/

        /*//C
        YearMonth yearMonth = null;
        for(Entry e : entries) {
            if (e.getScore() >= 50) {
                yearMonth = e.getYearMonth();
                System.out.println(yearMonth);
                break;
            }
        }*/

        //for D:
        Map<Object, Double> averageRankingResult = entries.stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getYearMonth().getYear(),
                        Collectors.averagingInt(Entry::getScore)));

        double maxAverageRanking = averageRankingResult.values().stream()
                .max(Double::compareTo).get();

        averageRankingResult.entrySet().stream()
                .filter(e -> e.getValue() == maxAverageRanking)
                .forEach(e -> System.out.printf("%s: %.2f %n", e.getKey(), e.getValue()));

    }
}
