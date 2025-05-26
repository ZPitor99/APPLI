package modele;

import java.io.File;
import java.io.IOException;

import static modele.LectureDistance.distancesVilles;
import static modele.LectureDistance.villes;
import static modele.LectureMembre.villeMembres;

public class ClientModele {
    public static void main(String[] args) throws IOException {

        Scenario sc0 = new Scenario(new File("scenario" + File.separator + "scenario_0.txt"));
        sc0.setListDouble();
        //System.out.println(villes);
        //System.out.println(distancesVilles);
        //System.out.println(villeMembres);
        //System.out.println(sc0);
        GrapheOriente g = new GrapheOriente(sc0.getVendeurListDouble(), sc0.getAcheteurListDouble());
        sc0.setTrieTopologiqueSimple(g.trieTopologique());
    }
}
