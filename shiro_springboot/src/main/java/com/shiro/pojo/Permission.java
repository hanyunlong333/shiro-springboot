package com.shiro.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pe_permission")
public class Permission  implements Serializable {
    private static  final long serialVersionUID = -4990810027542971546L;

    /**
     * 主键
     */
    @Id
    private String id;
    private String name;
    private String code;
    private String description;
}
