package team6.DAO;

import team6.entities.Vehicle;
import team6.expetions.ElementsNotFound;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class VehicleDAO {

    private final EntityManager em;


    public VehicleDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Vehicle vehicle) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(vehicle);
        transaction.commit();

        System.out.println("Complimenti il veicolo " + vehicle.getType() + " è stato creato con successo!");
    }

    public Vehicle findById(long id) {

        return em.find(Vehicle.class, id);

    }

    public void findAndDeleteById(long id) {

        Vehicle vehicle = this.findById(id);

        if (vehicle != null) {

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(vehicle);
            transaction.commit();

            System.out.println("Complimenti hai eliminato con successo il veicolo " + vehicle.getType());

        } else {
            System.out.println("Ops! non ho trovato nessun veicolo tramite il codice ID che mi hai fornito ");
        }
    }


    public List<Vehicle> getAllVehicles() throws ElementsNotFound {
        TypedQuery<Vehicle> getAllVehicles=em.createNamedQuery("getAllVehicles", Vehicle.class);
        List<Vehicle> elements= getAllVehicles.getResultList();
        if(elements.isEmpty()) throw new ElementsNotFound();
        return elements;
    }

}
