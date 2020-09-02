package com.example.dwdwproject.ResponseDTOs;

public class TypeRecord {
    private int typeId;
    private String recordType;

    public TypeRecord(int typeId, String recordType) {
        this.typeId = typeId;
        this.recordType = recordType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
