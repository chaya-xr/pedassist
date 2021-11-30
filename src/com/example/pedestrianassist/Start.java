package com.example.pedestrianassist;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/*
 * TODO:
 * 1. Start Camera.
 * 2.Enable TextToSpeech - feedback via voice
 * 3. take picture
 * 4. Identify walking man.
 * 5. enable voice assist in background
 */
public class Start extends Activity {
	TextToSpeechWrapper txtToSpeech;
	CameraInput cameraInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		txtToSpeech = new TextToSpeechWrapper(this,"Starting Camera");
		txtToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {

				@Override
				public void onDone(String utteranceId) {
					//startCamera();
					
				}

				@Override
				public void onError(String utteranceId) {
					// TODO Auto-generated method stub
					Log.e("VoiceRecognition","Text To Speech Failed");
					
				}

				@Override
				public void onStart(String utteranceId) {
					// TODO Auto-generated method stub
					//startVoiceRecognitionActivity();
					
				}
			}); 
		//VoiceRecognitionDemo voiceAssist = new VoiceRecognitionDemo();
		//voiceAssist.startVoiceRecognitionActivity();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	public void onCaptureButtonClick(View v){
		if(cameraInput!=null)
			cameraInput.takePicture();
	}
	public void startCamera(){
		cameraInput = new CameraInput(this);
		// Create an instance of Camera
        //mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
       // mPreview = new Preview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(cameraInput.getPreview());
        /*Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get an image from the camera
                    //mCamera.takePicture(null, null, mPicture);
                	cameraInput.takePicture();
                }
            }
        );*/
	}
	

}
