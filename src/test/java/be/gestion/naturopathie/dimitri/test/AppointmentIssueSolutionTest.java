package be.gestion.naturopathie.dimitri.test;

import be.gestion.naturopathie.dimitri.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 * Test unitaire pour la classe AppointmentIssueSolution et ses associations
 */
public class AppointmentIssueSolutionTest {

    private Appointment appointment;
    private Issue issue;
    private Solution solution;
    private IssueSolution issueSolution;
    private AppointmentIssueSolution association;
    private User user;
    private Patient patient;
    private Address address;
    private Contact contact;
    private City city;
    private Country country;
    private Supplier supplier;

    @BeforeEach
    public void setUp() {
        // Création des objets nécessaires pour les tests
        country = new Country("Belgique", "BE");
        city = new City("5000", "Namur", country);
        address = new Address("42", "Rue des Lilas", city);
        contact = new Contact("0123456789", "contact@exemple.com");
        patient = new Patient(new Date(), "M", "Jean", "Dupont", "Marié", "Employé", address, contact);
        user = new User("therapeute1", "motdepasse", address, contact);
        supplier = new Supplier();
        supplier.setSupplierName("Supplier Example");
        supplier.setAddress(address);
        supplier.setContact(contact);
        appointment = new Appointment();
        appointment.setDateTime(new Date());
        appointment.setSubject("Consultation nutrition");
        appointment.setPatient(patient);
        appointment.setUser(user);
        issue = new Issue();
        issue.setName("Problème nutritionnel");
        issue.setOrigin("Déséquilibre alimentaire");
        issue.setConstraint("Suivi spécifique requis");
        issue.setDescription("Problème lié à une mauvaise alimentation.");
        solution = new Solution();
        solution.setRemedy("Conseils nutritionnels");
        solution.setBenefits("Amélioration de la santé par la nutrition");
        solution.setSupplier(supplier);
        issueSolution = new IssueSolution();
        issueSolution.setIssue(issue);
        issueSolution.setSolution(solution);
        association = new AppointmentIssueSolution(appointment, issueSolution);
    }

    @AfterEach
    public void tearDown() {
        // Nettoyage après chaque test
        association = null;
        appointment = null;
        issueSolution = null;
        solution = null;
        issue = null;
        user = null;
        patient = null;
        address = null;
        contact = null;
        city = null;
        country = null;
        supplier = null;
    }

    @Test
    @DisplayName("CREATE - Création d'une association entre Appointment et IssueSolution")
    public void testCreateAppointmentIssueSolution() {
        // Test de la création de l'association entre Appointment et IssueSolution
        assertNotNull(association); // Vérifie que l'association a bien été créée
        assertNotNull(association.getAppointment()); // Vérifie que l'objet Appointment est bien présent
        assertNotNull(association.getIssueSolution()); // Vérifie que l'objet IssueSolution est bien présent
        assertEquals("Consultation nutrition", association.getAppointment().getSubject()); // Vérifie que le sujet de l'appointment est correct
        assertEquals("Conseils nutritionnels", association.getIssueSolution().getSolution().getRemedy()); // Vérifie que le remède de la solution est correct
        assertEquals("Problème nutritionnel", association.getIssueSolution().getIssue().getName()); // Vérifie que le nom du problème est correct
        assertEquals("Déséquilibre alimentaire", association.getIssueSolution().getIssue().getOrigin()); // Vérifie que l'origine du problème est correcte
    }

    @Test
    @DisplayName("READ - Lecture des données dans l'association")
    public void testReadAppointmentIssueSolutionFields() {
        // Test de la lecture des données dans l'association
        assertEquals("Consultation nutrition", association.getAppointment().getSubject()); // Vérifie que le sujet de l'appointment est correct
        assertEquals("Problème nutritionnel", association.getIssueSolution().getIssue().getName()); // Vérifie que le nom du problème est correct
        assertEquals("Conseils nutritionnels", association.getIssueSolution().getSolution().getRemedy()); // Vérifie que le remède est correct
        assertEquals("Déséquilibre alimentaire", association.getIssueSolution().getIssue().getOrigin()); // Vérifie que l'origine du problème est correcte
        assertEquals("Amélioration de la santé par la nutrition", association.getIssueSolution().getSolution().getBenefits()); // Vérifie que les bénéfices de la solution sont corrects
        assertEquals("Supplier Example", association.getIssueSolution().getSolution().getSupplier().getSupplierName()); // Vérifie que le nom du fournisseur est correct
    }

    @Test
    @DisplayName("UPDATE - Mise à jour des données dans l'association")
    public void testUpdateAppointmentIssueSolution() {
        // Test de la mise à jour des données dans l'association
        association.getAppointment().setSubject("Suivi nutritionnel"); // Mise à jour du sujet de l'appointment
        association.getIssueSolution().getSolution().setRemedy("Suivi nutritionnel détaillé"); // Mise à jour du remède de la solution

        assertEquals("Suivi nutritionnel", association.getAppointment().getSubject()); // Vérifie que le sujet de l'appointment a bien été mis à jour
        assertEquals("Suivi nutritionnel détaillé", association.getIssueSolution().getSolution().getRemedy()); // Vérifie que le remède de la solution a bien été mis à jour
    }

    @Test
    @DisplayName("DELETE - Suppression des données de l'association")
    public void testDeleteAppointmentIssueSolutionFields() {
        // Test de la suppression des données dans l'association
        association.getAppointment().setSubject(null); // Suppression du sujet de l'appointment
        association.getIssueSolution().getSolution().setRemedy(null); // Suppression du remède de la solution

        assertNull(association.getAppointment().getSubject()); // Vérifie que le sujet de l'appointment est bien null
        assertNull(association.getIssueSolution().getSolution().getRemedy()); // Vérifie que le remède de la solution est bien null
    }
}
