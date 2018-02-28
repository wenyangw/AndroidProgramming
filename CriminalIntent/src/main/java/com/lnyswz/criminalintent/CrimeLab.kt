package com.lnyswz.criminalintent

import android.content.Context
import java.util.*

/**
 * Created by Wenyang on 2018/2/28.
 */
class CrimeLab {

    private lateinit var mCrimes:  MutableList<Crime>


    companion object {
        private lateinit var sCrimeLab: CrimeLab

        fun get(context: Context): CrimeLab{
            if(sCrimeLab == null){
                sCrimeLab = CrimeLab(context)
            }
            return sCrimeLab
        }
    }

    private constructor(context: Context){
        mCrimes = ArrayList<Crime>()
        for (i in 0..99){
            var crime: Crime = Crime()
            crime.mTitle = "Crime #" + i
            crime.mSolved = i % 2 == 0
            mCrimes.add(crime)
        }
    }

//    fun getCrimes(): List<Crime>{
//        return mCrimes
//    }

    fun getCrime(id: UUID): Crime?{
        for (mCrime in mCrimes) {
            if(mCrime.mId.equals(id)){
                return mCrime
            }
        }
        return null
    }
}