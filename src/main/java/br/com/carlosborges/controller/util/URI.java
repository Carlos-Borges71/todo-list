package br.com.carlosborges.controller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URI {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UFT-8" );
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
}
