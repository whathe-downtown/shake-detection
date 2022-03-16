package com.example.shake_detection

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shake_detection.ShakeDetector.OnShakeListener


private var mSensorManager: SensorManager? = null
private var mAccelerometer: Sensor? = null
private var mShakeDetector: ShakeDetector? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ShakeDetector initialization
        // ShakeDetector initialization
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector()
        mShakeDetector!!.setOnShakeListener(object : OnShakeListener {
            override fun onShake(count: Int) {
                //감지시 할 작업 작성
                val intent = Intent(this@MainActivity, CreateActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager!!.registerListener(
            mShakeDetector,
            mAccelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    // background 상황에서도 흔들림을 감지하고 적용할 필요는 없다
    override fun onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager!!.unregisterListener(mShakeDetector)
        super.onPause()
    }
}