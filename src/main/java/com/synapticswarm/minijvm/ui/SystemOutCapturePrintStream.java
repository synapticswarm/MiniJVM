package com.synapticswarm.minijvm.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javafx.scene.control.TextArea;

/**
 * Captures output written to the System.out console and additionally prints it to a supplied javafx.scene.control.TextArea.
 * 
 * @author john
 *
 */
public class SystemOutCapturePrintStream extends PrintStream implements StackDisplayName {
	private TextArea textArea;
	
	public SystemOutCapturePrintStream(javafx.scene.control.TextArea textArea, OutputStream out){
		super(out);
		this.textArea = textArea;
	}
	
	public SystemOutCapturePrintStream(OutputStream out) {
		super(out);
	}

	public SystemOutCapturePrintStream(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public SystemOutCapturePrintStream(File file) throws FileNotFoundException {
		super(file);
	}

	public SystemOutCapturePrintStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public SystemOutCapturePrintStream(String fileName, String csn)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	public SystemOutCapturePrintStream(File file, String csn) throws FileNotFoundException,
			UnsupportedEncodingException {
		super(file, csn);
	}

	public SystemOutCapturePrintStream(OutputStream out, boolean autoFlush, String encoding)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
	}

	@Override
	public void println(String str) {
		super.println(str);
		this.textArea.appendText(str + "\n");
	}

    @Override
    public String getName() {
        return "System.out";
    }
}
