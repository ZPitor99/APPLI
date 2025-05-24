package modele;

import java.io.File;
import java.io.IOException;

public class ClientModele {
    public static void main(String[] args) throws IOException {

        Scenario sc0 = new Scenario(new File("scenario" + File.separator + "scenario_0.txt"));
        System.out.println(sc0);
        LectureDistance distance = new LectureDistance();
        distance.afficherMatrice();
        LectureMembre membre = new LectureMembre();
        System.out.println(membre);
    }
}
