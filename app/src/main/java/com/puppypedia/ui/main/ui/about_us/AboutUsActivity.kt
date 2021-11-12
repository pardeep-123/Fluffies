package com.puppypedia.ui.main.ui.about_us

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.AppConstant
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*


//************************0 -> About Us**********************
//************************1 -> Terms & Conditions**********************
//************************2 -> Privacy Policy**********************

class AboutUsActivity : AppCompatActivity(), Observer<RestObservable> {

    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)


        tb.iv_back.setOnClickListener {
            onBackPressed()
        }

        if (intent.getIntExtra(AppConstant.HEADING, 0) == 2) {
            tb.tv_title.text = getString(R.string.privacy_police)
            viewModel.getPrivacyPolicyAPI(this, true)
            viewModel.mResponse.observe(this, this)


        } else if (intent.getIntExtra(AppConstant.HEADING, 0) == 1) {
            tb.tv_title.text = getString(R.string.terms_conditions)
            viewModel.getTermsAPI(this, true)
            viewModel.mResponse.observe(this, this)

        } else {
            tb.tv_title.text = getString(R.string.about_us)
            viewModel.getAboutUsAPI(this, true)
            viewModel.mResponse.observe(this, this)
        }
    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is AboutusResponse) {
                    val aboutResponse: AboutusResponse = it.data
                    if (aboutResponse.code == Constants.success_code) {
                        tvText.text = HtmlCompat.fromHtml(
                            aboutResponse.body.value, HtmlCompat.FROM_HTML_MODE_LEGACY
                        )
                    } else {
                        Helper.showErrorAlert(this, aboutResponse.code as String)
                    }
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data as String)
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }
}