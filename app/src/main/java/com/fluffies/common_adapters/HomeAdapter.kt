package com.fluffies.common_adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.fragments.home.HomeFragment
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import com.fluffies.utils.helper.others.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter(
    var context: HomeFragment,
    var datalist: HomeFragmentResponse
) : RecyclerView.Adapter<HomeAdapter.Vh?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
//           holder.itemView.tvDescription.setText(datalist.body.banners[position].description)
//        holder.itemView.tvDescription.text = HtmlCompat.fromHtml(
//            datalist.body.banners[position].description, HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//
//        holder.itemView.tvDescription.movementMethod = LinkMovementMethod.getInstance()


        holder.itemView.webView.settings.javaScriptEnabled = true

//        holder.itemView.webView.loadData(datalist.body.banners[position].description, "text/html; charset=utf-8", "UTF-8")
        holder.itemView.webView.loadDataWithBaseURL("file:///android_asset",
            replaceLinkTags(datalist.body.banners[position].description).toString(), "text/html", "utf-8", null);

        holder.itemView.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl(
                    "javascript:document.body.style.setProperty(\"color\", \"white\");"
                )
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {

                val intent = Intent(Intent.ACTION_VIEW, request!!.url)
                context.startActivity(intent)
                return true
            }
        }

        holder.itemView.webView.setBackgroundColor(Color.TRANSPARENT)

        Glide.with(context).load("$IMAGE_URL${datalist.body.banners[position].image}")
            .placeholder(R.drawable.dogsimg).into(holder.itemView.details_img)
    }

    override fun getItemCount(): Int {
        return datalist.body.banners.size
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img: ImageView

    }

    /**
     * to change the webview link color
     */
    fun replaceLinkTags(text: String): String? {
        var text = text
        text = ("<html><head>"
                + "<style type=\"text/css\">body{color:" + "#424242" + ";} a{color:#00B8D4; text-decoration:none; }"
                + "</style></head>"
                + "<body>" + text + "</body></html>")
        var str: String
        while (text.indexOf("\u0082") > 0) {
            if (text.indexOf("\u0082") > 0 && text.indexOf("\u0083") > 0) {
                str = text.substring(text.indexOf("\u0082") + 1, text.indexOf("\u0083"))
                text =
                    text.replace("\u0082" + str + "\u0083".toRegex(), "<a href=\"$str\">$str</a>")
            }
        }
        return text
    }
}
