package com.tuyu.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_user", schema = "crmsys")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private Integer enabled;
    private Integer locked;
    private String description;
    private String name;
    private Collection<Client> clients;
    private Collection<Complaint> complaints;
    private Collection<ComplaintRecord> complaintRecord;
    private Collection<Document> documents;
    private Collection<Linkrecord> linkRecords;
    private Company company;
    private Dept dept;
    private Collection<Role> roles;

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
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "sex")
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "enabled")
    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "locked")
    public Integer getLocked() {
        return this.locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (this.id != null ? !this.id.equals(user.id) : user.id != null) {
            return false;
        }
        if (this.username != null ? !this.username.equals(user.username) : user.username != null) {
            return false;
        }
        if (this.password != null ? !this.password.equals(user.password) : user.password != null) {
            return false;
        }
        if (this.email != null ? !this.email.equals(user.email) : user.email != null) {
            return false;
        }
        if (this.sex != null ? !this.sex.equals(user.sex) : user.sex != null) {
            return false;
        }
        if (this.enabled != null ? !this.enabled.equals(user.enabled) : user.enabled != null) {
            return false;
        }
        if (this.locked != null ? !this.locked.equals(user.locked) : user.locked != null) {
            return false;
        }
        if (this.description != null ? !this.description.equals(user.description) : user.description != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(user.name) : user.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.username != null ? this.username.hashCode() : 0);
        result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.sex != null ? this.sex.hashCode() : 0);
        result = 31 * result + (this.enabled != null ? this.enabled.hashCode() : 0);
        result = 31 * result + (this.locked != null ? this.locked.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Client> getClients() {
        return this.clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Collection<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "user")
    public Collection<ComplaintRecord> getComplaintRecord() {
        return this.complaintRecord;
    }

    public void setComplaintRecord(Collection<ComplaintRecord> complaintRecord) {
        this.complaintRecord = complaintRecord;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Linkrecord> getLinkRecords() {
        return this.linkRecords;
    }

    public void setLinkRecords(Collection<Linkrecord> linkRecords) {
        this.linkRecords = linkRecords;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name = "deptno", referencedColumnName = "id")
    public Dept getDept() {
        return this.dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @ManyToMany
    @JoinTable(name = "crm_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
