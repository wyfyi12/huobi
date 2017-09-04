package bean;

import java.util.ArrayList;

public class Prop {
	private static ArrayList<String> domains=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{add("yzsmarts.xyz"); add("txmail.xyz");}};
	private static String txlkey="xwSMBSC5kVUzQFzs3CP5OO-kXvvRF-uByr4uhSw6v2s";
	private static String logkey="hAfGD7Ye2O2lEBd_YGJUXjzg4BSGm2f-cyCh-0EUPaI";
	private static String corpid="wm714ea8cd6bcdb5b1";
	private static int hour=23;
	private static int min=59;
	public static ArrayList<String> getDomains() {
		return domains;
	}
	public static void setDomains(ArrayList<String> domains) {
		Prop.domains = domains;
	}
	
	public static String getTxlkey() {
		return txlkey;
	}
	public static void setTxlkey(String txlkey) {
		Prop.txlkey = txlkey;
	}
	public static String getLogkey() {
		return logkey;
	}
	public static void setLogkey(String logkey) {
		Prop.logkey = logkey;
	}
	public static String getCorpid() {
		return corpid;
	}
	public static void setCorpid(String corpid) {
		Prop.corpid = corpid;
	}
	public static int getHour() {
		return hour;
	}
	public static void setHour(int hour) {
		Prop.hour = hour;
	}
	public static int getMin() {
		return min;
	}
	public static void setMin(int min) {
		Prop.min = min;
	}
	
}
