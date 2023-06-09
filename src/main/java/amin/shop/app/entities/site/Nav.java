package amin.shop.app.entities.site;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Table(name = "nav")
public class Nav {
    @Id
    @GeneratedValue
    //شناسه pk به صورت unique برای دیتا بیس که همه موجودیت هایمان باید داشته باشند.
    private long id;

    //    @Column(name="name")
    private String title;
    private String link;
    //is this field enable or unable.
    private boolean enable;
    private int itemOrder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }
}
