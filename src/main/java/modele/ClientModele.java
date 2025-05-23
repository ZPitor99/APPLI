package modele;


import java.io.File;
import java.io.IOException;

import static modele.LectureScenario.lectureScenario;

public class ClientModele {
    public static void main(String[] args) throws IOException {

        Scenario sn = lectureScenario(new File("scenario" + File.separator + "scenario_0.txt"));
        System.out.println(sn);
        LectureDistance distance = new LectureDistance();
        distance.afficherMatrice();
        LectureMembre membre = new LectureMembre();
    }
}
