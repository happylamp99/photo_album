package ict.methodologies.Photos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="imagestable")
public class Photos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "imageid",unique = true)
    private int id;

    @Column(name = "image_name", nullable = false)
    private String iName;

    @Column(name = "image_category", nullable = false)
    private String iCategory;

    @Column(name= "url",nullable = false)
    private String iURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiCategory() {
        return iCategory;
    }

    public void setiCategory(String iCategory) {
        this.iCategory = iCategory;
    }

    public String getiURL() {
        return iURL;
    }

    public void setiURL(String iURL) {
        this.iURL = iURL;
    }
}
