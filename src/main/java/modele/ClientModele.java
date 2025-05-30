package modele;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ClientModele {
    public static void main(String[] args) throws IOException {

        for(File file : new File("scenario" + File.separator).listFiles()) {
            Scenario sc0 = new Scenario(file);
            sc0.setListDouble();
            //System.out.println(villes);
            //System.out.println(distancesVilles);
            //System.out.println(villeMembres);
            //System.out.println(sc0);
            GrapheOriente g = new GrapheOriente(sc0.getVendeurListDouble(), sc0.getAcheteurListDouble());
            sc0.setTrieTopologiqueSimple(g.trieTopologique());
            sc0.setTrieTopologiqueGlouton(g.trieTopologiqueGlouton());
        }
    }
}
