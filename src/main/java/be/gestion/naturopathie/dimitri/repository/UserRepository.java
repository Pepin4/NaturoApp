package be.gestion.naturopathie.dimitri.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.User;


/**                                                                                                  

        ██╗   ██╗███████╗███████╗██████╗     ██████╗ ███████╗██████╗ 
        ██║   ██║██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔════╝██╔══██╗
        ██║   ██║███████╗█████╗  ██████╔╝    ██████╔╝█████╗  ██████╔╝
        ██║   ██║╚════██║██╔══╝  ██╔══██╗    ██╔══██╗██╔══╝  ██╔═══╝ 
        ╚██████╔╝███████║███████╗██║  ██║    ██║  ██║███████╗██║     
         ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                               
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité User.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param userName le nom d'utilisateur à rechercher.
     * @return un Optional contenant l'utilisateur si trouvé, ou vide si non trouvé.
     */
    Optional<User> findByUserName(String userName);

    /**
     * Vérifie si un utilisateur existe déjà avec le nom d'utilisateur fourni.
     *
     * @param username le nom d'utilisateur à vérifier.
     * @return true si un utilisateur existe avec ce nom d'utilisateur, false sinon.
     */
    boolean existsByUserName(String username);
}