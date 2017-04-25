package com.smartmobilesofware.ocrapiservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends Activity implements OnClickListener{

	private final int RESPONSE_OK = 200;
	private static final int CAMERA_CODE = 101, GALLERY_CODE = 201, CROPING_CODE = 301;

 private Button btn_select_image,prd,gett;
 private ImageView imageView;
 private Uri mImageCaptureUri;
 private File outPutFile = null;
 TextView tv;
 	//=================================================
 	private String apiKey="T9AA967cJJ";
	String fpth="";
	
	 String y="";
	//=================================================
	public String url="";
	public String namespace="http://tempuri.org/";
	public String method="evaluate";
	public String soapaction=namespace+method;
	//=================================================
	
 @Override
 protected void onCreate(Bundle savedInstanceState) 
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  try{
	  if(android.os.Build.VERSION.SDK_INT>9){
		  StrictMode.ThreadPolicy p=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		  StrictMode.setThreadPolicy(p);
	  }
  }
  catch (Exception e) {
  }
  
  outPutFile = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
  
  btn_select_image = (Button) findViewById(R.id.btn_select_image);
  prd = (Button) findViewById(R.id.button1);
  gett = (Button) findViewById(R.id.button2);
  imageView = (ImageView) findViewById(R.id.img_photo);

  btn_select_image.setOnClickListener(this);
  prd.setOnClickListener(this);
  gett.setOnClickListener(this);
 }

 private void selectImageOption() 
 {
  final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel" };

  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
  builder.setTitle("Add Photo!");
  builder.setItems(items, new DialogInterface.OnClickListener() {
   @Override
   public void onClick(DialogInterface dialog, int item) {

    if (items[item].equals("Capture Photo")) 
    {
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
     	File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp1.jpg");
     	mImageCaptureUri = Uri.fromFile(f);
     	intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
     	startActivityForResult(intent, CAMERA_CODE);
    }
    else if (items[item].equals("Choose from Gallery")) 
    {
    	Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    	startActivityForResult(i, GALLERY_CODE);
    }
    else if (items[item].equals("Cancel")) 
    {
    	dialog.dismiss();
    }
   }
  });
  builder.show();
 }

 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) 
  {
	  try {
		  mImageCaptureUri = data.getData();
		  System.out.println("Gallery Image URI : "+mImageCaptureUri);
		  CropingIMG();
	  }
	   catch (Exception e) {
		   e.printStackTrace();
	   }
  }
  else if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {
   try
   {
	   System.out.println("Camera Image URI : "+mImageCaptureUri);
	   CropingIMG();
  }
  catch (Exception e) {
	   e.printStackTrace();
  }
  } else if (requestCode == CROPING_CODE) {
   
   try {
	   if(outPutFile.exists()){
		   Bitmap photo = decodeFile(outPutFile);              
		   imageView.setImageBitmap(photo);
     
		   fpth=outPutFile.getPath();
    }
    else {
     Toast.makeText(getApplicationContext(), "Error while save image", Toast.LENGTH_SHORT).show();
    }
   }
   catch (Exception e) {
	   e.printStackTrace();
   }
  }
 }

 private void CropingIMG() {
   @SuppressWarnings("rawtypes")
  final ArrayList<Object> cropOptions = new ArrayList<Object>();

  Intent intent = new Intent("com.android.camera.action.CROP");
  intent.setType("image/*");

  List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
  int size = list.size();
  if (size == 0) {         
   Toast.makeText(this, "Cann't find image croping app", Toast.LENGTH_SHORT).show();
   return;
  }
  else {
   intent.setData(mImageCaptureUri);
   intent.putExtra("outputX", 512);
   intent.putExtra("outputY", 240);
   intent.putExtra("aspectX", 1);
   intent.putExtra("aspectY", 0.5);
   intent.putExtra("scale", true);

   //intent.putExtra("return-data", true);

   //Create output file here
   intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outPutFile));

   if (size == 1)
   {
	   Intent i   = new Intent(intent);
	   ResolveInfo res = (ResolveInfo) list.get(0);
	   i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
	   startActivityForResult(i, CROPING_CODE);
   } 
