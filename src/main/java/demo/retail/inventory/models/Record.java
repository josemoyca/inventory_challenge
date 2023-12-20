package demo.retail.inventory.models;

import java.util.Objects;

public class Record {
    private String id;
    private String eventType;
    private String recordType;
    private String createdAt;
    private String sObject;

    private String message;

    public Record() {
    }

    public Record(String eventType, String recordType, String sObject) {
        this.eventType = eventType;
        this.recordType = recordType;
        this.sObject = sObject;
    }

    public Record(String eventType, String recordType, String sObject, String message) {
        this.eventType = eventType;
        this.recordType = recordType;
        this.sObject = sObject;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getsObject() {
        return sObject;
    }

    public void setsObject(String sObject) {
        this.sObject = sObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Record record = (Record) object;
        return Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Record{");
        sb.append("id='").append(id).append('\'');
        sb.append(", eventType='").append(eventType).append('\'');
        sb.append(", recordType='").append(recordType).append('\'');
        sb.append(", createdAt='").append(createdAt).append('\'');
        sb.append(", sObject='").append(sObject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
