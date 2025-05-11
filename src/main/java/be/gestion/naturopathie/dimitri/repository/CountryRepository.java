package be.gestion.naturopathie.dimitri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Country;


/**                                                                                                  

         ██████╗ ██████╗ ██╗   ██╗███╗   ██╗████████╗██████╗ ██╗   ██╗    ██████╗ ███████╗██████╗ 
        ██╔════╝██╔═══██╗██║   ██║████╗  ██║╚══██╔══╝██╔══██╗╚██╗ ██╔╝    ██╔══██╗██╔════╝██╔══██╗
        ██║     ██║   ██║██║   ██║██╔██╗ ██║   ██║   ██████╔╝ ╚████╔╝     ██████╔╝█████╗  ██████╔╝
        ██║     ██║   ██║██║   ██║██║╚██╗██║   ██║   ██╔══██╗  ╚██╔╝      ██╔══██╗██╔══╝  ██╔═══╝ 
        ╚██████╗╚██████╔╝╚██████╔╝██║ ╚████║   ██║   ██║  ██║   ██║       ██║  ██║███████╗██║     
         ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝       ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                             
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Country.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	/**
     * Recherche un pays en fonction de son nom.
     *
     * @param countryName le nom du pays à rechercher.
     * @return le pays correspondant s'il est trouvé, sinon null.
     */
    Country findByCountryName(String countryName);
    
}
