package cn.EdgarLi.tools;


public class StaticData {
	private static String[] red = { "#800000", "#8B0000", "#A52A2A", "#B22222", "#DC143C", "#FF0000", "#C71585",
			"#D87093", "#FF1493", "#FF00FF", "#FF69B4", "#FFC0CB", "#FFB6C1", "#FFE4E1", "#FFF0F5" };
	private static String[] blue = { "#191970", "#000080", "#00008B", "#483D8B", "#0000CD", "#4169E1", "#1E90FF",
			"#6495ED", "#00BFFF", "#87CEFA", "#B0C4DE", "#ADD8E6", "#4682B4", "#008B8B", "#5F9EA0", "#00CED1",
			"#48D1CC", "#40E0D0", "#87CECB", "#B0E0E6", "#AFEEEE", "#E0FFFF", "#F0FFFF", "#F0F8FF", "#00FFFF" };
	public static String[] purple = {

			"#4B0082", "#800080", "#8B008B", "#9932CC", "#8A2BE2", "#9400D3", "#6A5ACD", "#9370DB", "#7B68EE",
			"#BA55D3", "#EE82EE", "#DDA0DD", "#D8BFD8", "#E6E6FA" };
	public static String[] grey = { "#000000", "#696969", "#808080", "#708090", "#778899", "#A9A9A9", "#C0C0C0",
			"#D3D3D3", "#DCDCDC", "#F5F5F5", "#F8F8FF", "#FFFFFF"  };
	public static String[] brown = { "#8B4513", "#A0522D", "#D2691E", "#CD5C5C", "#BC8F8F", "#F08080", "#FA8072",
			"#FFA07A", "#FF4500", "#FF6347", "#FF7F50", "#FF8C00", "#F4A460", "#CD853F", "#D2B48C", "#DEB887",
			"#F5DEB3", "#FFE4B5", "#FFDEAD", "#FFDAB9", "#FFE4C4", "#FAEBD7", "#FFEFD5", "#FFF8DC", "#FDF5E6",
			"#FAF0E6", "#FFF5EE" };

	public static String[] yellow = { "#FFFAFA", "#FFFAF0", "#FFFFF0", "#F5FFFA", "#B8860B", "#DAA520", "#FFD700",
			"#FFFF00", "#BDB76B", "#FOE68C", "#EEE8AA", "#F5F5DC", "#FFFACD", "#FAFAD2", "#FFFFE0", };
	public static String[] green = { "#2F4F4F", "#556B2F", "#808000", "#006400", "#228B22", "#2E8B57", "#008080",
			"#20B2AA", "#66CDAA", "#3CB371", "#8FBC8F", "#9ACD32", "#32CD32", "#00FF00", "#7FFF00", "#7CFC00", "#ADFF2F",
			"#00FA9A", "#00FF7F", "#90EE90", "#98F898", "#7FFFD4", "#F0FFF0"

	};

	static int convertHex(char x) {
		if (x <= '9' && x >= '0')
			return x - '0';
		else
			return x - 'A' + 10;

	}

	public static String[] ret(String[] args) {
		String[] RGB = new String[args.length];
		int Red, G, B;
		for (int i = 0; i < args.length; i++) {
			Red = convertHex(args[i].charAt(1)) * 16 + convertHex(args[i].charAt(2));
			G = convertHex(args[i].charAt(3)) * 16 + convertHex(args[i].charAt(4));
			B = convertHex(args[i].charAt(5)) * 16 + convertHex(args[i].charAt(6));
			RGB[i] = Red + "," + G + "," + B;
		}
		return RGB;
	}


	public static String[] getHex(int id) {
		switch (id) {
			case 0:
				return red;
			case 1:
				return blue;
			case 2:
				return green;
			case 3:
				return grey;
			case 4:
				return brown;
			case 5:
				return purple;
			case 6:
				return yellow;
		}
		return null;
	}

	public static String[] getRGB(int id) {
		return ret(getHex(id));
	}
}