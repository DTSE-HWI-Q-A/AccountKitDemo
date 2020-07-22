package com.hms.demo.example

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.support.api.entity.auth.Scope
import com.huawei.hms.support.hwid.HuaweiIdAuthManager
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //TODO 1
        val handler= Handler()
        handler.postDelayed({
            val scopes= listOf(Scope("email"))
            val authParams = HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                    .setScopeList(scopes)
                    .createParams()
            val service = HuaweiIdAuthManager.getService(this@SplashActivity, authParams)
            val task = service.silentSignIn()
            task.addOnSuccessListener {
                val intent= Intent(this@SplashActivity,ProfileActivity::class.java)
                intent.putExtra("account",it)
                startActivity(intent)
                finish()
            }
            task.addOnFailureListener{
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                finish()
            }
        },1000)

    }
}