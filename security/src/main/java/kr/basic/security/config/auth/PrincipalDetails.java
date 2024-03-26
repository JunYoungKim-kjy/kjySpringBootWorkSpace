package kr.basic.security.config.auth;

import kr.basic.security.entity.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
@Data
public class PrincipalDetails implements UserDetails , OAuth2User {

    private Users user;
    private Map<String, Object> attributes;

    public PrincipalDetails(Users user){
        this.user = user;
    }

    public PrincipalDetails(Users user,Map<String,Object> attributes){
        this.user = user;
        this.attributes = attributes; //구글로그인할때 프로필 정보 이메일이 넘겨옴.
    }


    // 일반 로그인 객체
    //user 권한 넘겨준다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });


        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는가.?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠가지지 않았나.?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //user 비번이 기간이 지났나.?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 되어있나?
    @Override
    public boolean isEnabled() {
        // 계정이 비활성화 될 때 : 1년동안 방문하지 않은 사이트 -> 휴면 계정
        return true;
    }

    @Override
    public Map<String, Object> getAttribute(String name) {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
