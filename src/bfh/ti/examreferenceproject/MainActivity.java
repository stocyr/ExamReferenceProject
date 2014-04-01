/*
 ***************************************************************************
 * \brief   Embedded-Android (BTE5484)
 *	    	Exam 08.04.2014
 * \file    MainActivity.java
 * \version 1.0
 * \date    01.04.2014
 * \author  Cyril Stoller
 *
 * \remark  Last Modifications:
 * \remark  V1.0, stolc2, 01.04.2014
 ***************************************************************************
 */
package bfh.ti.examreferenceproject;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ADC adc;
	private TextView viewADCValue;

	private SysfsFileGPIO led1;
	// private SysfsFileGPIO led2;
	// private SysfsFileGPIO led3;
	// private SysfsFileGPIO led4;

	private SysfsFileGPIO button1;
	// private SysfsFileGPIO button2;
	// private SysfsFileGPIO button3;
	// private SysfsFileGPIO button4;

	private int adcValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set up ADC
		adc = new ADC();

		// set up LEDs
		led1 = new SysfsFileGPIO(SysfsFileGPIO.LED_L1);
		// led2 = new SysfsFileGPIO(SysfsFileGPIO.LED_L2);
		// led3 = new SysfsFileGPIO(SysfsFileGPIO.LED_L3);
		// led4 = new SysfsFileGPIO(SysfsFileGPIO.LED_L4);

		led1.set_direction_out();
		// led2.set_direction_out();
		// led3.set_direction_out();
		// led4.set_direction_out();

		// set up buttons
		button1 = new SysfsFileGPIO(SysfsFileGPIO.BUTTON_T1);
		// button2 = new SysfsFileGPIO(SysfsFileGPIO.BUTTON_T2);
		// button3 = new SysfsFileGPIO(SysfsFileGPIO.BUTTON_T3);
		// button4 = new SysfsFileGPIO(SysfsFileGPIO.BUTTON_T4);

		button1.set_direction_in();
		// button2.set_direction_in();
		// button3.set_direction_in();
		// button4.set_direction_in();

		// set up adc timer, delay 0ms and repeat in 50ms
		Timer adcTimer = new Timer();
		ADCTimerTask adcTimerTask = new ADCTimerTask();
		adcTimer.schedule(adcTimerTask, 0, 50);

		// set up button timer, delay 0ms and repeat in 50ms
		Timer buttonTimer = new Timer();
		ButtonTimerTask buttonTimerTask = new ButtonTimerTask();
		buttonTimer.schedule(buttonTimerTask, 0, 50);

		// set up textView
		viewADCValue = (TextView) findViewById(R.id.myTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ADCTimerTask extends TimerTask {
		@Override
		public void run() {
			// Read analog value and display it on the screen
			adcValue = adc.read_adc(ADC.ADC_IN4);

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// update textView
					viewADCValue.setText("ADC-Value: " + adcValue);
				}
			});
		}
	}

	class ButtonTimerTask extends TimerTask {
		@Override
		public void run() {
			// Read button values and react accordingly
			// Warning: BUTTONS AND LEDS ARE ACTIVE-LOW
			led1.write_value(button1.read_value());
		}
	}
}
