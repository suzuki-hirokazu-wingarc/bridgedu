package wat.f.bridgedu.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.UserDetailsImpl;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity userEntity = userDetailsRepository.find(username);
            List<GrantedAuthority> authorities = userDetailsRepository.findAuthorities(username).stream()
                .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                .collect(Collectors.toList());
            UserDetails userDetails = UserDetailsImpl.from(userEntity, authorities);
            return userDetails;
        } catch (Exception e) {
            throw new UsernameNotFoundException(username, e);
        }
    }
    
}
