package fr.iutlens.mmi.fight.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Utils {
	
	public static String getStringResourceByName(Context context, String aString) {
	      String packageName = context.getPackageName();
	      int resId = context.getResources().getIdentifier(aString, "string", packageName);
	      return context.getString(resId);
	    }
	
	public static Bitmap loadImage(Context context, int id) {

//		Drawable blankDrawable = context.getResources().getDrawable(id);
//		Bitmap b =((BitmapDrawable)blankDrawable).getBitmap();

		Bitmap b = BitmapFactory.decodeResource(context.getResources(), id);
		return b;
	}
	
	public static Bitmap loadImages(Context context, int id1, int id2) {
		Drawable blankDrawable = context.getResources().getDrawable(id1);
		Bitmap b =((BitmapDrawable)blankDrawable).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
		Canvas c = new Canvas(b);
		c.drawBitmap(loadImage(context,id2),0,0,null);
		return b;
	}
}
