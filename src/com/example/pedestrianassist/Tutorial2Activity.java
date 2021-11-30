package com.example.pedestrianassist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class Tutorial2Activity extends Activity implements CvCameraViewListener2 {
    private static final String    TAG = "OCVSample::Activity";

    private static final int       VIEW_MODE_RGBA     = 0;
    private static final int       VIEW_MODE_GRAY     = 1;
    private static final int       VIEW_MODE_CANNY    = 2;
    private static final int       VIEW_MODE_FEATURES = 5;

    private int                    mViewMode;
    private Mat                    mRgba;
    private Mat                    mIntermediateMat;
    private Mat                    mGray;

    private MenuItem               mItemPreviewRGBA;
    private MenuItem               mItemPreviewGray;
    private MenuItem               mItemPreviewCanny;
    private MenuItem               mItemPreviewFeatures;

    private CameraBridgeViewBase   mOpenCvCameraView;

    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");

                    // Load native library after(!) OpenCV initialization
                    System.loadLibrary("mixed_sample");

                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public Tutorial2Activity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.tutorial2_surface_view);
       
        
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        mItemPreviewRGBA = menu.add("Preview RGBA");
        mItemPreviewGray = menu.add("Preview GRAY");
        mItemPreviewCanny = menu.add("Canny");
        mItemPreviewFeatures = menu.add("Find features");
        return true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
        //String path = getResources().getString(R.drawable.nowalkorig);
        //Log.d(TAG, "*****"+path);
        
        
		
        //InputStream stream = getResources().openRawResource(R.raw.nowalk);
        
        String filePath1=null;
        String filePath2=null;
        //String path = getResources().getString(R.drawable.nowalk);
        //Log.d(TAG, "*****PATH"+path);
        /*Mat image1 = null;
        try{
        	image1 = Utils.loadResource(this.getApplicationContext(), R.drawable.nowalk);
        }catch(IOException e){Log.e(TAG, "image1 is null"+e);}
        */
       // Mat image2 = Highgui.imread("nowalk.jpg", Highgui.IMREAD_GRAYSCALE);
       // Mat image1 = Highgui.imread(path, Highgui.IMREAD_GRAYSCALE);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        final int viewMode = mViewMode;
        switch (viewMode) {
        case VIEW_MODE_GRAY:
            // input frame has gray scale format
            Imgproc.cvtColor(inputFrame.gray(), mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
            break;
        case VIEW_MODE_RGBA:
            // input frame has RBGA format
        	//mRgba = inputFrame.rgba();
        	/* No features .. empty keypoint
            
        	String PATH="android.resource://org.opencv.samples.tutorial2/" + R.drawable.nowalk;
        	mGray = Highgui.imread(PATH,Highgui.IMREAD_GRAYSCALE);
        	*/
        	
        	
        	/*String path = Environment.getExternalStorageDirectory() + "pan/";

        	images_to_be_stitched.add(Highgui.imread(path + "img1.jpg"));
        	images_to_be_stitched.add(Highgui.imread(path + "img2.jpg"));
        	images_to_be_stitched.add(Highgui.imread(path + "img3.jpg"));*/
        	/*Mat image = Highgui.imread("/mnt/sdcard/ban.jpg"); 
        	
        	
        	*/
        	
        	 InputStream is;
        	 
    		is = this.getResources().openRawResource(R.drawable.nowalk);
    		Bitmap bmInImg = BitmapFactory.decodeStream(is);

    		//byte [] mPhotoIntArray;
    		//mPhotoIntArray = new byte[bmInImg.getWidth() * bmInImg.getHeight()*4];
    		// Copy pixel data from the Bitmap into the 'intArray' array
    		
    		//bmInImg.getPixels(mPhotoIntArray, 0, bmInImg.getWidth(), 0, 0, bmInImg.getWidth(), bmInImg.getHeight());
    		/*ByteArrayOutputStream stream = new ByteArrayOutputStream();
    		bmInImg.compress(Bitmap.CompressFormat.JPEG, 100, stream);
    		mPhotoIntArray = stream.toByteArray();*/
    		
    		int bytes = bmInImg.getByteCount();
    		ByteBuffer buffer = ByteBuffer.allocate(bytes);
    		bmInImg.copyPixelsToBuffer(buffer);
    		byte [] mPhotoIntArray = buffer.array();
    		mRgba.put(0,0,mPhotoIntArray);
        	
    	
        	Imgproc.cvtColor(mRgba,mGray,Imgproc.COLOR_RGB2GRAY,4);
    		FindFeaturesTrainingImage(mGray.getNativeObjAddr(),mRgba.getNativeObjAddr());
            break;
        case VIEW_MODE_CANNY:
            // input frame has gray scale format
            mRgba = inputFrame.rgba();
            Imgproc.Canny(inputFrame.gray(), mIntermediateMat, 80, 100);
            Imgproc.cvtColor(mIntermediateMat, mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
            break;
        case VIEW_MODE_FEATURES:
            // input frame has RGBA format
            mRgba = inputFrame.rgba();
            mGray = inputFrame.gray();
            boolean match = FindFeatures(mGray.getNativeObjAddr(), mRgba.getNativeObjAddr());
            
        	String text = "";
        	
            if(match == true){
            	text = "*************************Found A Match.. Yaaaay!************";	
            }else{
            	text = "***********************Unfortunately this is not a match :(*****************";
            }
            //Toast toast = Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_SHORT);
        	//toast.show();
            Log.d("NoWalk Match?", text);
            break;
        }

        return mRgba;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "called onOptionsItemSelected; selected item: " + item);

        if (item == mItemPreviewRGBA) {
            mViewMode = VIEW_MODE_RGBA;
        } else if (item == mItemPreviewGray) {
            mViewMode = VIEW_MODE_GRAY;
        } else if (item == mItemPreviewCanny) {
            mViewMode = VIEW_MODE_CANNY;
        } else if (item == mItemPreviewFeatures) {
            mViewMode = VIEW_MODE_FEATURES;
        }

        return true;
    }

    public native boolean FindFeatures(long matAddrGr, long matAddrRgba);
    public native void FindFeaturesTrainingImage(long matAddrGr,long matAddrRgba);
}
