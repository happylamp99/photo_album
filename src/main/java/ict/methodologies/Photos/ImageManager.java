package ict.methodologies.Photos;

import javax.persistence.*;
import java.util.List;

public class ImageManager {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("photo_album");

    public static void main(String[] args) {
        addImage(1, "Photos", "Category","/home/ToolOS/Downloads/874711.png");
        getImages();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addImage(int id, String name, String category,String filepath) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Photos photos = new Photos();
            photos.setId(id);
            photos.setiName(name);
            photos.setiCategory(category);
            photos.setiURL(filepath);
            em.persist(photos);
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

        TypedQuery<Photos> tq = em.createQuery(query, Photos.class);
        tq.setParameter("imageid", id);
        Photos photos = null;
        try {
            photos = tq.getSingleResult();
            System.out.println(photos.getiName() + " " + photos.getiCategory()+" "+ photos.getiURL());
        } catch (NoResultException ex) {
            System.out.println("ex");
        } finally {
            em.close();

        }
    }

    public static List<Photos> getImages() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT i FROM Photos i where i.id IS NOT NULL";
        TypedQuery<Photos> tq = em.createQuery(strQuery, Photos.class);
        List<Photos> photos;
        photos = null;
        try {
            photos = tq.getResultList();
            photos.forEach(photos -> System.out.println(photos.getiName() + " " + photos.getiCategory()+" "+ photos.getiURL()));
            return photos;
        } catch (NoResultException ex) {
            System.out.println("ex");
        } finally {
            em.close();
        }
        return photos;
    }

    public static void changeIName(int id, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Photos photos = null;
        try {
            et = em.getTransaction();
            et.begin();

            photos = em.find(Photos.class, id);
            photos.setiName(name);

            em.persist(photos);
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
        Photos photos = null;
        try {
            et = em.getTransaction();
            et.begin();
            photos = em.find(Photos.class, id);
            em.remove(photos);
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
