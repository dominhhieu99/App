package com.dung.lapit.main

interface OnMainPresentListener {

    fun onLoadDataSuccess(name: String, namSinh: String, gioiTinh: String, viTri: String)
    fun onLoadDataFailed()

}