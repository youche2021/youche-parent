package com.youche.mongodb.utils;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class Pagination<T> implements Serializable {

    private long total;
    private List<T> content = new ArrayList();
    private PaginationRequest pageable;

    public Pagination() {
    }

    public long getTotal() {
        return this.total;
    }

    /**
     * 根据Page，转化成 Pagination
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Pagination<T> from(Page<T> page) {
        if (page == null) {
            return new Pagination<>();
        }
        return build(page.getContent(), page.getTotalElements(), page.getPageable());
    }

    /**
     * 根据参数，初始化 Pagination
     *
     * @param content
     * @param totalElements
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Pagination<T> build(List<T> content, long totalElements, Pageable pageable) {
        Pagination<T> pageResult = new Pagination<>();
        pageResult.setTotal(totalElements);
        pageResult.setContent(content);
        pageResult.setPageable(PaginationRequest.from(pageable));
        return pageResult;
    }

    public int getTotalPages() {
        return this.getSize() == 0 ? 1 : (int) Math.ceil((double) this.total / (double) this.getSize());
    }

    public long getTotalElements() {
        return this.total;
    }

    public boolean hasNext() {
        return this.getNumber() + 1 < this.getTotalPages();
    }

    public int getNumber() {
        return this.pageable.getPageNumber();
    }

    public int getSize() {
        return this.pageable.getPageSize();
    }

    public int getNumberOfElements() {
        return this.content.size();
    }

    public boolean hasPrevious() {
        return this.getNumber() > 0;
    }

    public boolean isFirst() {
        return !this.hasPrevious();
    }

    public boolean isLast() {
        return !this.hasNext();
    }

    public PaginationRequest nextPageable() {
        return this.hasNext() ? this.pageable.next() :  null;
    }

    public PaginationRequest previousPageable() {
        return this.hasPrevious() ? this.pageable.previousOrFirst() : null;
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public Iterator<T> iterator() {
        return this.content.iterator();
    }
}
