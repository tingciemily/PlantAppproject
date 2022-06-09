package com.example.plantapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.plantapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog:ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "登入"

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("請稍等")
        progressDialog.setMessage("正在登入中...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.noAccountTv.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        binding.loginBtn.setOnClickListener {
            validateData()
        }
    }

    private fun validateData(){
        email = binding.emaillEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emaillEt.error = "格式不對"
        }else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error = "不可空白"
        }else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin(){
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "登入 $email", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, recycleView::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "登入失敗 ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    private fun checkUser(){
        //如果用戶已登錄就跳轉到profileActivity
        //獲取用戶資訊
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }
}