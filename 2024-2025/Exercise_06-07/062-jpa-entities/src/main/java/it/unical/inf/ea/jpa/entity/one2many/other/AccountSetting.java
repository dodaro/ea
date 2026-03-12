package it.unical.inf.ea.jpa.entity.one2many.other;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_settings")
public class AccountSetting {
 
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(name = "name", nullable = false)
    private String settingName;
 
    @Column(name = "value", nullable = false)
    private String settingValue;

    /* default fetch
        OneToOne: EAGER
        ManyToOne: EAGER
        OneToMany: LAZY
        ManyToMany: LAZY
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="account_id", nullable = false)
    private Account myaccount;

    @Column(name="IMAGE")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] image;

    // getters and setters
}