package com.oceaneconsulting.tanaguru.enumerations;

/**
 * Type de niveau d'audit
 *
 * @author diamamp
 *
 */
public enum AuditLevel {

    AW22_OR("Aw22;LEVEL_3"),
    AW22_AR("Aw22;LEVEL_2"),
    AW22_BZ("Aw22;LEVEL_1");

    private String value;

    private AuditLevel() {
        // TODO Auto-generated constructor stub
    }

    private AuditLevel(String value) {
        this.value = value;
    }

    public AuditLevel getStatusFromValue(String statusName) {
        for (AuditLevel statusType : AuditLevel.values()) {
            if (statusType.getValue().equals(statusName)) {
                return statusType;
            }
        }
        for (AuditLevel statusType : AuditLevel.values()) {
            if (statusType.name().equals(statusName)) {
                return statusType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {

        for (AuditLevel c : AuditLevel.values()) {
            if (c.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public boolean equals(String value) {
        AuditLevel statusType = getStatusFromValue(value);
        if (statusType != null) {
            return this.equals(statusType);
        }
        return false;
    }

}