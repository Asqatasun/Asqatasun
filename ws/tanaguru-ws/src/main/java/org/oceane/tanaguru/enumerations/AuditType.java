package org.oceane.tanaguru.enumerations;

/**
 * type  de status
 * 
 * @author diamamp
 * 
 */
public enum AuditType {
    PAGE("PAGE"), 
    SITE("SITE"),
    SCENARIO("SCENARIO");
    
    private String value;

    private AuditType()
    {
        // TODO Auto-generated constructor stub
    }

    private AuditType( String value )
    {
        this.value = value;
    }

    public AuditType getStatusFromValue( String statusName )
    {
        for ( AuditType statusType : AuditType.values() )
        {
            if ( statusType.getValue().equals( statusName ) )
            {
                return statusType;
            }
        }
        for ( AuditType statusType : AuditType.values() )
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
        AuditType statusType = getStatusFromValue( value );
        if ( statusType != null )
        {
            return this.equals( statusType );
        }
        return false;
    }

    
}
