package dev.christopherbell.azurras.models.blog;

import java.util.Date;



//@Entity
//@Table(name = "blog_table_tags")
public class BlogTag {
  //  @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date creationDate;
    private String name;

    public BlogTag() {
        this.creationDate = new Date();
        this.name = "";
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
