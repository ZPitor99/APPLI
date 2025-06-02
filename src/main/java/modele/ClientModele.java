package modele;

import java.io.File;
import java.io.IOException;

public class ClientModele {
    public static void main(String[] args) throws IOException {
        Scenario sc0 = new Scenario(new File("scenario" + File.separator + "scenario_3.txt"));
        sc0.setListDouble();
        GrapheOriente g = new GrapheOriente(sc0.getVendeurListDouble(), sc0.getAcheteurListDouble());
        sc0.setTrieTopologiqueSimple(g.trieTopologique());
        sc0.setTrieTopologiqueGlouton(g.trieTopologiqueGlouton());
        sc0.setTrieTopologiqueOptimal(g.trieTopologiqueOptimal(5));
        System.out.println(sc0.trieTopologiqueOptimal + "\n" + sc0.trieTopologiqueOptimalLongueur);
    }
}
