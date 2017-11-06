package com.tuyu.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_complaint_state", schema = "crmsys")
public class ComplaintState {
    private Integer id;
    private String name;
    private Collection<Complaint> complaints;
    private Collection<ComplaintRecord> complaintRecords;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintState that = (ComplaintState) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "complaintState")
    public Collection<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Collection<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "complaintState")
    public Collection<ComplaintRecord> getComplaintRecords() {
        return this.complaintRecords;
    }

    public void setComplaintRecords(Collection<ComplaintRecord> complaintRecords) {
        this.complaintRecords = complaintRecords;
    }
}
