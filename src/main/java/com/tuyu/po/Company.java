package com.tuyu.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_company", schema = "crmsys")
public class Company {
    private Integer id;
    private String name;
    private String description;
    private Collection<Dept> depts;
    private Collection<Role> roles;
    private Collection<User> users;

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
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        if (this.id != null ? !this.id.equals(company.id) : company.id != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(company.name) : company.name != null) {
            return false;
        }
        if (this.description != null ? !this.description.equals(company.description) : company.description != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "company")
    public Collection<Dept> getDepts() {
        return this.depts;
    }

    public void setDepts(Collection<Dept> depts) {
        this.depts = depts;
    }

    @OneToMany(mappedBy = "company")
    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "company")
    public Collection<User> getUsers() {
        return this.users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
