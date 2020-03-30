#!/usr/bin/env bash

set -e

# All paths are **relative** to HERE

# Upgrade script (by now incomplete)
rsync ../../../../../../../engine/asqatasun-resources/src/main/resources/upgrade/Asqatasun_upgrade_from_v4.0.3_to_v4.1.0.sh .

# SQL upgrade for both engine and web-app
rsync ../../../../../../../engine/asqatasun-resources/src/main/resources/sql-update/asqatasun-40-update-from-4.0.3-to-4.1.0.sql .
rsync ../../../../../../../web-app/tgol-resources/src/main/resources/sql-update/tgol-40-update-from-4.0.3-to-4.1.0.sql .

# SQL Procedures
for i in \
    PROCEDURE_AUDIT_delete_from_user_email.sql \
    PROCEDURE_CONTRACT_delete_from_label.sql \
    PROCEDURE_CONTRACT_functionality_add_from_contract_label.sql \
    PROCEDURE_CONTRACT_functionality_add_from_user_email.sql \
    PROCEDURE_CONTRACT_functionality_remove_from_contract_label.sql \
    PROCEDURE_CONTRACT_functionality_remove_from_user_email.sql \
    PROCEDURE_CONTRACT_referential_add_from_contract_label.sql \
    PROCEDURE_CONTRACT_referential_add_from_user_email.sql \
    PROCEDURE_CONTRACT_referential_remove_from_contract_label.sql \
    PROCEDURE_CONTRACT_referential_remove_from_user_email.sql \
    ; do
    rsync "../../../../../../../web-app/tgol-resources/src/main/resources/sql-management/${i}" . ;
done

# WAR file
rsync ../../../../../../../web-app/asqatasun-web-app/target/asqatasun-web-app-5.0-SNAPSHOT.war .
