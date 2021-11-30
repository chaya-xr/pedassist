package com.example.pedestrianassist;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

public class Preview extends ViewGroup implements SurfaceHolder.Callback{

	private static final String TAG = "Preview:";
	SurfaceView mSurfaceView;
    SurfaceHolder mHolder;
    Camera mCamera;

   @SuppressWarnings("deprecation")
public Preview(Context context, Camera camera) {
        super(context);
        
        mCamera = camera;
        

        mSurfaceView = new SurfaceView(context);
        //addView(mSurfaceView);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
   /* @Override
    public void onClick(View v) {
        switch(mPreviewState) {
        case K_STATE_FROZEN:
            mCamera.startPreview();
            mPreviewState = K_STATE_PREVIEW;
            break;

        default:
            mCamera.takePicture( null, rawCallback, null);
            mPreviewState = K_STATE_BUSY;
        } // switch
        shutterBtnConfig();
    }*/

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		 // Surface will be destroyed when we return, so stop the preview.
	    if (mCamera != null) {
	        // Call stopPreview() to stop updating the preview surface.
	        mCamera.stopPreview();
	    }
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCamera(Camera camera) {
	    if (mCamera == camera) { return; }
	    
	    stopPreviewAndFreeCamera();
	    
	    mCamera = camera;
	    
	    if (mCamera != null) {
	        List<Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
	        //mSupportedPreviewSizes = localSizes;
	        requestLayout();
	      
	        try {
	            mCamera.setPreviewDisplay(mHolder);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      
	        // Important: Call startPreview() to start updating the preview
	        // surface. Preview must be started before you can take a picture.
	        mCamera.startPreview();
	    }
	}
	/**
	 * When this function returns, mCamera will be null.
	 */
	private void stopPreviewAndFreeCamera() {

	    if (mCamera != null) {
	        // Call stopPreview() to stop updating the preview surface.
	        mCamera.stopPreview();
	    
	        // Important: Call release() to release the camera for use by other
	        // applications. Applications should release the camera immediately
	        // during onPause() and re-open() it during onResume()).
	        mCamera.release();
	    
	        mCamera = null;
	    }
	}
	
	
    
}
