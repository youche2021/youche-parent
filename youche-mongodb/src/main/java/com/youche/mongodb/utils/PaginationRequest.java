package com.youche.mongodb.utils;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Data
public class PaginationRequest implements Serializable {

    private int page;
    private int size;

    public PaginationRequest() {}

    public PaginationRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static PaginationRequest from(Pageable pageable) {
        Sort sort = pageable.getSort();
        return new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize() );
    }

    public PaginationRequest next() {
        return new PaginationRequest(this.getPageNumber() + 1, this.getPageSize());
    }

    public PaginationRequest previous() {
        return this.getPageNumber() == 0 ? this : new PaginationRequest(this.getPageNumber() - 1, this.getPageSize());
    }

    public PaginationRequest first() {
        return new PaginationRequest(0, this.getPageSize());
    }

    public int getPageSize() {
        return this.size;
    }

    public int getPageNumber() {
        return this.page;
    }

    public long getOffset() {
        return (long)this.page * (long)this.size;
    }

    public boolean hasPrevious() {
        return this.page > 0;
    }

    public PaginationRequest previousOrFirst() {
        return this.hasPrevious() ? this.previous() : this.first();
    }
}
