//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.models;

//Material enum
public enum Material {
    POLYSI("Multi Crystaline Silicone"),
    MONOSI("Mono Crystaline Silicone"),
    AMSI("Amorphous Silicone"),
    CDTE("Cadmium Telluride"),
    CIGS("Copper Indium Gallium Selenide");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
