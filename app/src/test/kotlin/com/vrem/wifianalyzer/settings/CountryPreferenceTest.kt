/*
 * WiFiAnalyzer
 * Copyright (C) 2020  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.vrem.wifianalyzer.settings

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vrem.wifianalyzer.RobolectricUtil
import com.vrem.wifianalyzer.wifi.band.WiFiChannelCountry.Companion.findAll
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import java.util.*

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@LooperMode(LooperMode.Mode.PAUSED)
class CountryPreferenceTest {
    private val mainActivity = RobolectricUtil.INSTANCE.activity
    private val countries = findAll()
    private val fixture = CountryPreference(mainActivity, Robolectric.buildAttributeSet().build())
    private val currentLocale = Locale.getDefault()

    @Test
    fun testEntries() {
        // execute
        val actual: Array<CharSequence> = fixture.entries
        // validate
        Assert.assertEquals(countries.size, actual.size)
        countries.forEach {
            val countryName: String = it.countryName(currentLocale)
            Assert.assertTrue(countryName, actual.contains(countryName))
        }
    }

    @Test
    fun testEntryValues() {
        // execute
        val actual: Array<CharSequence> = fixture.entryValues
        // validate
        Assert.assertEquals(countries.size, actual.size)
        countries.forEach {
            val countryCode: String = it.countryCode()
            Assert.assertTrue(countryCode, actual.contains(countryCode))
        }
    }
}