/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package acoes_sita;


import acoes.Apadrinar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        /* Entorno de persistencia */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBP"); //En este momento se crea el esquema en la base de datos.
	EntityManager em = emf.createEntityManager();
	EntityTransaction ts = em.getTransaction();
        
        /*
        
        // Creamos un usuario normal y corriente.
        Usuario us = new Usuario("Patata", "García", "Pérez");
        
        // Creamos un usuario con rol de Agente.
        Agente ag = new Agente();
        
        // Creamos un niño.
        Niño n = new Niño("Tubérculo", "Remolacha", "Sánchez");
        
        
        // Hacemos que el usuario anterior apadrine al niño.
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date start = new Date();
        Apadrinar ap = new Apadrinar(15, (Date) start , n, us);
        
        // Correo.
        Correo c = new Correo();
        
        */
        
        ts.begin();
        
        // Comprobamos el funcionamiento al insertar datos.
        /*
        em.persist(us);
        em.persist(ag);
        em.persist(n);
        em.persist(c);
        em.persist(ap);
        */
        
        /*
        Date end = new Date("2019/05/03");
        Apadrinar ap1 = em.find(Apadrinar.class, 105L);        
        ap1.setFecha_fin(end);        
        em.merge(ap1);
        */
        
        ts.commit();
                     
        em.close();
        emf.close();
    }
    
}
