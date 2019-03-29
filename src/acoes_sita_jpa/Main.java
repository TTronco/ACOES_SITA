/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package acoes_sita_jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("acoesDB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction ts = em.getTransaction();
        ts.begin();
            // Generar esquema.
            // Crear datos ficticios en nuestra BD.
            System.out.println("Hola Mundo");
        
            System.out.println("FUNCIONAAA");

            System.out.println("Probando desde github");
        ts.commit();
        
    }
    
}
