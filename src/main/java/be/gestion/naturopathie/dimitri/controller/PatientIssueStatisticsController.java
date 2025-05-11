package be.gestion.naturopathie.dimitri.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import be.gestion.naturopathie.dimitri.repository.PatientIssueStatisticsRepository;


/**                                                                    
 _____ _       _   _     _   _         _    _____ _____ _____ __    
|   __| |_ ___| |_|_|___| |_|_|___ ___| |  |     |_   _| __  |  |   
|__   |  _| .'|  _| |_ -|  _| |  _| .'| |  |   --| | | |    -|  |__ 
|_____|_| |__,|_| |_|___|_| |_|___|__,|_|  |_____| |_| |__|__|_____|
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
**/



/**
 * Contrôleur permettant l'affichage des statistiques sur le nombre de problèmes par patient..
 */
@Controller
public class PatientIssueStatisticsController {

    @Autowired
    private PatientIssueStatisticsRepository statisticsRepository;

    /**
     * Affiche la page HTML contenant les statistiques des problèmes rencontrés par les patients.
     *
     * @param model Le modèle utilisé pour injecter les données dans la vue Thymeleaf.
     * @return Le nom de la vue à afficher (templates/statistical.html).
     */
    @RequestMapping("/patient-issue-statistics")
    public String showPatientIssueStatistics(Model model) {
        List<Map<String, Object>> issueStats = statisticsRepository.getPatientIssueStatistics();
        model.addAttribute("issueStats", issueStats);
        return "statistical";
    }

    /**
     * Retourne les données statistiques au format JSON pour des traitements de données.
     *
     * @return Une liste de statistiques sous forme de maps (clé-valeur).
     * Chaque map représente une ligne de données issues de la requête personnalisée.
     */
    @RequestMapping("/patient-issue-statistics-data")
    @ResponseBody
    public List<Map<String, Object>> getPatientIssueStatisticsData() {
        return statisticsRepository.getPatientIssueStatistics();
    }
}