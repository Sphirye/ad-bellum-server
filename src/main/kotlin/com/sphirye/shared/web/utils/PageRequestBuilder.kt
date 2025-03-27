package com.sphirye.shared.web.utils

import com.sphirye.springtemplate.model.Auditing_
import com.sphirye.springtemplate.service.tools.Constants
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
                return Sort.by(Sort.Order.desc(Auditing_.CREATED_DATE))
            }

            if (direction == "ASC") {
                return Sort.by(Sort.Order.asc(Auditing_.CREATED_DATE))
            }
        }

        return Sort.unsorted()
    }
}