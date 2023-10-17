package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "TBL_ALERT")
@AllArgsConstructor
@NoArgsConstructor
data class Alert(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_MEMBER_ID")
    var member: Member?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_REF_ALERT_TEMPLATE_ID_ID")
    var alertTemplate: AlertTemplate?,
)
