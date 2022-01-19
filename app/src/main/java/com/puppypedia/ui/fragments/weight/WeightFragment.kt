package com.puppypedia.ui.fragments.weight

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.WeightAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.add_weight.AddWeightActivity
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.SwipeRevealLayout
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.fragment_weight.*


class WeightFragment : Fragment(), Observer<RestObservable> ,WeightAdapter.OnDeleteClick{

    lateinit var v: View
    var petId: String = ""
    lateinit var dialog: Dialog
   var postId = ""
    lateinit var sharedPrefUtil: SharedPrefUtil
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass
    private lateinit var weightAdapter: WeightAdapter
    var aboutResponse: GetWeightResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_weight, container, false)
        //mValidationClass = ValidationsClass.getInstance()
        sharedPrefUtil = SharedPrefUtil(requireContext())
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddWeight.setOnClickListener {
            startActivity(Intent(requireContext(), AddWeightActivity::class.java))
        }

        // code for swipe

    }
    override fun onResume() {
        super.onResume()
        apiWeightChart()
    }
    private fun apiWeightChart() {
        viewModel.getWeightApi(requireActivity(), sharedPrefUtil.petId, true)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetWeightResponse) {
                    aboutResponse = it.data
                    weightAdapter = WeightAdapter(requireContext(), aboutResponse?.body?.weightCharts!!,this,"weight")
                    rvWeight.adapter = weightAdapter
                } else if (it.data is DeleteResponse){
                    apiWeightChart()
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(requireActivity(), it.data as String)
                } else {
                    Helper.showErrorAlert(requireActivity(), it.error.toString())
                }
            }
        }
    }

    override fun onDeleteClick(postId: String, petId: String) {

        deleteDialog(postId,petId)
    }

    private fun deleteDialog(postId: String, petId: String) {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)
        dialog.show()
        dialog.findViewById<TextView>(R.id.tvTitle).text = "Are you sure you want to remove this record"
        dialog.findViewById<AppCompatButton>(R.id.btnYes).setOnClickListener {

            dialog.dismiss()
            apiDeletePet(postId,petId)
        }
        dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
            weightAdapter.notifyDataSetChanged()
        }
    }

    fun apiDeletePet(postId: String, petId: String) {
        viewModel.apiWeightPet(requireActivity(), postId, true)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }


}