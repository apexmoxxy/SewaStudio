package com.example.sewastudio.model

class Studio {
    var id : Int = 0
    var deskripsi : String = ""
    var nomorRuang : String = ""

    constructor(desc: String, nomor: String) {
        deskripsi = desc
        nomorRuang = nomor
    }
}