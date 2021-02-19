package com.sudeepghimire.myfood
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sudeepghimire.myfood.Repository.UserRepository
import com.sudeepghimire.myfood.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    private lateinit var etname: EditText
    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var etphoneNo: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etaddress: EditText
    private lateinit var btnsignup: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etname = findViewById(R.id.etName)
        etemail = findViewById(R.id.etEmail)
        etaddress = findViewById(R.id.etAddress)
        etphoneNo = findViewById(R.id.etPhoneNo)
        etpassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPass)
        btnsignup = findViewById(R.id.btnSignup)
        btnsignup.setOnClickListener {
            val name = etname.text.toString()
            val email = etemail.text.toString()
            val address = etaddress.text.toString()
            val password = etpassword.text.toString()
            val phoneNo = etphoneNo.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                etpassword.error = "Password does not match"
                etpassword.requestFocus()
                return@setOnClickListener
            } else {

                // Api code goes here
                val user =
                    User(name = name, email=email, address = address,phoneNo = phoneNo, password = password)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val userRepository = UserRepository()
                        val response = userRepository.registerUser(user)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "User registration successful!!", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Username cannot be duplicate", Toast.LENGTH_SHORT
                            ).show()


                        }
                    }
                }
            }
        }
    }
}
