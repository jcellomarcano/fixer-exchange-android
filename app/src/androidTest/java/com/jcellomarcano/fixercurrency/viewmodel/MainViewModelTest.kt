package com.jcellomarcano.fixercurrency.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jcellomarcano.fixercurrency.repository.CurrencyRepository
import com.jcellomarcano.fixercurrency.repository.FixerRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest : TestCase() {

    private lateinit var mainViewModel: MainViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        mainViewModel = MainViewModel(FixerRepository)
    }


}