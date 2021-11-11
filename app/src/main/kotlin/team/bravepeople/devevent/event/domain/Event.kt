package team.bravepeople.devevent.event.domain

data class Event(
    val site: String? = "",
    val name: String = "",
    val category: String? = "",
    val headerDate: String = "", // 헤더 날짜
    val joinDate: String? = "", // 신청 날짜
    val startDate: String? = "", // 시작 날짜
    val owner: String? = "" // 주최
) {
    fun contains(_value: String): Boolean {
        val value = _value.lowercase()
        return name.lowercase().contains(value) ||
            category?.lowercase()?.contains(value) == true ||
            owner?.lowercase()?.contains(value) == true
    }
}
