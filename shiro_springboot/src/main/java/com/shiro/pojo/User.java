package com.shiro.pojo;

import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 */
@Entity
@Table(name = "pe_user")
@Getter
@Setter
/**
 * AuthCachePrincipal
 *  redis和shiro插件包提供的一个接口
 */
public class User  implements Serializable, AuthCachePrincipal {
    private static final long serialVersionUID = 4297464181093070302L;

    /**
     * ID
     * @return
     */
    @Id
    private String id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="pe_user_role",joinColumns={@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")}
    )
    private Set<Role> roles = new HashSet<Role>();


    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
