package be.gestion.naturopathie.dimitri.test;

import be.gestion.naturopathie.dimitri.model.*;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaire pour la classe MedicalInfo et ses associations
 */
public class MedicalInfoTest {

    // Déclaration des objets nécessaires pour les tests
    private MedicalInfo medicalInfo;
    private Patient patient;
    private Address address;
    private Contact contact;
    private City city;
    private Country country;

    // Méthode exécutée avant chaque test pour initialiser les objets nécessaires
    @BeforeEach
    public void setUp() {
        // Création d'un objet Country (pays)
        country = new Country("Belgique", "BE");

        // Création d'un objet City (ville) associée au pays
        city = new City("5000", "Namur", country);

        // Création d'une adresse associée à une ville
        address = new Address("42", "Rue des Lilas", city);

        // Création d'un contact pour le patient
        contact = new Contact("0123456789", "contact@exemple.com");

        // Création d'un patient avec des informations de base
        patient = new Patient(
                new Date(), // date de naissance
                "M",        // genre
                "Jean",
                "Dupont",
                "Marié",
                "Employé",
                address,
                contact
        );

        // Création d'un objet MedicalInfo (information médicale associée à un patient)
        medicalInfo = new MedicalInfo(
                "Diagnostic",
                "Problème de santé lié au diabète.",
                "Médication pour diabète",
                new Date(),
                patient
        );
    }

    // Méthode exécutée après chaque test pour libérer les ressources utilisées
    @AfterEach
    public void tearDown() {
        // Mise à null des objets pour libérer les ressources après chaque test
        medicalInfo = null;
        patient = null;
        address = null;
        contact = null;
        city = null;
        country = null;
    }

    // Test de la création d'une information médicale pour un patient
    @Test
    @DisplayName("CREATE - Création d'une information médicale pour un patient")
    public void testCreateMedicalInfo() {
        // Vérification que l'objet MedicalInfo n'est pas nul
        assertNotNull(medicalInfo);

        // Vérification que le patient associé à l'information médicale n'est pas nul
        assertNotNull(medicalInfo.getPatient());

        // Vérification des valeurs spécifiques des champs de l'objet MedicalInfo
        assertEquals("Diagnostic", medicalInfo.getType());
        assertEquals("Problème de santé lié au diabète.", medicalInfo.getDescription());
        assertEquals("Médication pour diabète", medicalInfo.getTreatment());

        // Vérification des informations du patient associé à l'information médicale
        assertEquals("Jean", medicalInfo.getPatient().getFirstName());
        assertEquals("Dupont", medicalInfo.getPatient().getLastName());
    }

    // Test de la lecture des données dans l'information médicale
    @Test
    @DisplayName("READ - Lecture des données dans l'information médicale")
    public void testReadMedicalInfoFields() {
        // Vérification des valeurs des champs de l'information médicale
        assertEquals("Diagnostic", medicalInfo.getType());
        assertEquals("Problème de santé lié au diabète.", medicalInfo.getDescription());
        assertEquals("Médication pour diabète", medicalInfo.getTreatment());

        // Vérification que la date d'ajout de l'information médicale n'est pas nulle
        assertNotNull(medicalInfo.getDateAdded());

        // Vérification des informations du patient associé
        assertEquals("Jean", medicalInfo.getPatient().getFirstName());
        assertEquals("Dupont", medicalInfo.getPatient().getLastName());
    }

    // Test de la mise à jour des données dans l'information médicale
    @Test
    @DisplayName("UPDATE - Mise à jour des données dans l'information médicale")
    public void testUpdateMedicalInfo() {
        // Mise à jour des champs de l'information médicale
        medicalInfo.setType("Suivi médical");
        medicalInfo.setDescription("Suivi du diabète et ajustement du traitement.");
        medicalInfo.setTreatment("Nouvelle médication adaptée");

        // Vérification que les données mises à jour sont correctement enregistrées
        assertEquals("Suivi médical", medicalInfo.getType());
        assertEquals("Suivi du diabète et ajustement du traitement.", medicalInfo.getDescription());
        assertEquals("Nouvelle médication adaptée", medicalInfo.getTreatment());
    }

    // Test de la suppression des données dans l'information médicale
    @Test
    @DisplayName("DELETE - Suppression des données dans l'information médicale")
    public void testDeleteMedicalInfoFields() {
        // Suppression des valeurs des champs de l'information médicale
        medicalInfo.setType(null);
        medicalInfo.setDescription(null);
        medicalInfo.setTreatment(null);

        // Vérification que les champs ont été supprimés (mis à null)
        assertNull(medicalInfo.getType());
        assertNull(medicalInfo.getDescription());
        assertNull(medicalInfo.getTreatment());
    }
}
