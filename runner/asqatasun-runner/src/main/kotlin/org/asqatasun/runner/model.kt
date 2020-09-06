package org.asqatasun.runner

enum class Referential(val code: String) {
    RGAA_4_0("Rgaa40"),
    RGAA_3_0("Rgaa30"),
    ACCESSIWEB_2_2("Aw22"),
    SEO("Seo");
    companion object {
        private val map = values().associateBy(Referential::code)
        fun fromCode(code: String): Referential? = map[code]
    }
}

enum class Level(val code: String) {
    GOLD("Or"),
    SILVER("Ar"),
    BRONZE("Bz");
    companion object {
        private val map = values().associateBy(Level::code)
        fun fromCode(code: String): Level? = map[code]
    }
}

enum class AuditType(val code: String) {
    PAGE_AUDIT("Page"),
    UPLOAD_AUDIT("File"),
    SCENARIO_AUDIT("Scenario"),
    SITE_AUDIT("File");
    companion object {
        private val map= values().associateBy(AuditType::code)
        fun fromCode(code: String): AuditType? = map[code]
    }
}
