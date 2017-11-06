package com.tuyu.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_city", schema = "crmsys")
public class City {
    private Integer id;
    private String code;
    private String name;
    private String firstLetter;
    private Integer level;
    private City parent;
    private Collection<City> citys;

    private Collection<Client> clients;

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
    @Column(name = "code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "first_letter")
    public String getFirstLetter() {
        return this.firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        City city = (City) o;

        if (this.id != null ? !this.id.equals(city.id) : city.id != null) {
            return false;
        }
        if (this.code != null ? !this.code.equals(city.code) : city.code != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(city.name) : city.name != null) {
            return false;
        }
        if (this.firstLetter != null ? !this.firstLetter.equals(city.firstLetter) : city.firstLetter != null) {
            return false;
        }
        if (this.level != null ? !this.level.equals(city.level) : city.level != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.code != null ? this.code.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.firstLetter != null ? this.firstLetter.hashCode() : 0);
        result = 31 * result + (this.level != null ? this.level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public City getParent() {
        return this.parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Collection<City> getCitys() {
        return this.citys;
    }

    public void setCitys(Collection<City> citys) {
        this.citys = citys;
    }

    @OneToMany(mappedBy = "city")
    public Collection<Client> getClients() {
        return this.clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }
}
