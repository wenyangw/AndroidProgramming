package com.lnyswz.criminalintent

import java.util.*

/**
 * Created by Wenyang on 2018/2/23.
 */
class Crime{
    val mId: UUID = UUID.randomUUID()
    var mTitle: String = ""
    val mDate: Date = Date()
    var mSolved: Boolean = false
}