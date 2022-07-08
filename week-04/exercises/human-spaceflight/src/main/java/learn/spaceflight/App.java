package learn.spaceflight;

import learn.spaceflight.personnel.Astronaut;
import learn.spaceflight.spacecraft.MoonHopper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        // Spring code here...
        // 1. An ApplicationContext is the DI container.
        // 2. The ClassPathXmlApplicationContext is a specific type of DI container.
        // It requires an XML configuration file to register beans or object instances.
        // Here, we use "dependency-configuration.xml" but the file can be called whatever we like as long
        // as it uses the correct XML schema.
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        // 3. The container knows about an Astronaut, so we ask it for one.
        Astronaut captain = container.getBean("captain", Astronaut.class);
        Astronaut crew = container.getBean("captain", Astronaut.class);
        System.out.println("Captain: " + captain);
        System.out.println("Not the Captain: " + crew);

        MoonHopper hopper = container.getBean(MoonHopper.class);
        System.out.println(hopper);

    }
}
