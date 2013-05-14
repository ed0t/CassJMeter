package com.netflix.jmeter.sampler;

import java.util.Collection;
import java.util.LinkedList;

public class MultiGetSampler extends AbstractSampler
{
    private static final long serialVersionUID = -2103499609822848596L;

    public ResponseData execute() throws OperationException
    {
        Operation ops = Connection.getInstance().newOperation(getColumnFamily(), false);
        setSerializers(ops);
        return ops.get(getKeys(), getColumnName());
    }

    public Collection<Object> getKeys() {
        LinkedList<Object> keys = new LinkedList<Object>();
        String text = getProperty(KEY).getStringValue();
        String[] keyArray = text.split(",");
        for (String singleKey : keyArray) {
          keys.add(convert(singleKey, getKSerializerType()));
        }
        return keys;
    }
}
