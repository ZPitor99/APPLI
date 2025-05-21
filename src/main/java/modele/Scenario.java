package modele;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    public List<String> vendeurList;
    public List<String> acheteurList;

    public Scenario() {
        vendeurList = new ArrayList<>();
        acheteurList = new ArrayList<>();
    }

    public Scenario(List<String> vendeurList, List<String> acheteurList) {
        this.vendeurList = vendeurList;
        this.acheteurList = acheteurList;
    }

    public String toString() {
        return vendeurList.toString() + "\n" + acheteurList.toString();
    }
}
