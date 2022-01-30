package com.elahe.randomuser.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elahe.randomuser.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.IllegalStateException

interface BaseView {
    val viewContext: Context?
    val rootView: CoordinatorLayout?

    fun setProgressIndicator(mustShow: Boolean) {
        rootView.let {
            viewContext.let { context ->
                var loadingView = it?.findViewById<View>(R.id.loadingView)
                if (loadingView == null && mustShow) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                    it?.addView(loadingView)
                }
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        rootView?.let {
            Snackbar.make(it, message, duration).show()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showError(customException: CustomException) {
        viewContext?.let {
            when (customException.type) {
                CustomException.Type.SIMPLE -> showSnackBar(
                    customException.serverMessage
                        ?: it.getString(customException.userFriendlyMessage)
                )
                CustomException.Type.AUTH -> {
                }
            }
        }
    }
}

abstract class BaseActivity
    : AppCompatActivity(), BaseView {
    override val viewContext: Context?
        get() = this

    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("RootView must be instance of CoordinatorLayout")
            } else
                return viewGroup
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }
}

abstract class BaseFragment

    : Fragment(), BaseView {
    override val viewContext: Context?
        get() = context

    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}

abstract class BaseViewModel : ViewModel() {
    val progressBarLiveData = MutableLiveData<Boolean>()
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
