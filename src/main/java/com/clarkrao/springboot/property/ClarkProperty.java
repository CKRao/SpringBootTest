package com.clarkrao.springboot.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/25 22:12
 * @Description:
 */
@Component
@Data
public class ClarkProperty {

    @Value("${com.clark.title}")
    private String title;

    @Value("${com.clark.description}")
    private String description;
}
