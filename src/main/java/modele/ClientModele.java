package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientModele {
    public static void main(String[] args) throws IOException {

        Scenario sc0 = new Scenario(new File("scenario" + File.separator + "scenario_0.txt"));
        sc0.setListDouble();
        //System.out.println(sc0);
        GrapheOriente g = new GrapheOriente(sc0.getVendeurListDouble(), sc0.getAcheteurListDouble());
        ArrayList<String> trie =  g.trieTopologique();
    }
}
