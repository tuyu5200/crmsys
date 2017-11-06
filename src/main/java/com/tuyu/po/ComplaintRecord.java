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
@Table(name = "crm_complaint_record", schema = "crmsys")
public class ComplaintRecord {
    private Integer id;
    private String complaintTitle;
    private String content;
    private Date recordDate;
    private Collection<ComplaintDispose> complaintDisposes;
    private ComplaintState complaintState;
    private Complaint complaint;
    private User user;

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
    @Column(name = "complaint_title")
    public String getComplaintTitle() {
        return this.complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "record_date")
    public Date getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintRecord that = (ComplaintRecord) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) {
            return false;
        }
        if (this.complaintTitle != null ? !this.complaintTitle.equals(that.complaintTitle) : that.complaintTitle != null) {
            return false;
        }
        if (this.content != null ? !this.content.equals(that.content) : that.content != null) {
            return false;
        }
        if (this.recordDate != null ? !this.recordDate.equals(that.recordDate) : that.recordDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.complaintTitle != null ? this.complaintTitle.hashCode() : 0);
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        result = 31 * result + (this.recordDate != null ? this.recordDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "complaintRecord")
    public Collection<ComplaintDispose> getComplaintDisposes() {
        return this.complaintDisposes;
    }

    public void setComplaintDisposes(Collection<ComplaintDispose> complaintDisposes) {
        this.complaintDisposes = complaintDisposes;
    }

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "id")
    public ComplaintState getComplaintState() {
        return this.complaintState;
    }

    public void setComplaintState(ComplaintState complaintState) {
        this.complaintState = complaintState;
    }

    @ManyToOne
    @JoinColumn(name = "comp_Id", referencedColumnName = "id")
    public Complaint getComplaint() {
        return this.complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
