package com.oceaneconsulting.tanaguru.enumerations;

/**
 * Type de niveau d'audit
 * 
 * @author diamamp
 * 
 */
public enum AuditLevel {
	OR("AW22;Or"), 
    AR("AW22;Ar"),
    BZ("AW22;Bz");
    
    private String value;

    private AuditLevel()
    {
        // TODO Auto-generated constructor stub
    }

    private AuditLevel( String value )
    {
        this.value = value;
    }

    public AuditLevel getStatusFromValue( String statusName )
    {
        for ( AuditLevel statusType : AuditLevel.values() )
        {
            if ( statusType.getValue().equals( statusName ) )
            {
                return statusType;
            }
        }
        for ( AuditLevel statusType : AuditLevel.values() )
        {
            if ( statusType.name().equals( statusName ) )
            {
                return statusType;
            }
        }
        return null;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public boolean equals( String value )
    {
        AuditLevel statusType = getStatusFromValue( value );
        if ( statusType != null )
        {
            return this.equals( statusType );
        }
        return false;
    }

    
}
