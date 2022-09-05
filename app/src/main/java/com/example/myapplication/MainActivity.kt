package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() { //extends 대신 :으로 상속을 나타낸다.
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //액티비티가 시작되면 최초로 호출되는 메서드 onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //loadData()
        //setContentView(R.layout.activity_main) //액티비티가 표시할 레이아웃 파일을 지정한다.
        //R이란 안드로이드 스튜디오에서 자동으로 생성되는 리소스 정보를 가지는 클래스
        binding.resultButton.setOnClickListener {
            //아무 입력도 안했을 경우 에러 처리
            if (binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()) {
                //인텐트는 데이터를 담아서 다른 액티비티에 전달하는 역할도 한다.
//                saveData(
//                    //마지막에 입력한 내용 저장하기
//                    binding.heightEditText.text.toString().toFloat(),
//                    binding.weightEditText.text.toString().toFloat()
//                )
                val intent = Intent(this, ResultActivity::class.java).apply {
                    intent.putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    intent.putExtra("height", binding.heightEditText.text.toString().toFloat())
                }
                startActivity(intent) //액티비티 전환시 호출
            }
        }
    }
}

//private fun saveData(height: Float, weight: Float) {
//    //데이터 저장하기
//    val pref = PreferenceManager.getDefaultSharedPreferences(this)
//    val editor = pref.edit()
//
//    editor.putFloat("KEY_HEIGHT", height).putFloat("KEY_WEIGHT", weight).apply()
//}

//private fun loadData() {
//    //데이터 불러오기
//    val pref = PreferenceManager.getDefaultSharedPreferences(this)
//    val height = pref.getFloat("KEY_HEIGHT", 0f)
//    val weight = pref.getFloat("KEY_WEIGHT", 0f)
//
//    if (height != 0f && weight != 0f) {
//        binding.heightEditText.setText(height.toString())
//        binding.weightEditText.setText(weight.toString())
//    }
//}