//   else {
//    for (ResolveInfo res : list) {
//     final CropingOption co = new CropingOption();
//
//     co.title  = getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
//     co.icon  = getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
//     co.appIntent= new Intent(intent);
//     co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//     cropOptions.add(co);
//    }
//
//    CropingOptionAdapter adapter = new CropingOptionAdapter(getApplicationContext(), cropOptions);
//
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    builder.setTitle("Choose Croping App");
//    builder.setCancelable(false);
//    builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
//     public void onClick( DialogInterface dialog, int item ) {
////         startActivityForResult( cropOptions.get(item).appIntent, CROPING_CODE);
//         startActivityForResult( cropOptions.get(item).get, CROPING_CODE);
//     }
//    });
//
//    builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
//     @Override
//     public void onCancel( DialogInterface dialog ) {
//
//      if (mImageCaptureUri != null ) {
//       getContentResolver().delete(mImageCaptureUri, null, null );
//       mImageCaptureUri = null;
//      }
//     }
//    } );
//    
//    AlertDialog alert = builder.create();
//    alert.show();
//   }
  }
 }

 private Bitmap decodeFile(File f) {
  try {
   // decode image size
   BitmapFactory.Options o = new BitmapFactory.Options();
   o.inJustDecodeBounds = true;
   BitmapFactory.decodeStream(new FileInputStream(f), null, o);

   // Find the correct scale value. It should be the power of 2.
   final int REQUIRED_SIZE = 512;
   int width_tmp = o.outWidth, height_tmp = o.outHeight;
   int scale = 1;
   while (true) {
    if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
     break;
    width_tmp /= 2;
    height_tmp /= 2;
    scale *= 2;
   }

   // decode with inSampleSize
   BitmapFactory.Options o2 = new BitmapFactory.Options();
   o2.inSampleSize = scale;
   return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
  } catch (FileNotFoundException e) {
  }
  return null;
 }

@Override
public void onClick(View arg0) {
	

	if(arg0==btn_select_image){
	    selectImageOption();
	}
	else if (arg0==prd) 
	{
		final String langCode = "en";
		if (fpth != null && !apiKey.equals("") && !langCode.equals("")) {
			final ProgressDialog dialog = ProgressDialog.show( MainActivity.this, "Loading ...", "Converting to text.", true, false);
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					final OCRServiceAPI apiClient = new OCRServiceAPI(apiKey);
					apiClient.convertToText(langCode, fpth);
					
					// Doing UI related code in UI thread
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							dialog.dismiss();
							y=apiClient.getResponseText()+"";
							if(y.equalsIgnoreCase("")){
								y="No Result";
							}
							
							
							
							// Showing response dialog
							final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
							alert.setMessage(y);
							alert.setPositiveButton(
								"OK",
								new DialogInterface.OnClickListener() {
									public void onClick( DialogInterface dialog, int id) {
										Toast.makeText(MainActivity.this, y+"", Toast.LENGTH_SHORT).show();
							//---------------------------------------------------------------------------------------------------							
										 tv=(TextView)findViewById(R.id.textView1);
										tv.setText(y);
         //---------------------------------------------------------------------------------------------------
									}
								});
							
							// Setting dialog title related from response code
							if (apiClient.getResponseCode() == RESPONSE_OK) {
								alert.setTitle("Success");
							} else {
								alert.setTitle("Faild");
							}							
							alert.show();
						}
					});
				}
			});
			thread.start();
		} else {
			Toast.makeText(MainActivity.this, "All data are required.", Toast.LENGTH_SHORT).show();
		}	
	}else if (arg0==gett) {
		y=tv.getText().toString().trim();
		if(y.equalsIgnoreCase("No Result"))
		{
			Toast.makeText(getApplicationContext(),"No Result", Toast.LENGTH_LONG).show();
		}
		else{
		try 
		{	String re="";
			String eq=y;
			
			 SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			url=sh.getString("url", "");
			
			
			
			
			SoapObject sop=new SoapObject(namespace,method);
			sop.addProperty("a", eq);
			
			SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			senv.setOutputSoapObject(sop);
			senv.dotNet=true;
			
			HttpTransportSE htp=new HttpTransportSE(url);				
			htp.call(soapaction, senv);
			
			String result=senv.getResponse().toString();
			Intent i=new Intent(getApplicationContext(), ResultView.class);
			i.putExtra("re", result);
			startActivity(i);
			
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_LONG).show();
		}
		}
	}
}
}