package com.sun.ankosample

import android.R.attr
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.sun.ankosample.data.Account
import com.sun.ankosample.data.User
import kotlinx.android.synthetic.main.activity_main.btn_dialog
import kotlinx.android.synthetic.main.activity_main.btn_goto
import kotlinx.android.synthetic.main.activity_main.btn_logging
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.alert
import org.jetbrains.anko.applyRecursively
import org.jetbrains.anko.button
import org.jetbrains.anko.customView
import org.jetbrains.anko.debug
import org.jetbrains.anko.dip
import org.jetbrains.anko.editText
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noButton
import org.jetbrains.anko.padding
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.selector
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.textView
import org.jetbrains.anko.themedButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), OnClickListener, AnkoLogger {

    private val user = User("123", "Suner")
    private val account = Account("sun-asterisk", "taukhongpit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        //MainActivityUI().setContentView(this);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_goto -> goToScreenWithMoreParams()
            R.id.btn_dialog -> customDialog()
            R.id.btn_logging -> logging()
        }
    }

    private fun initComponents() {
        btn_goto?.setOnClickListener(this)
        btn_dialog?.setOnClickListener(this)
        btn_logging?.setOnClickListener(this)

        //createLoginUi()
        //createUIUseAnkoLayout()
    }

    private fun gotoScreenBasic() {
        startActivity(SecondActivity.getIntent(this, user, account))
    }

    private fun goToScreenWithMoreParams() {
        startActivity(intentFor<SecondActivity>(EXTRAS_USER to user, EXTRAS_ACCOUNT to account).singleTop())
    }

    private fun gotoWithOneParams() {
        startActivity(intentFor<SecondActivity>(EXTRAS_USER to user))
    }

    /**
     * Logging use Anko
     */
    private fun logging() {
        info { "Hello Sun*" }
        debug { "Make Awesome" }
    }

    /**
     * Show simple alert dialog
     */
    private fun showSimpleDialog() {
        alert("Hi, I'm Rashi", "Do you find my posts helpful?") {
            yesButton {
                toast("Thanks, will start writing more :)")
            }
            noButton {
                toast("Alright, let's start working on improvements")
            }
        }.show()
    }

    @SuppressLint("SetTextI18n")
    private fun customDialog() {
        alert {
            title = "Hi, Its Rashi again"
            positiveButton("Done") { toast("Yay :D") }
            customView {
                verticalLayout {
                    textView("Follow me for posts on tech, design and spirituality.")
                    button {
                        text = "I'm in!"
                        onClick {
                            info("thanks for following")
                        }
                    }
                    padding = dip(16)
                }
            }
        }.show()
    }

    /**
     * Show an alert dialog with a list of text items
     */
    private fun showDialogWithListItem() {
        val countries = listOf("Russia", "USA", "Japan", "Australia")
        selector("Where are you from?", countries) { dialogInterface, i ->
            toast("So you're living in ${countries[i]}, right?")
        }
    }

    /**
     * Anko misc - dimension
     */
    private fun createLoginUi() {
        verticalLayout {
            button("Login").apply {
                height = dip(48)
            }
            attr.layout_below.and(R.id.btn_misc)
        }.applyRecursively { view ->
            view.onClick {
                toast("Approve Login")
            }
        }
    }

    /**
     * Anko Layout
     */
    @SuppressLint("PrivateResource")
    private fun createUIUseAnkoLayout() {
        verticalLayout {
            button("Anko Layout") {
                textSize = 26f
            }
            themedButton("Theme Button", theme = R.style.Base_ThemeOverlay_AppCompat_Dark_ActionBar)
        }
    }

    companion object {
        const val EXTRAS_USER = "EXTRAS_USER"
        const val EXTRAS_ACCOUNT = "EXTRAS_ACCOUNT"
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }
}
