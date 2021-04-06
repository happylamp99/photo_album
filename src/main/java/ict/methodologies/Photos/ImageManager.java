package ict.methodologies.Photos;

import javax.persistence.*;
import java.util.List;

public class ImageManager {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("photo_album");

    public static void main(String[] args) {
        addImage(2, "Image", "Category");

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addImage(int id, String name, String category) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Image image = new Image();
            image.setId(id);
            image.setiName(name);
            image.setiCategory(category);

            em.persist(image);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getImage(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT i FROM Images i WHERE i.id = :imageID";

        TypedQuery<Image> tq = em.createQuery(query, Image.class);
        tq.setParameter("imageID", id);
        Image image = null;
        try {
            image = tq.getSingleResult();
            System.out.println(image.getiName() + " " + image.getiCategory());
        } catch (NoResultException ex) {
            System.out.println("ex");
        } finally {
            em.close();

        }
    }

    public static void getImages(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT i FROM Images i where i.id IS NOT NULL";
        TypedQuery<Image> tq = em.createQuery(strQuery, Image.class);
        List<Image> images;
        try {
            images = tq.getResultList();
            images.forEach(image -> System.out.println(image.getiName() + " " + image.getiCategory()));
        } catch (NoResultException ex) {
            System.out.println("ex");
        } finally {
            em.close();
        }
    }

    public static void changeIName(int id, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Image image = null;
        try {
            et = em.getTransaction();
            et.begin();

            image = em.find(Image.class, id);
            image.setiName(name);

            em.persist(image);
            et.commit();

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteImage(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Image image = null;
        try {
            et = em.getTransaction();
            et.begin();
            image = em.find(Image.class, id);
            em.remove(image);

            em.persist(image);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
}
