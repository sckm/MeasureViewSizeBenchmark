package com.github.sckm.benchmark.view_measure

import android.content.Context
import android.view.LayoutInflater
import android.view.View.MeasureSpec
import android.view.ViewGroup
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewMeasureTest {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun noNestedWeights() {
        benchmarkRule.measureRepeated {
            val container: ViewGroup = runWithTimingDisabled {
                val context = ApplicationProvider.getApplicationContext<Context>()
                LayoutInflater.from(context).inflate(R.layout.layout_no_nested, null) as ViewGroup
            }
            measureAndLayoutWrapLength(container)
        }
    }


    @Test
    fun nestedWeights() {
        benchmarkRule.measureRepeated {
            val container: ViewGroup = runWithTimingDisabled {
                val context = ApplicationProvider.getApplicationContext<Context>()
                LayoutInflater.from(context).inflate(R.layout.layout_nested, null) as ViewGroup
            }
            measureAndLayoutWrapLength(container)
        }
    }

    private fun measureAndLayoutWrapLength(container: ViewGroup) {
        val widthMeasureSpec = MeasureSpec.makeMeasureSpec(360, MeasureSpec.EXACTLY)
        val heightMeasureSpec = MeasureSpec.makeMeasureSpec(640, MeasureSpec.AT_MOST)
        container.measure(widthMeasureSpec, heightMeasureSpec)
        container.layout(
            0, 0, container.measuredWidth,
            container.measuredHeight
        )
    }
}