package com.wilson.controller


import android.content.Context
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.wilson.MyApp
import com.wilson.R
import com.wilson.models.MetOfficeData
import com.wilson.presenters.MetOfficeDataContract
import com.wilson.presenters.MetOfficeDataPresenter
import com.wilson.views.activity.HomeActivity
import com.wilson.views.adapters.ItemClickListener
import com.wilson.views.adapters.Section
import com.wilson.views.adapters.SectionedExpandableLayoutHelper
import com.wilson.views.widgets.ItemOffsetDecoration
import kotlinx.android.synthetic.main.controller_matoffice_data.view.*
import javax.inject.Inject
import android.widget.ArrayAdapter
import com.wilson.utils.Constants
import com.wilson.utils.SharedPrefsUtils


class MetOfficeDataController : BaseController(), MetOfficeDataContract.View, ItemClickListener, View.OnClickListener {


    var dataList = ArrayList<MetOfficeData>()
    lateinit var rootView: RelativeLayout
    lateinit var sectionedExpandableLayoutHelper: SectionedExpandableLayoutHelper

    @Inject
    lateinit var mPresenter: MetOfficeDataPresenter



    override val title: String
        get() = activity!!.getString(R.string.app_name)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        rootView = inflater.inflate(R.layout.controller_matoffice_data, container, false) as RelativeLayout
        this.mPresenter = MetOfficeDataPresenter(this as MetOfficeDataContract.View)
        MyApp.component.inject(this.mPresenter)
        val adapterOne = ArrayAdapter.createFromResource(activity,
                R.array.array_one, R.layout.spinner_textview)
        adapterOne.setDropDownViewResource(R.layout.spinner_dropdown)
        rootView.spOne.adapter = adapterOne
        var array_one_values= applicationContext!!.resources.getStringArray(R.array.array_one_values)
        rootView.spOne.setSelection(SharedPrefsUtils.getIntegerPreference(activity as Context,Constants.PREF_MATRIX,0))

        val adapterTwo = ArrayAdapter.createFromResource(activity,
                R.array.array_two, R.layout.spinner_textview)
        adapterTwo.setDropDownViewResource(R.layout.spinner_dropdown)
        rootView.spTwo.adapter = adapterTwo
        rootView.spTwo.setSelection(SharedPrefsUtils.getIntegerPreference(activity as Context,Constants.PREF_LOCATION,0))
        val itemDecoration = ItemOffsetDecoration(activity as Context, R.dimen.tasklist_space)
        rootView.recyclerView.addItemDecoration(itemDecoration)
        mPresenter.initProgressDialog()
        rootView.btnGetData.setOnClickListener(this)
        setOfflineScreen()
        mPresenter.getMetOfficeData(array_one_values[rootView.spOne.selectedItemPosition], rootView.spTwo.selectedItem.toString())
        return rootView
    }


    override fun onNetworkError() {
        (activity as HomeActivity).setOfflineView(View.VISIBLE)
        rootView.pbMetOfficeData.visibility = View.GONE
        rootView.spOne.isEnabled = false
        rootView.spTwo.isEnabled = false
//        rootView.btnGetData.isEnabled = false

    }


    override fun getActivityView(): FragmentActivity? {
        return activity as HomeActivity
    }

    override fun setMetOfficeData(tasks: List<MetOfficeData>) {
        dataList.clear()
        if(tasks!=null){
            setOnlineScreen()
            dataList.addAll(tasks)
            if (dataList.size > 0) {
                sectionedExpandableLayoutHelper = SectionedExpandableLayoutHelper(activity as Context,
                        rootView.recyclerView, this, 6)
                val mutableByLength: MutableMap<Int, MutableList<MetOfficeData>> = dataList.groupByTo(mutableMapOf()) { it.year }
                for (entry in mutableByLength) {
                    Log.e(entry.key.toString(), entry.value[0].value.toString())
                    sectionedExpandableLayoutHelper.addSection(entry.key.toString(), entry.value as ArrayList<MetOfficeData>)
                }
                sectionedExpandableLayoutHelper.notifyDataSetChanged()
                SharedPrefsUtils.setIntegerPreference(activity as Context,Constants.PREF_MATRIX,rootView.spOne.selectedItemPosition)
                SharedPrefsUtils.setIntegerPreference(activity as Context,Constants.PREF_LOCATION,rootView.spTwo.selectedItemPosition)
                rootView.recyclerView.visibility = View.VISIBLE
            } else {
                rootView.emptyView.visibility = View.VISIBLE
                rootView.recyclerView.visibility = View.GONE
            }
        }
        else{
            rootView.emptyView.visibility = View.VISIBLE
            rootView.recyclerView.visibility = View.GONE
            rootView.pbMetOfficeData.visibility = View.GONE
        }

    }

    override fun setOfflineScreen() {
        rootView.pbMetOfficeData.visibility = View.VISIBLE
        rootView.recyclerView.visibility = View.GONE
        rootView.spOne.isEnabled = false
        rootView.spTwo.isEnabled = false
//        rootView.btnGetData.isEnabled = false
    }

    override fun setOnlineScreen() {
        rootView.pbMetOfficeData.visibility = View.GONE
        rootView.recyclerView.visibility = View.VISIBLE
        rootView.spOne.isEnabled = true
        rootView.spTwo.isEnabled = true
//        rootView.btnGetData.isEnabled = true
    }

    override fun onClick(v: View?) {
        setOfflineScreen()
        var array_one_values= applicationContext!!.resources.getStringArray(R.array.array_one_values)
        mPresenter.getMetOfficeData(array_one_values[rootView.spOne.selectedItemPosition], rootView.spTwo.selectedItem.toString())
    }

    override fun itemClicked(item: MetOfficeData?) {

    }

    override fun itemClicked(section: Section?) {
    }



}
