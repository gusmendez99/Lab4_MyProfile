package com.gustavomendez.lab4profile.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.gustavomendez.lab4profile.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WebViewFragment : Fragment() {

    companion object {
        var webViewRepo:WebView? = null

        fun newInstance(url:String, title:String): WebViewFragment {
            //Like Singleton, we return an instance filled with the Project info (url, title)
            val args = Bundle()
            args.putString("url", url)
            args.putString("title", title)
            val fragment = WebViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)

        (activity as AppCompatActivity).supportActionBar!!.title = arguments!!.getString("title")

        webViewRepo = view.findViewById<View>(R.id.web_view_repo) as WebView

        //Loads the URL we have passed from the MainActivity as a argument
        webViewRepo!!.loadUrl(arguments!!.getString("url"))

        val webSettings = webViewRepo!!.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        webViewRepo!!.webViewClient = WebViewClient()

        return view
    }






}
