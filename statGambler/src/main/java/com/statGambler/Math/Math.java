package com.statGambler.Math;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class Math {
		public static BigInteger CBig(int n, int k)
		{
			BigInteger fenzi = new BigInteger("1");
			BigInteger fenmu = new BigInteger("1");
			for(int i=n-k+1; i <= n; i++){
				String s = Integer.toString(i);
				BigInteger stobig = new BigInteger(s);
				fenzi = fenzi.multiply(stobig);
			}
			for(int j=1; j <= k; j++){
				String ss = Integer.toString(j);
				BigInteger stobig2 = new BigInteger(ss);
				fenmu = fenmu.multiply(stobig2);
			}
			BigInteger result = fenzi.divide(fenmu);
			return result;
	}
		
		public static int CInt(int n, int k) {
			return CBig(n, k).intValue();
		}
		
	public static double redondeo(double resul) {
		//return resul;
		return java.lang.Math.round((resul*100000000000d))/100000000000d;
	}
	
	public static double redondeoPromedios(double resul) {
		//return resul;
		return java.lang.Math.round((resul*100d))/100d;
	}
	
	public static String doubleToString(double d) {		
		DecimalFormat nF= new DecimalFormat();
		nF.applyPattern("#.########");
		
		return nF.format(d);
	}
	
	public static double redondeoEsperanzaRuletas(double resul) {
		//return resul;
		return java.lang.Math.round((resul*100d))/100d;
	}
	
	
}
