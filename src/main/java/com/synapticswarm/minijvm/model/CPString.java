package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPString extends BaseConstantPoolEntry{
	private int utf8Index = -1;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        setComment(comment);
        Helper.checkArgHasOneIndex(value, getDisplayName());
        setUtf8Index(Integer.parseInt(Helper.stripLeadingHash(value)));
    }

    public int getUtf8Index() {
        return utf8Index;
    }

    public void setUtf8Index(int utf8Index) {
        this.utf8Index = utf8Index;
    }

    @Override
	public String getDisplayName() {
		return "String";
	}

	@Override
	public String toString() {
		return 	getDisplayName() + ":" + this.utf8Index;
	}

    @Override
	public String lookupValue(MiniConstantPool mcp) {
		CPUtf8 c = (CPUtf8) mcp.getEntries()[this.utf8Index];
		return c.getRawStringValue();
	}
	
}