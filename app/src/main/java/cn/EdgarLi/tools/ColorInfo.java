package cn.EdgarLi.tools;

public class ColorInfo {

	public ColorInfo() {
	}

	public ColorInfo(String hex, String rgb) {
		this.hex = hex;
		this.rgb = rgb;
	}

	public ColorInfo(String hex, String rgb,int color) {
		this.hex = hex;
		this.rgb = rgb;
		this.color=color;
	}

	public String hex;
	public String rgb;
	public int color;

}
