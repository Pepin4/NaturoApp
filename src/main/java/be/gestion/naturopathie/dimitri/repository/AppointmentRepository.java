package be.gestion.naturopathie.dimitri.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Appointment;
import be.gestion.naturopathie.dimitri.model.Patient;



/**                                                                                                  

         █████╗ ██████╗ ██████╗  ██████╗ ██╗███╗   ██╗████████╗███╗   ███╗███████╗███╗   ██╗████████╗    ██████╗ ███████╗██████╗ 
        ██╔══██╗██╔══██╗██╔══██╗██╔═══██╗██║████╗  ██║╚══██╔══╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝    ██╔══██╗██╔════╝██╔══██╗
        ███████║██████╔╝██████╔╝██║   ██║██║██╔██╗ ██║   ██║   ██╔████╔██║█████╗  ██╔██╗ ██║   ██║       ██████╔╝█████╗  ██████╔╝
        ██╔══██║██╔═══╝ ██╔═══╝ ██║   ██║██║██║╚██╗██║   ██║   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║       ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║  ██║██║     ██║     ╚██████╔╝██║██║ ╚████║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║       ██║  ██║███████╗██║     
        ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                                                                
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Appointment.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	/**
     * Récupère la liste des rendez-vous associés à un utilisateur donné (via son ID).
     *
     * @param userId l'identifiant de l'utilisateur.
     * @return une liste de rendez-vous associés à cet utilisateur.
     */
    List<Appointment> findByUserUserId(int userId);

    /**
     * Récupère la liste des rendez-vous pour un patient à une date spécifique.
     * Cette méthode utilise une fonction SQL (DATE) pour comparer uniquement la partie date (sans l'heure).
     *
     * @param patient le patient pour lequel on cherche les rendez-vous.
     * @param date la date à vérifier (sans tenir compte de l'heure).
     * @return une liste de rendez-vous correspondant au patient et à la date donnée.
     */
    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND FUNCTION('DATE', a.dateTime) = :date")
    List<Appointment> findByPatientAndDate(@Param("patient") Patient patient, @Param("date") LocalDate date);
}