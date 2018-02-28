package com.lnyswz.criminalintent

import android.os.Bundle

class CrimeActivity : SingleFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(CrimeFragment(), R.id.fragment_container)
    }
}
