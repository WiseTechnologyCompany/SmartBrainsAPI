package br.com.wisefinances.smartbrains.domain.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Setter
public class PageDTO {

    private List<?> content;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;

    public PageDTO(Page<?> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}