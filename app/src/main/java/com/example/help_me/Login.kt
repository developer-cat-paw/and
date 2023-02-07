package com.example.help_me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.help_me.databinding.ActivityLoginBinding
import com.google.gson.annotations.SerializedName
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Login : AppCompatActivity() {

    private var mbinding: ActivityLoginBinding ?= null
    private val binding get() = mbinding!!

    private val baseUrl: String = "" //http주소

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var id : String = ""
        var password : String = ""
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginRequest::class.java)
        val call = service.getLogin(id, password)
        call.equals(object : Callback<Profile>{
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if(response.code() == 200){
                    val loginResponse = response.body()
                    val image = loginResponse?.image //프로필 사진
                    val name = loginResponse?.image //이름
                    val shortText = loginResponse?.shortText //자기소개글

                }
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.d("상태","error : ${t.message}")
            }
        })
    }

    interface LoginRequest{
        @GET("")
        fun getLogin(
            @Query("id") id: String,
            @Query("password") password: String
        )
    }
}

class Profile{
    @SerializedName("image") var image: String = ""
    @SerializedName("name") var name: String = ""
    @SerializedName("shortText") var shortText: String = ""
    @SerializedName("android") var android: Boolean = false
    @SerializedName("ios") var ios: Boolean = false
    @SerializedName("web") var web: Boolean = false
    @SerializedName("sever") var sever: Boolean = false
    @SerializedName("ai") var ai: Boolean = false
    @SerializedName("design") var design: Boolean = false
}
