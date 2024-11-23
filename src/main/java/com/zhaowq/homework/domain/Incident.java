package com.zhaowq.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
    private Long id;
    private String title;
    private String description;
    private String createTime;
}
