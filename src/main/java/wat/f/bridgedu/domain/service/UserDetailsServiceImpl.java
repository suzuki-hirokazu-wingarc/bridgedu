package wat.f.bridgedu.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
import wat.f.bridgedu.domain.entity.UserEntity;
import wat.f.bridgedu.domain.repository.AuthorityRepository;
import wat.f.bridgedu.domain.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userDetailsRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        try {
            UserEntity userEntity = userDetailsRepository.findById(username).get();
            System.out.println(userEntity);
            List<GrantedAuthority> authorities = authorityRepository.findByUsername(username).stream()
                .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                .collect(Collectors.toList());
            UserDetails userDetails = UserDetailsImpl.from(userEntity, authorities);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username, e);
        }
    }
    
}
