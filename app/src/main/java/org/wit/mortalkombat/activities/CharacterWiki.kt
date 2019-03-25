package org.wit.mortalkombat.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.characterwiki_layout.*
import org.wit.mortalkombat.R

class CharacterWiki : AppCompatActivity() {
    var mywebview: WebView? = null
    //var WebView = webViewid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.wiki)

        setContentView(R.layout.characterwiki_layout)
        mywebview = findViewById<WebView>(R.id.webview)
        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
           }
        }
        mywebview!!.loadUrl("https://en.wikipedia.org/wiki/List_of_Mortal_Kombat_characters")
    }
}

       // WebView=findViewById(R.id.webViewid)
       // WebView.setWebViewClient( WebViewClient())

       // WebView.loadUrl("https://www.rosettahub.com/console/Federation.aspx#FederatedDashboard")
        //startActivity(intent)
