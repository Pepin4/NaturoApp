package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.MedicalInfo;


/**                                                                                                  

        ███╗   ███╗███████╗██████╗ ██╗ ██████╗ █████╗ ██╗     ██╗███╗   ██╗███████╗ ██████╗     ██████╗ ███████╗██████╗ 
        ████╗ ████║██╔════╝██╔══██╗██║██╔════╝██╔══██╗██║     ██║████╗  ██║██╔════╝██╔═══██╗    ██╔══██╗██╔════╝██╔══██╗
        ██╔████╔██║█████╗  ██║  ██║██║██║     ███████║██║     ██║██╔██╗ ██║█████╗  ██║   ██║    ██████╔╝█████╗  ██████╔╝
        ██║╚██╔╝██║██╔══╝  ██║  ██║██║██║     ██╔══██║██║     ██║██║╚██╗██║██╔══╝  ██║   ██║    ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║ ╚═╝ ██║███████╗██████╔╝██║╚██████╗██║  ██║███████╗██║██║ ╚████║██║     ╚██████╔╝    ██║  ██║███████╗██║     
        ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝     ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                        
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité MedicalInfo.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface MedicalInfoRepository extends JpaRepository<MedicalInfo, Integer> {
    
	/**
     * Récupère la liste des informations médicales associées à un patient via son identifiant.
     *
     * @param patientId l'identifiant du patient dont on veut récupérer les informations médicales.
     * @return une liste d'objets MedicalInfo liés au patient.
     */
    List<MedicalInfo> findByPatient_PatientId(int patientId);

}