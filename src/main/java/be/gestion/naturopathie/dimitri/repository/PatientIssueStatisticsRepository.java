package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**                                                                                                  

        ███████╗████████╗ █████╗ ████████╗██╗███████╗████████╗██╗ ██████╗ █████╗ ██╗         ██████╗ ███████╗██████╗ 
        ██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██║██╔════╝╚══██╔══╝██║██╔════╝██╔══██╗██║         ██╔══██╗██╔════╝██╔══██╗
        ███████╗   ██║   ███████║   ██║   ██║███████╗   ██║   ██║██║     ███████║██║         ██████╔╝█████╗  ██████╔╝
        ╚════██║   ██║   ██╔══██║   ██║   ██║╚════██║   ██║   ██║██║     ██╔══██║██║         ██╔══██╗██╔══╝  ██╔═══╝ 
        ███████║   ██║   ██║  ██║   ██║   ██║███████║   ██║   ██║╚██████╗██║  ██║███████╗    ██║  ██║███████╗██║     
        ╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝   ╚═╝╚══════╝   ╚═╝   ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                                                     
**/


/**
 * Repository pour gérer les statistiques.
 * permettant de récupérer des statistiques sur les problèmes rencontrés par les patients.
 */
@Repository
public class PatientIssueStatisticsRepository {

	/**
	 * JdbcTemplate est un outil fourni par Spring pour faciliter l'interaction avec la base de données en exécutant des requêtes SQL.
	 * Dans ce cas, il est utilisé pour récupérer des données brutes à partir de la base de données (par exemple, les statistiques des problèmes des patients).
	 * 
	 * JdbcTemplate exécute la requête SQL et retourne les résultats sous forme de listes de maps (chaque map représentant une ligne du résultat avec des paires clé-valeur).
	 * Ces données brutes sont ensuite traitées et organisées par le contrôleur ou le service avant d'être envoyées à la vue.
	 * 
	 * L'objectif de cette injection est de permettre l'accès à la base de données pour récupérer les informations nécessaires à l'affichage des statistiques des problèmes des patients.
	 */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Récupère les 20 problèmes les plus fréquents rencontrés par les patients.
     * Chaque problème est accompagné du nombre distinct de patients concernés.
     * 
     * La requête SQL effectue une jointure sur les tables :
     * - appointment : les rendez-vous
     * - appointment_issue_solution : table de liaison entre les rendez-vous et les solutions liées aux problèmes
     * - issue_solution : table associant un problème à une solution
     * - issue : les problèmes rencontrés
     * 
     * Le résultat est groupé par nom de problème et trié par nombre de patients (ordre décroissant).
     * 
     * @return une liste de Map<String, Object> contenant :
     *         - "issue_name" : le nom du problème
     *         - "patient_count" : le nombre de patients concernés
     */
    public List<Map<String, Object>> getPatientIssueStatistics() {
        String sql = "SELECT i.i_name AS issue_name, COUNT(DISTINCT a.fk_patient) AS patient_count " +
                     "FROM appointment a " +
                     "JOIN appointment_issue_solution ais ON a.a_id = ais.fk_appointment " +
                     "JOIN issue_solution is1 ON ais.fk_issue_solution = is1.is_id " +
                     "JOIN issue i ON is1.fk_issue = i.i_id " +
                     "GROUP BY i.i_name " +
                     "ORDER BY patient_count DESC ";

        return jdbcTemplate.queryForList(sql);
    }
}