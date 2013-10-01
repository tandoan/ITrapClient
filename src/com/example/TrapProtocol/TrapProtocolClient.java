package com.example.TrapProtocol;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


public class TrapProtocolClient extends TrapProtocol{
	// Debugging
    private static final String TAG = "TapProtocolClient";
    private static final boolean D = true;
    
	private DataInputStream mDataInputStream;
	private OutputStream mOutputStream;
	private Context mContext;
	private File mAlbumStorageDir;

	public TrapProtocolClient( DataInputStream dataInputStream, OutputStream outputStream, Context context, File albumStorageDir){
		mDataInputStream = dataInputStream;
		mOutputStream = outputStream;
		mContext = context;
		mAlbumStorageDir = albumStorageDir;
	}
	
	public void run() throws IOException{
		int bytes;
		 // Keep listening to the InputStream while connected
        while (true) {
           
            	// Read from the InputStream
            	
            	//get the name
            	bytes = mDataInputStream.readInt(); 
               	byte[] nameInBytes = new byte[bytes];               	
               	mDataInputStream.read(nameInBytes, 0, bytes);
            	String name = new String(nameInBytes, "UTF-8");  
            	
            	//get the file
            	bytes = mDataInputStream.readInt();
            	int counter = bytes;
            	
            	byte[] buffer = new byte[1024];
            	int readAmount = 1024;
            	
            	
            	String fullPath = mAlbumStorageDir.toString() + File.separatorChar+name;
            	FileOutputStream outputStream = new FileOutputStream(new File(fullPath), true);
            	                	
            	//read and then write the stream directly to the 
            	while(counter > 0) {
            		Log.d(TAG, "reading bytes");
            		if(counter < 1024) {
            			readAmount = 1024 - counter;
            		}
            		mDataInputStream.read(buffer, 0, readAmount);
            		outputStream.write(buffer, 0, readAmount);
            		counter -= readAmount;
            	}
            	outputStream.flush();
            	outputStream.close();
            	Log.d(TAG, "file written");
            	
            	// add to gallery
            	Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(fullPath);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                mContext.sendBroadcast(mediaScanIntent);
                
                Log.d(TAG, "intent sent");
       
                
            
        }
	}
}
