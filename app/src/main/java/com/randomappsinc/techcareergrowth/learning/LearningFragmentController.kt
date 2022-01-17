package com.randomappsinc.techcareergrowth.learning

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

internal class LearningFragmentController(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    private var watchContentFragment: WatchContentFragment? = null

    fun onStateChange(newState: LearningState) {
        when (newState) {
            LearningState.WATCH_CONTENT -> {
                if (watchContentFragment == null) {
                    watchContentFragment = WatchContentFragment.getInstance()
                    addFragment(watchContentFragment)
                } else {
                    showFragment(watchContentFragment!!)
                }
                // Hide other fragments
            }
            LearningState.QUIZ -> {

            }
            LearningState.SCORE_REPORT -> {
            }
        }
    }

    private fun addFragment(fragment: Fragment?) {
        fragmentManager.beginTransaction().add(containerId, fragment!!).commit()
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().show(fragment).commit()
    }

    private fun hideFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(fragment).commit()
    }

    fun restoreFragments() {
        for (fragment in fragmentManager.fragments) {
            val fragmentName = fragment.javaClass.simpleName
            if (WatchContentFragment::class.java.simpleName.equals(fragmentName)) {
                watchContentFragment = fragment as WatchContentFragment
            }
        }
    }
}
