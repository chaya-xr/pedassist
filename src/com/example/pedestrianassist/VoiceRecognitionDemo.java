package com.example.pedestrianassist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
 
/**
 * A very simple application to handle Voice Recognition intents
 * and display the results
 */
public class VoiceRecognitionDemo extends Activity
{
 
    private static final int REQUEST_CODE = 1234;
    private ListView wordsList;
    private static final String welcomeText = "Welcome to Pedestrian Assist. Say Cross the Road to get started!";
    private TextToSpeechWrapper ttswrapper;
    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);
        
        //text to speech setup
        ttswrapper = new TextToSpeechWrapper(this,welcomeText);
 
        Button speakButton = (Button) findViewById(R.id.speakButton);
 
        wordsList = (ListView) findViewById(R.id.list);
        	
        // Disable button if no recognition service is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0)
        {
            speakButton.setEnabled(false);
            speakButton.setText("Recognizer not present");
        }
        else{
        	 ttswrapper.setOnUtteranceProgressListener(new UtteranceProgressListener() {

 				@Override
 				public void onDone(String utteranceId) {
 					startVoiceRecognitionActivity();
 					
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
        	
        }
     
     }
    
 
    /**
     * Handle the action of the button being clicked
     */
    public void speakButtonClicked(View v)
    {
        startVoiceRecognitionActivity();
    }
 
    /**
     * Fire an intent to start the voice recognition activity.
     */
    public void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUEST_CODE);
    }
 
    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    matches));
            if (matches.contains("cross the road")) {
                startCrossInitActivity();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    /*
     * Invoke activity to initiate crossing assistance
     */
    public void startCrossInitActivity()
    {
    	startActivity(new Intent(this,Start.class));
    }
    
}
