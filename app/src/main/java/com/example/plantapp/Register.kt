package com.example.plantapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.plantapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var actionBar:ActionBar
    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "註冊"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("請稍等")
        progressDialog.setMessage("正在連線中...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {
            vaildateDate()
        }
    }

    private fun vaildateDate(){
        email = binding.emaillEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emaillEt.error = "格式不對"
        }else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error = "不可空白"
        }else if(password.length < 6){
            binding.passwordEt.error = "密碼至少6位數"
        }else{
            firebaseRegister();
        }
    }

    private fun firebaseRegister(){
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "創建用戶: $email", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "註冊失敗 ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //點擊按鈕，返回上一個Activity
        return super.onSupportNavigateUp()
    }
}