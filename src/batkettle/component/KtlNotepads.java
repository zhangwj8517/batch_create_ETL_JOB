package batkettle.component;

public class KtlNotepads {
	private String noteXML;
	private String note;
	private String xloc = new String();
	private String yloc = new String();
	private final String width = "\t\t<width>170</width>\n";
	private final String heigth = "\t\t<heigth>26</heigth>\n";
	private final String fontname = "\t\t<fontname>Microsoft YaHei UI</fontname>\n";
	private final String fontsize = "\t\t<fontsize>9</fontsize>\n";
	private final String fontbold = "\t\t<fontbold>N</fontbold>\n";
	private final String fontitalic = "\t\t<fontitalic>N</fontitalic>\n";
	private final String fontcolorred = "\t\t<fontcolorred>0</fontcolorred>\n";
	private final String fontcolorgreen = "\t\t<fontcolorgreen>0</fontcolorgreen>\n";
	private final String fontcolorblue = "\t\t<fontcolorblue>0</fontcolorblue>\n";
	private final String backgroundcolorred = "\t\t<backgroundcolorred>255</backgroundcolorred>\n";
	private final String backgroundcolorgreen = "\t\t<backgroundcolorgreen>205</backgroundcolorgreen>\n";
	private final String backgroundcolorblue = "\t\t<backgroundcolorblue>112</backgroundcolorblue>\n";
	private final String bordercolorred = "\t\t<bordercolorred>100</bordercolorred>\n";
	private final String bordercolorgreen = "\t\t<bordercolorgreen>100</bordercolorgreen>\n";
	private final String bordercolorblue = "\t\t<bordercolorblue>100</bordercolorblue>\n";
	private final String drawshadow = "\t\t<drawshadow>Y</drawshadow>\n";

	public KtlNotepads(String note, String xloc, String yloc) {
		this.note = "\t\t<note>ODS&#x76ee;&#x6807;&#x8868;&#x3a;" + note + "</note>\n";
		this.xloc = "\t\t<xloc>" + xloc + "</xloc>\n";
		this.yloc = "\t\t<yloc>" + yloc + "</yloc>\n";
	}

	public String getXml() {
		this.noteXML = "\t<notepads>\n\t<notepad>\n" + this.note + this.xloc + this.yloc + this.width + this.heigth
				+ this.fontname + this.fontsize + this.fontbold + this.fontitalic + this.fontcolorred
				+ this.fontcolorgreen + this.fontcolorblue + this.backgroundcolorred + this.backgroundcolorgreen
				+ this.backgroundcolorblue + this.bordercolorred + this.bordercolorgreen + this.bordercolorblue
				+ this.drawshadow + "\t</notepad>\n\t</notepads>\n";
		return this.noteXML;
	}

}
