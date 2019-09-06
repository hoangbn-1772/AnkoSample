package com.sun.ankosample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.ankosample.data.Account
import com.sun.ankosample.data.User
import kotlinx.android.synthetic.main.activity_second.text_name
import kotlinx.android.synthetic.main.activity_second.text_password
import kotlinx.android.synthetic.main.activity_second.text_user_id
import kotlinx.android.synthetic.main.activity_second.text_username

class SecondActivity : AppCompatActivity() {

    private val user by lazy { intent.getParcelableExtra<User>(MainActivity.EXTRAS_USER) }
    private val account by lazy { intent.getParcelableExtra<Account>(MainActivity.EXTRAS_ACCOUNT) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        updateUi()
    }

    private fun updateUi() {
        text_user_id?.text = user.userId
        text_name?.text = user.userName
        text_username.text = account.username
        text_password.text = account.password
    }

    companion object {
        fun getIntent(context: Context, user: User, account: Account): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            val bundle = Bundle().apply {
                putParcelable(MainActivity.EXTRAS_USER, user)
                putParcelable(MainActivity.EXTRAS_ACCOUNT, account)
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            return intent.putExtras(bundle)
        }
    }
}
