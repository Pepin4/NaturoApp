package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.AppointmentIssueSolution;


/**                                                                                                  

         █████╗ ██████╗ ██████╗  ██████╗ ██╗███╗   ██╗████████╗███╗   ███╗███████╗███╗   ██╗████████╗██╗███████╗███████╗██╗   ██╗███████╗    
        ██╔══██╗██╔══██╗██╔══██╗██╔═══██╗██║████╗  ██║╚══██╔══╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██║██╔════╝██╔════╝██║   ██║██╔════╝    
        ███████║██████╔╝██████╔╝██║   ██║██║██╔██╗ ██║   ██║   ██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ██║███████╗███████╗██║   ██║█████╗█████╗
        ██╔══██║██╔═══╝ ██╔═══╝ ██║   ██║██║██║╚██╗██║   ██║   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ██║╚════██║╚════██║██║   ██║██╔══╝╚════╝
        ██║  ██║██║     ██║     ╚██████╔╝██║██║ ╚████║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ██║███████║███████║╚██████╔╝███████╗    
        ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝    
                                                                                                                                             
        ███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ ███████╗██████╗                                         
        ██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔══██╗██╔════╝██╔══██╗                                        
        ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██████╔╝█████╗  ██████╔╝                                        
        ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██╔══██╗██╔══╝  ██╔═══╝                                         
        ███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ██║  ██║███████╗██║                                             
        ╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚═╝  ╚═╝╚══════╝╚═╝                                             
                                                                                                                                             
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité AppointmentIssueSolution.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface AppointmentIssueSolutionRepository extends JpaRepository<AppointmentIssueSolution, Integer> {
    
	/**
     * Recherche toutes les associations de problèmes/solutions pour un rendez-vous donné.
     *
     * @param appointmentId l'identifiant du rendez-vous.
     * @return une liste d'objets AppointmentIssueSolution liés à ce rendez-vous.
     */
    List<AppointmentIssueSolution> findByAppointment_AppointmentId(int appointmentId);

	
}
