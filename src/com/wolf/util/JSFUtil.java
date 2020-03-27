package com.wolf.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	public static void successMessage(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,"");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}
	
	public static void errorMessage(String titulo,String detalhe){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo,detalhe);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}
	
	public static void warnMessage(String titulo,String detalhe){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,detalhe);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}
	
	public static void copy(String text){
		  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		  StringSelection selection = new StringSelection(text);
		  clipboard.setContents(selection, null);
		}
}
