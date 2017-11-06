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
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "legal")
    public String getLegal() {
        return this.legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    @Basic
    @Column(name = "postcode")
    public Integer getPostcode() {
        return this.postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "register_date")
    public Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "next_time")
    public Date getNextTime() {
        return this.nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return this.telephone;
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

        if (this.id != null ? !this.id.equals(client.id) : client.id != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(client.name) : client.name != null) {
            return false;
        }
        if (this.legal != null ? !this.legal.equals(client.legal) : client.legal != null) {
            return false;
        }
        if (this.postcode != null ? !this.postcode.equals(client.postcode) : client.postcode != null) {
            return false;
        }
        if (this.phone != null ? !this.phone.equals(client.phone) : client.phone != null) {
            return false;
        }
        if (this.fax != null ? !this.fax.equals(client.fax) : client.fax != null) {
            return false;
        }
        if (this.email != null ? !this.email.equals(client.email) : client.email != null) {
            return false;
        }
        if (this.url != null ? !this.url.equals(client.url) : client.url != null) {
            return false;
        }
        if (this.registerDate != null ? !this.registerDate.equals(client.registerDate) : client.registerDate != null) {
            return false;
        }
        if (this.nextTime != null ? !this.nextTime.equals(client.nextTime) : client.nextTime != null) {
            return false;
        }
        if (this.level != null ? !this.level.equals(client.level) : client.level != null) {
            return false;
        }
        if (this.telephone != null ? !this.telephone.equals(client.telephone) : client.telephone != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.legal != null ? this.legal.hashCode() : 0);
        result = 31 * result + (this.postcode != null ? this.postcode.hashCode() : 0);
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.fax != null ? this.fax.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.url != null ? this.url.hashCode() : 0);
        result = 31 * result + (this.registerDate != null ? this.registerDate.hashCode() : 0);
        result = 31 * result + (this.nextTime != null ? this.nextTime.hashCode() : 0);
        result = 31 * result + (this.level != null ? this.level.hashCode() : 0);
        result = 31 * result + (this.telephone != null ? this.telephone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "id")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Collection<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Linkman> getLinkmen() {
        return this.linkmen;
    }

    public void setLinkmen(Collection<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Linkrecord> getLinkrecords() {
        return this.linkrecords;
    }

    public void setLinkrecords(Collection<Linkrecord> linkrecords) {
        this.linkrecords = linkrecords;
    }
}
