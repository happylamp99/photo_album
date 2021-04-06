package ict.methodologies.Photos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="imagestable")
public class Image implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "imageID",unique = true)
    private int id;

    @Column(name = "ImageName", nullable = false)
    private String iName;

    @Column(name = "ImageCategory", nullable = false)
    private String iCategory;

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
}
