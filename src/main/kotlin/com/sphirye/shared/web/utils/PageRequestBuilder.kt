package com.sphirye.shared.web.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest

object PageRequestBuilder {

    public fun build(request: NativeWebRequest): PageRequest {

        val page = request.getParameter("page")?.toIntOrNull() ?: Constants.API_REQUEST_DEFAULT_PAGE
        val pageSize = request.getParameter("pageSize")?.toIntOrNull() ?: Constants.API_REQUEST_DEFAULT_PAGE_SIZE
        val sortDirection = request.getParameter("sortDirection")?.toString()
        val sort = setSortByDirection(sortDirection)

        return PageRequest.of(page, pageSize, sort)
    }

    private fun setSortByDirection(direction: String?): Sort {
        if (!direction.isNullOrEmpty()) {
            if (direction == "DESC") {
                return Sort.by(Sort.Order.desc(Constants.CREATED_DATE_FIELD))
            }

            if (direction == "ASC") {
                return Sort.by(Sort.Order.asc(Constants.CREATED_DATE_FIELD))
            }
        }

        return Sort.unsorted()
    }
}
