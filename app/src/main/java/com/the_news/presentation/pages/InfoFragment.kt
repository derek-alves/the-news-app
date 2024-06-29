package com.the_news.presentation.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.the_news.MainActivity
import com.the_news.R
import com.the_news.databinding.FragmentInfoBinding
import com.the_news.presentation.viewModel.NewsViewModel


class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if (article.url?.isNotEmpty() == true) {
                loadUrl(article.url)
            }
        }

        fragmentInfoBinding.fbSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Saved Successfully!", Snackbar.LENGTH_SHORT).show()
        }
    }


}