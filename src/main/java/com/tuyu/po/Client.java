package com.tuyu.po;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_client", schema = "crmsys")
public class Client {
    private Integer id;
    private String name;
    private String legal;
    private Integer postcode;
    private String phone;
    private String fax;
    private String email;
    private String url;
    private Date registerDate;
    private Date nextTime;
    private Integer level;
    private String telephone;
    private User user;
    private City city;
    private Collection<Complaint> complaints;
    private Collection<Document> documents;
    private Collection<Linkman> linkmen;
    private Collection<Linkrecord> linkrecords;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "legal")
    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    @Basic
    @Column(name = "postcode")
    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "register_date")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "next_time")
    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) {
            return false;
        }
        if (name != null ? !name.equals(client.name) : client.name != null) {
            return false;
        }
        if (legal != null ? !legal.equals(client.legal) : client.legal != null) {
            return false;
        }
        if (postcode != null ? !postcode.equals(client.postcode) : client.postcode != null) {
            return false;
        }
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) {
            return false;
        }
        if (fax != null ? !fax.equals(client.fax) : client.fax != null) {
            return false;
        }
        if (email != null ? !email.equals(client.email) : client.email != null) {
            return false;
        }
        if (url != null ? !url.equals(client.url) : client.url != null) {
            return false;
        }
        if (registerDate != null ? !registerDate.equals(client.registerDate) : client.registerDate != null) {
            return false;
        }
        if (nextTime != null ? !nextTime.equals(client.nextTime) : client.nextTime != null) {
            return false;
        }
        if (level != null ? !level.equals(client.level) : client.level != null) {
            return false;
        }
        if (telephone != null ? !telephone.equals(client.telephone) : client.telephone != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (legal != null ? legal.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (nextTime != null ? nextTime.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Collection<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Linkman> getLinkmen() {
        return linkmen;
    }

    public void setLinkmen(Collection<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Linkrecord> getLinkrecords() {
        return linkrecords;
    }

    public void setLinkrecords(Collection<Linkrecord> linkrecords) {
        this.linkrecords = linkrecords;
    }
}
