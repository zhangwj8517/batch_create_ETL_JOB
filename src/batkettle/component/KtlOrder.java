package batkettle.component;

import java.util.*;
import java.util.LinkedHashMap;

public class KtlOrder {

	private String hopsxml = new String();
	private String orderXML = new String();
	private String enabled = "Y";

	ArrayList<Hop> hops = new ArrayList<Hop>();

	private class Hop {

		public Hop(String home, String to) {
			this.setHome(home);
			this.setTo(to);
		}

		public String getHome() {
			return home;
		}

		public void setHome(String home) {
			this.home = home;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		private String home;
		private String to;
	}

	public void addFromToRelationship(String form, String to) {

		hops.add(new Hop(form, to));
	}

	public String getXML() {

		for (int i = 0; i < hops.size(); i++) {

			String from = hops.get(i).getHome();
			String to = hops.get(i).getTo();
			this.hopsxml = this.hopsxml + "\t<hop> <from>" + from + "</from><to>" + to + "</to><enabled>" + this.enabled
					+ "</enabled> </hop>\n";

		}

		this.orderXML = "\t<order>\n" + this.hopsxml + "\t</order>\n";
		return this.orderXML;
	}

}
