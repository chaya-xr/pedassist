package com.example.pedestrianassist;

import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

public class TextToSpeechWrapper implements OnInitListener {
	private TextToSpeech tts;
	String textToSpeak;
	Context context;
	
	
	public TextToSpeechWrapper (Context ctxt,String text) {
		tts = new TextToSpeech(ctxt, this);
		context = ctxt;
		textToSpeak = text;
	}
	
	public int success(){
		return tts.SUCCESS;
	}

	@Override
	public void onInit(int status) {
		
		if (status == TextToSpeech.SUCCESS) {
			 
            int result = tts.setLanguage(Locale.US);
 
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
            	Log.d("TTSWrapper","tts set");
                //btnSpeak.setEnabled(true);
            	//default settings -- change if necessary
            	tts.setPitch((float) 1.0);
            	tts.setSpeechRate(1);
                speakOut();
 
        }} else {
            Log.e("TTS", "Initilization Failed!");
        }
	}
	
	
	
	private void speakOut() {
		speakOut(textToSpeak);
	}
	public void speakOut(String text) {
		 
        //String text = welcomeText;
		HashMap map = new HashMap();
		map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
        
    }
	
	public boolean isSpeaking(){
		return tts.isSpeaking();
	}
	public int setOnUtteranceProgressListener (UtteranceProgressListener listener){
		 return tts.setOnUtteranceProgressListener(listener);
	 }
	
	public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

    }
	
	
}
