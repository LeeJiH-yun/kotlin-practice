package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() { //extends 대신 :으로 상속을 나타낸다.
    private var time = 0 //시간 계산할 변수 0으로 초기화
    private var timerTask: Timer? = null //0.01초마다 이 변수를 증가시킨다.
    private var isRunning = false
    private var lap = 1

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //액티비티가 시작되면 최초로 호출되는 메서드 onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main) //액티비티가 표시할 레이아웃 파일을 지정한다.
        //R이란 안드로이드 스튜디오에서 자동으로 생성되는 리소스 정보를 가지는 클래스
        /** ResultActivity = 비만도 계산하기 **/
        /* binding.resultButton.setOnClickListener {
            //아무 입력도 안했을 경우 에러 처리
            if (binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()) {
                //인텐트는 데이터를 담아서 다른 액티비티에 전달하는 역할도 한다.
                val intent = Intent(this, ResultActivity::class.java).apply {
                    intent.putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    intent.putExtra("height", binding.heightEditText.text.toString().toFloat())
                }
                startActivity(intent) //액티비티 전환시 호출
            }
        } */
        /** 스톱워치 **/
        binding.fab.setOnClickListener {
            isRunning = !isRunning //버튼이 클릭되면 타이머가 동작 중인지 저장하는 변수의 값을 반전

            if (isRunning) { //상태에 따라 시작 또는 일시정지
               start()
            }
            else {
                pause()
            }
        }
        binding.lapButton.setOnClickListener {
            recordLapTime()
        }
        binding.resetFab.setOnClickListener {
            reset()
        }
    }
    /** 스톱워치 **/
    private fun pause() {
        //타이머 시작과 반대로 버튼을 누르면 시작 이미지로 교체한다.
        binding.fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        //실행 중인 타이머가 있다면 타이머를 취소한다.
        timerTask?.cancel()
    }
    private fun start() {
        //타이머 시작버튼을 누르면 이미지를 일시정지 이미지로 변경한다.
        binding.fab.setImageResource(R.drawable.ic_baseline_pause_24)
        timerTask = timer(period = 10) { //0.01초마다
            time++ //변수 증가
            var sec = time / 100
            var milli = time % 100
            runOnUiThread { // UI를 갱신한다.
                binding.secTextView.text = "$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }
    private fun recordLapTime() {
        //랩타임 기록 메서드
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        binding.lapLayout.addView(textView, 0)
        lap++
    }
    private  fun reset() {
        timerTask?.cancel() //실행 중인 타이머가 있다면 취소한다.

        time = 0 // 모든 변수와 화면에 표시되는 모든 것을 초기화
        isRunning = false

        binding.fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        binding.secTextView.text = "0"
        binding.milliTextView.text = "00"

        binding.lapLayout.removeAllViews() //모든 랩타임 제거
        lap = 1
    }
}