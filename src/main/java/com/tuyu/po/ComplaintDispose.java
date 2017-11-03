package com.tuyu.po;

import javax.persistence.*;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Entity
@Table(name = "crm_complaint_dispose", schema = "crmsys")
public class ComplaintDispose {
    private Integer id;
    private String dispose;
    private ComplaintRecord complaintRecord;

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
    @Column(name = "dispose")
    public String getDispose() {
        return dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintDispose that = (ComplaintDispose) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (dispose != null ? !dispose.equals(that.dispose) : that.dispose != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dispose != null ? dispose.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id")
    public ComplaintRecord getComplaintRecord() {
        return complaintRecord;
    }

    public void setComplaintRecord(ComplaintRecord complaintRecord) {
        this.complaintRecord = complaintRecord;
    }
}
