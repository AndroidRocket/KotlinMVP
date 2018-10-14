

package com.wilson.controller

import android.os.Bundle
import android.view.View

import com.bluelinelabs.conductor.Controller
import com.wilson.views.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
abstract class BaseController : Controller {

    protected abstract val title: String

    protected constructor() {}
    protected constructor(args: Bundle) : super(args) {}

    override fun onAttach(view: View) {
        super.onAttach(view)

        // Quick way to access the toolbar for demo purposes. Production app needs to have this done properly
        val activity = (activity as HomeActivity?)!!

        // Activity should have already been set after the conductor is attached.

        activity.toolbar.title = title
//        activity.enableUpArrow(router.backstackSize > 1)
    }
}