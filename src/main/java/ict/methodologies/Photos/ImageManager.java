package ict.methodologies.Photos;

import ict.methodologies.Photos.Models.Photos;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ImageManager {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("photo_album");

    public static void main(String[] args) {

        ENTITY_MANAGER_FACTORY.close();
    }

    private static String imageURL;
    public static String getImageURL() {
        return imageURL;
    }

    private static String imageName;
    public static String getImageName() {
        return imageName;
    }

    private static String imageCategory;
    public static String getImageCategory() {
        return imageCategory;
    }

    private static Double imageLong;
    public static Double getImageLong(){
        return imageLong;
    }

    private static Double imageLat;
    public static Double getImageLat(){
        return imageLat;
    }

    public static void addImage(int id, String name, String category, String url, Double longt, Double lat, Date date) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Photos photos = new Photos();
            photos.setId(id);
            photos.setiName(name);
            photos.setiCategory(category);
            photos.setiURL(url);
            photos.setiLong(longt);
            photos.setiLat(lat);
            photos.setDate(date);
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
        String query = "SELECT i FROM Photos i WHERE i.id = :imageid";

        TypedQuery<Photos> tq = em.createQuery(query, Photos.class);
        tq.setParameter("imageid", id);
        Photos photos;
        try {
            photos = tq.getSingleResult();
            System.out.println(photos.getiName() + " " + photos.getiCategory());
            imageURL = photos.getiURL();
            imageName = photos.getiName();
            imageCategory = photos.getiCategory();
            imageLong=photos.getiLong();
            imageLat= photos.getiLat();
        }
        catch (NoResultException ex) {
            System.out.println("ex");
        } finally {
            em.close();

        }
    }

    public static List<Photos> getImages() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT i FROM Photos i";
        TypedQuery<Photos> tq = em.createQuery(strQuery, Photos.class);
        List<Photos> photos;
        photos = tq.getResultList();
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
