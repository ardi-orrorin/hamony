package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@Entity
@Table(name = "TBL_ALERT_TEMPLATE")
@AllArgsConstructor
@NoArgsConstructor
data class AlertTemplate(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long?,

    @Column(name = "BODY")
    var body: String = "",

    @Column(name = "CREATEAT")
    var createAT: LocalDateTime = LocalDateTime.now()
)
