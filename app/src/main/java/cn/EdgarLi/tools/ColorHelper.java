package cn.EdgarLi.tools;

import android.graphics.Color;

public class ColorHelper {
	
	private final static String[] alpha = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	
	public static String toHex(int color){
		String answer = "";
		color = color & 0xFFFFFF;
		while (color > 0) {
			int a = color % 16;
			color /= 16;
			answer = alpha[a] + answer;
		}
		while (answer.length() < 6) {
			answer = "0" + answer;
		}
		return answer;
	}
	
	public static int fitColor(int color){
		return (Color.red(color)+Color.green(color)+Color.blue(color))/3>180?Color.BLACK:Color.WHITE;
		//return Color.rgb(255-Color.red(color),255-Color.green(color),255-Color.blue(color));
	}

}
