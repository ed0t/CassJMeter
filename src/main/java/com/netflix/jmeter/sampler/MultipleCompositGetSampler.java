package com.netflix.jmeter.sampler;

import java.util.Collection;
import java.util.LinkedList;

public class MultipleCompositGetSampler extends AbstractSampler
{
    private static final long serialVersionUID = -2103499609822848596L;

    public ResponseData execute() throws OperationException
    {
        Operation ops = Connection.getInstance().newOperation(getColumnFamily(), false);
        setSerializers(ops);
//        return ops.getComposite(getProperty(KEY).getStringValue(), getProperty(COLUMN_NAME).getStringValue());
        return ops.getComposite(getKeys(), getProperty(COLUMN_NAME).getStringValue());
    }

    public Collection<String> getKeys() {
        LinkedList<String> keys = new LinkedList<String>();
        String text = getProperty(KEY).getStringValue();
        String[] keyArray = text.split(",");
        for (String singleKey : keyArray) {
            keys.add(singleKey);
        }
        return keys;
    }
}
