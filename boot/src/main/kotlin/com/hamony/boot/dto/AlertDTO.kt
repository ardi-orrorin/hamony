package com.hamony.boot.dto

data class AlertDTO(

    var id: Long? = null,

    var member: MemberDTO? = null,

    var alertTemplate: AlertTemplateDTO? = null,
)
