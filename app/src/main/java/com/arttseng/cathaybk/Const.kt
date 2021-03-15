package com.arttseng.cathaybk

class Const {
    companion object {
        val KEY_MEDIA_PROJECTION_RESULTCODE = "resultCode"
        val KEY_MEDIA_PROJECTION_INTENT = "dataIntent"
        val URL = "url"
        val Title = "title"

        var RecordingLength = 1000*60*1L
        var ScanPeriod = 1000*60*5L

        //var BaseURL = "https://developer.github.com/v3/"
        var BaseURL = "https://api.github.com/"
        const val GetAllUsers = "/user"


        //const val MatchAPI = "GetGameList"
        const val UpdateStatusAPI = "UpdateStatus"
        const val SMZB = "https://smzb.cn/"
    }
}